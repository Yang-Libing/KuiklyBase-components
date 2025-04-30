#include "function.h"
#include "hilog/log.h"
#include <future>
#include <unistd.h>

typedef void *(*callJSFunction)(void *data);

struct CallbackData {
    void *data;
    void *callback;
    std::promise<void *> result;
    bool sync;
};

napi_value
callFunction(napi_env env, napi_value recv, napi_value func, int size, const napi_value *argv,
             napi_value *exceptionObj) {
    napi_value result;
    auto status = napi_call_function(env, recv, func, size, argv, &result);
    if (exceptionObj != nullptr && status == napi_pending_exception) {
        napi_get_and_clear_last_exception(env, exceptionObj);
    }
    return result;
}

napi_value createFunction(napi_env env, const char *name, napi_callback callback, void *release) {
    napi_value result;
    napi_create_function(env, name, NAPI_AUTO_LENGTH, callback, release, &result);
    return result;
}

napi_threadsafe_function
createThreadSafeFunctionWithCallback(napi_env env, const char *workName, void *callback) {
    napi_value workNameNapiValue = 0;
    napi_create_string_utf8(env, workName, NAPI_AUTO_LENGTH, &workNameNapiValue);
    napi_threadsafe_function tsfn;
    napi_create_threadsafe_function(env, 0, NULL, workNameNapiValue, 0, 1, NULL, NULL, NULL,
                                    (napi_threadsafe_function_call_js) callback, &tsfn);
    return tsfn;
}

void callThreadSafeFunctionWithData(napi_threadsafe_function tsfn, void *data) {
    napi_acquire_threadsafe_function(tsfn);
    napi_call_threadsafe_function(tsfn, data, napi_tsfn_nonblocking);
}

static void callJS(napi_env env, napi_value noUsed, void *context, void *data) {
    auto *callbackData = (CallbackData *) data;
    napi_handle_scope scope;
    napi_open_handle_scope(env, &scope);
    auto callback = (callJSFunction) callbackData->callback;
    auto result = callback(callbackData->data);
    if (callbackData->sync) {
        callbackData->result.set_value(result);
    }
    napi_close_handle_scope(env, scope);
}

napi_threadsafe_function
createThreadSafeFunctionWithSync(napi_env env, const char *workName) {
    napi_value workNameNapiValue = nullptr;
    napi_create_string_utf8(env, workName, NAPI_AUTO_LENGTH, &workNameNapiValue);
    napi_threadsafe_function tsfn;
    napi_create_threadsafe_function(env, 0, NULL, workNameNapiValue, 0, 1, NULL, NULL, NULL,
                                    (napi_threadsafe_function_call_js) callJS, &tsfn);
    return tsfn;
}

void *
callThreadSafeFunction(napi_threadsafe_function tsfn, void *callback, void *data, bool sync, int tsfnOriginTid) {
    napi_acquire_threadsafe_function(tsfn);
    auto *callbackData = new CallbackData{
            .data = data,
            .callback = callback,
            .result = {},
            .sync = sync
    };
    // Worker 线程 napi_call_threadsafe_function_with_priority 存在 bug，已提 ir 给华为
    if (tsfnOriginTid == getpid()) {
        // with
        napi_call_threadsafe_function_with_priority(tsfn, callbackData, napi_priority_high, true);
    } else {
        napi_call_threadsafe_function(tsfn, (void *) callbackData, napi_tsfn_blocking);
    }
    if (sync) {
        return callbackData->result.get_future().get();
    } else {
        return nullptr;
    }
}


#include "napi/native_api.h"
#include <pthread.h>
#include <rawfile/raw_file_manager.h>
#include <thread>
#include <sys/stat.h>
#include "nativerender/plugin_manager.h"
#include "libs/shared/libkn_api.h"
#include "libs/skikobridge/ComposeDispatcher.h"

// 定义NAPI注册的宏
#define NAPI_DESCRIPTOR(name, method)                                                                                  \
    napi_property_descriptor { name, nullptr, method, nullptr, nullptr, nullptr, napi_default, nullptr }

#define NAPI_DESCRIPTOR_N(method)                                                                                      \
    napi_property_descriptor {                                                                                         \
#method, nullptr, method, nullptr, nullptr, nullptr, napi_default, nullptr                                     \
    }

static napi_value TestShow(napi_env env, napi_callback_info info) {
    size_t requireArgc = 2;
    size_t argc = 2;
    napi_value args[2] = {nullptr};

    napi_get_cb_info(env, info, &argc, args, nullptr, nullptr);

    napi_valuetype valuetype0;
    napi_typeof(env, args[0], &valuetype0);

    napi_valuetype valuetype1;
    napi_typeof(env, args[1], &valuetype1);

    int value0;
    napi_get_value_int32(env, args[0], &value0);

    int value1;
    napi_get_value_int32(env, args[1], &value1);

//     int sum = testSum(value0, value1);

//     napi_value result;
//     napi_create_int32(env, sum, &result);
    return nullptr;
}

static napi_value InitResourceManager(napi_env env, napi_callback_info info) {
    size_t argc = 1;
    napi_value args[1] = {nullptr};
    napi_get_cb_info(env, info, &argc, args, nullptr, nullptr);

    auto manager = OH_ResourceManager_InitNativeResourceManager(env, args[0]);
    initNativeResourceManager(manager);

    napi_value result;
    napi_create_int32(env, 0, &result);
    return result;
}

static napi_value drawFromRenderNode(napi_env env, napi_callback_info info) {
    std::string id("opengl_xcomponent");
    PluginRender *render = PluginRender::GetInstance(id);
    render->didReceiveRenderNodeDraw();
    return nullptr;
}

static napi_value CCallArkTS(napi_env env, napi_callback_info info) {
    size_t argc = 1;
    // 声明参数数组
    napi_value args[1] = {nullptr};

    // 获取传入的参数并依次放入参数数组中
    napi_get_cb_info(env, info, &argc, args, nullptr, nullptr);

    // 创建一个int，作为ArkTS的入参
    napi_value argv = nullptr;
    napi_create_int32(env, 2, &argv);

    // 调用传入的callback，并将其结果返回
    napi_value result = nullptr;
    napi_call_function(env, nullptr, args[0], 1, &argv, &result);

    return result;
}

libkn_kref_com_tmm_demo_ComposeController m_composeController;

static void touchEventToComposeController(napi_value touchEvent) {
    if (m_composeController.pinned != nullptr) {
        sendPointerEvent2ComposeController(m_composeController, touchEvent);
    }
}

static bool didReceiveRenderNodeDraw() {
    if (m_composeController.pinned != nullptr) {
        didReceiveRenderNodeDrawFromComposeController(m_composeController);
        return true;
    }
    return false;
}

static bool
initializeComposeController(int xComponentSelfIdealSizeWidth, int xComponentSelfIdealSizeHeight) {
    if (m_composeController.pinned == nullptr) {
        m_composeController = OhComposeMainDraw(xComponentSelfIdealSizeWidth,
                                                xComponentSelfIdealSizeHeight);
        return true;
    }
    return false;
}


EXTERN_C_START
static napi_value
Init(napi_env
env,
napi_value exports
) {
skikobridge_compose_coroutines_dispatcher_bind_jsenv(env);
napi_property_descriptor desc[] = {
        {"testShow",            nullptr, TestShow,            nullptr, nullptr, nullptr, napi_default, nullptr},
        {"drawFromRenderNode",  nullptr, drawFromRenderNode,  nullptr, nullptr, nullptr, napi_default, nullptr},
        {"initResourceManager", nullptr, InitResourceManager, nullptr, nullptr, nullptr, napi_default, nullptr},
        {"cCallArkTS",          nullptr, CCallArkTS,          nullptr, nullptr, nullptr, napi_default, nullptr}
};
napi_define_properties(env, exports,
sizeof(desc) / sizeof(desc[0]), desc);
PluginManager::GetInstance()->
Export(env,
        exports,
        touchEventToComposeController,
        initializeComposeController,
        didReceiveRenderNodeDraw
);
InitEnv(env, exports
);
return
exports;
}
EXTERN_C_END

        NAPI_EXTERN

static napi_module demoModule = {
        .nm_version = 1,
        .nm_flags = 0,
        .nm_filename = nullptr,
        .nm_register_func = Init,
        .nm_modname = "show",
        .nm_priv = ((void *) 0),
        .reserved = {0},
};

extern "C" __attribute__((constructor)) void RegisterShowModule(void) {
    napi_module_register(&demoModule);
}

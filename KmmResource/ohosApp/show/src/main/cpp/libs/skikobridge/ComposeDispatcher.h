//
// Created by krauschen on 2024/3/16.
//
// Node APIs are not fully supported. To solve the compilation error of the interface cannot be found,
// please include "napi/native_api.h".

#ifndef SKIKOBRIDGE_COMPOSE_DISPATCHER_H
#define SKIKOBRIDGE_COMPOSE_DISPATCHER_H

#include <js_native_api_types.h>

#define COMPOSE_EXPORT extern "C"

typedef void (*skikobridge_async_vsync_callback)(long long timestamp, void *dataContext);

typedef void (*skikobridge_async_callback)(void *data);

/*获取当前环境的 napi_env*/
COMPOSE_EXPORT napi_env skikobridge_get_current_napi_env();

/* 绑定 JS 的运行环境 */
COMPOSE_EXPORT void skikobridge_compose_coroutines_dispatcher_bind_jsenv(void *jsEnv);

/* 获取屏幕像素密度 */
COMPOSE_EXPORT float skikobridge_compose_get_screen_density();

/*
请求一帧 Vsync 回调
*/
COMPOSE_EXPORT void skikobridge_compose_request_vsync_frame(void *dataContext,
                                                            skikobridge_async_vsync_callback callBack);

/*
一段时间后将任务回抛到 JS 线程进行执行 after 是毫秒 
TODO:需要处理取消的情况
*/
COMPOSE_EXPORT void skikobridge_compose_coroutines_dispatcher_async_task(float after, void *data,
                                                                         skikobridge_async_callback callBack);

/* LogI */
COMPOSE_EXPORT void skikobridge_compose_logI(const char *log);

/* LogE */
COMPOSE_EXPORT void skikobridge_compose_logE(const char *log);

/* LogW */
COMPOSE_EXPORT void skikobridge_compose_logW(const char *log);

/* LogD */
COMPOSE_EXPORT void skikobridge_compose_logD(const char *log);

#endif //SKIKOBRIDGE_COMPOSE_DISPATCHER_H

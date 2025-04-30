//
// Created by krauschen on 2024/3/26.
//
// Node APIs are not fully supported. To solve the compilation error of the interface cannot be found,
// please include "napi/native_api.h".

#ifndef SKIKOBRIDGE_SKIKOBRIDGETOUCHOHOSEVENT_H
#define SKIKOBRIDGE_SKIKOBRIDGETOUCHOHOSEVENT_H

#include "types.h"
#include "js_native_api.h"

typedef napi_value SkikoBridgeJSTouchEvent;
typedef napi_value SkikoBridgeJSTouchObject;

/* 单个触摸事件点位对象 */
typedef struct SkikoBridgeNativeTouchObject {
    int touchType;
    int id;
    float x;
    float y;
} SkikoBridgeNativeTouchObject;

/* 单个触摸事件历史点位对象 */
typedef struct SkikoBridgeNativeTouchHistoricalPoint {
    int size;
    float force;
    long timestamp;
    SkikoBridgeNativeTouchObject *touchObject;
} SkikoBridgeNativeTouchHistoricalPoint;

/* 单个触摸事件历史点位对象 */
typedef struct SkikoBridgeNativeTouchEvent {
    int id;
    int touchType;
    long timeStamp; // 对齐到 Android Compose

    int touchesCount;
    void *touchesObject;

    int historicalPointsCount;
    void *historicalPoints;

} SkikoBridgeNativeTouchEvent;

/*
根据 JS 的 touchEvent 创建一个 C 的 TouchEvent
*/
SKIKO_EXPORT SkikoBridgeNativeTouchEvent *
skikobridge_create_native_touch_event_from_jsevent(SkikoBridgeJSTouchEvent jsTouchEvent);

/*
获取 nativeTouchEvent 数组中的 touchObject
*/
SKIKO_EXPORT SkikoBridgeNativeTouchObject *
skikobridge_native_touch_get_touch_object_at_index(SkikoBridgeNativeTouchEvent *nativeEvent,
                                                   int index);

/*
获取 nativeTouchEvent 数组中的 historicalPoint
*/
SKIKO_EXPORT SkikoBridgeNativeTouchHistoricalPoint *
skikobridge_native_touch_get_historycal_point_at_index(SkikoBridgeNativeTouchEvent *nativeEvent,
                                                       int index);

/*
释放 nativeTouchEvent
*/
SKIKO_EXPORT void skikobridge_delete_native_touch_event(SkikoBridgeNativeTouchEvent *nativeEvent);

#endif // SKIKOBRIDGE_SKIKOBRIDGETOUCHOHOSEVENT_H

//
// Created on 2024/3/18.
//
// Node APIs are not fully supported. To solve the compilation error of the interface cannot be found,
// please include "napi/native_api.h".

#ifndef SKIKOBRIDGE_PICTURERECORDER_H
#define SKIKOBRIDGE_PICTURERECORDER_H

#include "types.h"

SKIKO_EXPORT KNativePointer org_jetbrains_skia_PictureRecorder__1nMake();

SKIKO_EXPORT KNativePointer org_jetbrains_skia_PictureRecorder__1nGetFinalizer();
SKIKO_EXPORT KNativePointer
org_jetbrains_skia_PictureRecorder__1nBeginRecording(KNativePointer ptr, KFloat left,
                                                     KFloat top, KFloat right,
                                                     KFloat bottom, KNativePointer bbh);

SKIKO_EXPORT KNativePointer
org_jetbrains_skia_PictureRecorder__1nGetRecordingCanvas(KNativePointer ptr);

SKIKO_EXPORT KNativePointer
org_jetbrains_skia_PictureRecorder__1nFinishRecordingAsPicture(KNativePointer ptr);

SKIKO_EXPORT KNativePointer org_jetbrains_skia_PictureRecorder__1nFinishRecordingAsPictureWithCull(
        KNativePointer ptr, KFloat left, KFloat top, KFloat right, KFloat bottom);

SKIKO_EXPORT KNativePointer
org_jetbrains_skia_PictureRecorder__1nFinishRecordingAsDrawable(KNativePointer ptr);

#endif // SKIKOBRIDGE_PICTURERECORDER_H

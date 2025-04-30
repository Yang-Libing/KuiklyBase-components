//
// Created on 2024/3/14.
//
// Node APIs are not fully supported. To solve the compilation error of the interface cannot be found,
// please include "napi/native_api.h".

#ifndef SKIKOBRIDGE_BACKENDRENDERTARGET_H
#define SKIKOBRIDGE_BACKENDRENDERTARGET_H

#include "types.h"

SKIKO_EXPORT KNativePointer org_jetbrains_skia_BackendRenderTarget__1nGetFinalizer();

SKIKO_EXPORT KNativePointer
org_jetbrains_skia_BackendRenderTarget__1nMakeGL(KInt width, KInt height, KInt sampleCnt,
                                                 KInt stencilBits, KInt fbId,
                                                 KInt fbFormat);

#endif //SKIKOBRIDGE_BACKENDRENDERTARGET_H

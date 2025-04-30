//
// Created on 2024/3/14.
//
// Node APIs are not fully supported. To solve the compilation error of the interface cannot be found,
// please include "napi/native_api.h".

#ifndef SKIKOBRIDGE_COLORSPACE_H
#define SKIKOBRIDGE_COLORSPACE_H

#include "types.h"

SKIKO_EXPORT KNativePointer org_jetbrains_skia_ColorSpace__1nGetFinalizer();

SKIKO_EXPORT KNativePointer org_jetbrains_skia_ColorSpace__1nMakeSRGB();

SKIKO_EXPORT KNativePointer org_jetbrains_skia_ColorSpace__1nMakeSRGBLinear();

SKIKO_EXPORT KNativePointer org_jetbrains_skia_ColorSpace__1nMakeDisplayP3();


SKIKO_EXPORT void
org_jetbrains_skia_ColorSpace__nConvert(KNativePointer fromPtr, KNativePointer toPtr, float r,
                                        float g, float b, float a, float *result);


SKIKO_EXPORT KBoolean org_jetbrains_skia_ColorSpace__1nIsGammaCloseToSRGB(KNativePointer ptr);

SKIKO_EXPORT KBoolean org_jetbrains_skia_ColorSpace__1nIsGammaLinear(KNativePointer ptr);

SKIKO_EXPORT KBoolean org_jetbrains_skia_ColorSpace__1nIsSRGB(KNativePointer ptr);


#endif //SKIKOBRIDGE_COLORSPACE_H

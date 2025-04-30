//
// Created on 2024/3/14.
//
// Node APIs are not fully supported. To solve the compilation error of the interface cannot be found,
// please include "napi/native_api.h".

#ifndef SKIKOBRIDGE_PAINT_H
#define SKIKOBRIDGE_PAINT_H

#include "types.h"

SKIKO_EXPORT KNativePointer org_jetbrains_skia_Paint__1nGetFinalizer();

SKIKO_EXPORT KNativePointer org_jetbrains_skia_Paint__1nMake();

SKIKO_EXPORT KNativePointer org_jetbrains_skia_Paint__1nMakeClone(KNativePointer ptr);

SKIKO_EXPORT KBoolean org_jetbrains_skia_Paint__1nEquals(KNativePointer aPtr, KNativePointer bPtr);

SKIKO_EXPORT void org_jetbrains_skia_Paint__1nReset(KNativePointer ptr);

SKIKO_EXPORT KBoolean org_jetbrains_skia_Paint__1nIsAntiAlias(KNativePointer ptr);

SKIKO_EXPORT void org_jetbrains_skia_Paint__1nSetAntiAlias(KNativePointer ptr, KBoolean value);

SKIKO_EXPORT KBoolean org_jetbrains_skia_Paint__1nIsDither(KNativePointer ptr);

SKIKO_EXPORT void org_jetbrains_skia_Paint__1nSetDither(KNativePointer ptr, KBoolean value);

SKIKO_EXPORT KInt org_jetbrains_skia_Paint__1nGetColor(KNativePointer ptr);

SKIKO_EXPORT void org_jetbrains_skia_Paint__1nSetColor(KNativePointer ptr, KInt color);


SKIKO_EXPORT void
org_jetbrains_skia_Paint__1nGetColor4f(KNativePointer ptr, KInteropPointer result);


SKIKO_EXPORT void
org_jetbrains_skia_Paint__1nSetColor4f(KNativePointer ptr, KFloat r, KFloat g, KFloat b, KFloat a,
                                       KNativePointer colorSpacePtr);

SKIKO_EXPORT KInt org_jetbrains_skia_Paint__1nGetMode(KNativePointer ptr);

SKIKO_EXPORT void org_jetbrains_skia_Paint__1nSetMode(KNativePointer ptr, KInt mode);

SKIKO_EXPORT KFloat org_jetbrains_skia_Paint__1nGetStrokeWidth(KNativePointer ptr);

SKIKO_EXPORT void org_jetbrains_skia_Paint__1nSetStrokeWidth(KNativePointer ptr, KFloat width);

SKIKO_EXPORT KFloat org_jetbrains_skia_Paint__1nGetStrokeMiter(KNativePointer ptr);

SKIKO_EXPORT void org_jetbrains_skia_Paint__1nSetStrokeMiter(KNativePointer ptr, KFloat miter);

SKIKO_EXPORT KInt org_jetbrains_skia_Paint__1nGetStrokeCap(KNativePointer ptr);

SKIKO_EXPORT void org_jetbrains_skia_Paint__1nSetStrokeCap(KNativePointer ptr, KInt cap);

SKIKO_EXPORT KInt org_jetbrains_skia_Paint__1nGetStrokeJoin(KNativePointer ptr);

SKIKO_EXPORT void org_jetbrains_skia_Paint__1nSetStrokeJoin(KNativePointer ptr, KInt join);

SKIKO_EXPORT KNativePointer org_jetbrains_skia_Paint__1nGetMaskFilter(KNativePointer ptr);

SKIKO_EXPORT void
org_jetbrains_skia_Paint__1nSetMaskFilter(KNativePointer ptr, KNativePointer filterPtr);

SKIKO_EXPORT KNativePointer org_jetbrains_skia_Paint__1nGetImageFilter(KNativePointer ptr);

SKIKO_EXPORT void
org_jetbrains_skia_Paint__1nSetImageFilter(KNativePointer ptr, KNativePointer filterPtr);

SKIKO_EXPORT KInt org_jetbrains_skia_Paint__1nGetBlendMode(KNativePointer ptr);

SKIKO_EXPORT void org_jetbrains_skia_Paint__1nSetBlendMode(KNativePointer ptr, KInt mode);

SKIKO_EXPORT KNativePointer org_jetbrains_skia_Paint__1nGetPathEffect(KNativePointer ptr);

SKIKO_EXPORT void
org_jetbrains_skia_Paint__1nSetPathEffect(KNativePointer ptr, KNativePointer pathEffectPtr);

SKIKO_EXPORT KNativePointer org_jetbrains_skia_Paint__1nGetShader(KNativePointer ptr);

SKIKO_EXPORT void
org_jetbrains_skia_Paint__1nSetShader(KNativePointer ptr, KNativePointer shaderPtr);

SKIKO_EXPORT KNativePointer org_jetbrains_skia_Paint__1nGetColorFilter(KNativePointer ptr);

SKIKO_EXPORT void
org_jetbrains_skia_Paint__1nSetColorFilter(KNativePointer ptr, KNativePointer colorFilterPtr);

SKIKO_EXPORT KBoolean org_jetbrains_skia_Paint__1nHasNothingToDraw(KNativePointer ptr);


#endif // SKIKOBRIDGE_PAINT_H

//
// Created on 2024/3/14.
//
// Node APIs are not fully supported. To solve the compilation error of the interface cannot be found,
// please include "napi/native_api.h".

#ifndef SKIKOBRIDGE_DIRECTCONTEXT_H
#define SKIKOBRIDGE_DIRECTCONTEXT_H

#include "types.h"

SKIKO_EXPORT KNativePointer org_jetbrains_skia_DirectContext__1nMakeGL();

SKIKO_EXPORT KNativePointer
org_jetbrains_skia_DirectContext__1nMakeGLWithInterface(KNativePointer ptr);

SKIKO_EXPORT KNativePointer org_jetbrains_skia_DirectContext__1nMakeMetal(KNativePointer devicePtr,
                                                                          KNativePointer queuePtr);

SKIKO_EXPORT KNativePointer
org_jetbrains_skia_DirectContext__1nMakeDirect3D(KNativePointer adapterPtr,
                                                 KNativePointer devicePtr,
                                                 KNativePointer queuePtr);

SKIKO_EXPORT void org_jetbrains_skia_DirectContext__1nFlush(KNativePointer ptr);

SKIKO_EXPORT void org_jetbrains_skia_DirectContext__1nSubmit(KNativePointer ptr, KBoolean syncCpu);

SKIKO_EXPORT void org_jetbrains_skia_DirectContext__1nReset(KNativePointer ptr, KInt flags);

SKIKO_EXPORT void org_jetbrains_skia_DirectContext__1nAbandon(KNativePointer ptr, KInt flags);

#endif // SKIKOBRIDGE_DIRECTCONTEXT_H

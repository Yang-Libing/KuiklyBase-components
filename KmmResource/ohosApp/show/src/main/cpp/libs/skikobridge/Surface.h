//
// Created on 2024/3/14.
//
// Node APIs are not fully supported. To solve the compilation error of the interface cannot be found,
// please include "napi/native_api.h".

#ifndef SKIKOBRIDGE_SURFACE__H
#define SKIKOBRIDGE_SURFACE__H

#include "types.h"

SKIKO_EXPORT KNativePointer
org_jetbrains_skia_Surface__1nMakeRasterDirect(KInt width, KInt height, KInt colorType,
                                               KInt alphaType, KNativePointer colorSpacePtr,
                                               KNativePointer pixelsPtr, KInt rowBytes,
                                               KInt *surfacePropsInts);


SKIKO_EXPORT KNativePointer
org_jetbrains_skia_Surface__1nMakeRasterDirectWithPixmap(KNativePointer pixmapPtr,
                                                         KInt *surfacePropsInts);


SKIKO_EXPORT KNativePointer
org_jetbrains_skia_Surface__1nMakeRaster(KInt width, KInt height, KInt colorType,
                                         KInt alphaType, KNativePointer colorSpacePtr,
                                         KInt rowBytes, KInt *surfacePropsInts);


SKIKO_EXPORT KNativePointer
org_jetbrains_skia_Surface__1nMakeRasterN32Premul(KInt width, KInt height);


SKIKO_EXPORT KNativePointer org_jetbrains_skia_Surface__1nMakeFromBackendRenderTarget(
        KNativePointer pContext, KNativePointer pBackendRenderTarget, KInt surfaceOrigin,
        KInt colorType,
        KNativePointer colorSpacePtr, KInteropPointer surfacePropsInts);

SKIKO_EXPORT KNativePointer org_jetbrains_skia_Surface__1nMakeFromMTKView(KNativePointer contextPtr,
                                                                          KNativePointer mtkViewPtr,
                                                                          KInt surfaceOrigin,
                                                                          KInt sampleCount,
                                                                          KInt colorType,
                                                                          KNativePointer colorSpacePtr,
                                                                          KInteropPointer surfacePropsInts);

SKIKO_EXPORT KNativePointer
org_jetbrains_skia_Surface__1nMakeRenderTarget(KNativePointer contextPtr, KBoolean budgeted,
                                               KInt width, KInt height, KInt colorType,
                                               KInt alphaType, KNativePointer colorSpacePtr,
                                               KInt sampleCount, KInt surfaceOrigin,
                                               KInteropPointer surfacePropsInts,
                                               KBoolean shouldCreateWithMips);

SKIKO_EXPORT KNativePointer org_jetbrains_skia_Surface__1nMakeNull(KInt width, KInt height);

SKIKO_EXPORT KNativePointer org_jetbrains_skia_Surface__1nGetCanvas(KNativePointer ptr);

SKIKO_EXPORT KInt org_jetbrains_skia_Surface__1nGetWidth(KNativePointer ptr);

SKIKO_EXPORT KInt org_jetbrains_skia_Surface__1nGetHeight(KNativePointer ptr);

SKIKO_EXPORT KNativePointer org_jetbrains_skia_Surface__1nMakeImageSnapshot(KNativePointer ptr);

SKIKO_EXPORT KNativePointer
org_jetbrains_skia_Surface__1nMakeImageSnapshotR(KNativePointer ptr, KInt left, KInt top,
                                                 KInt right, KInt bottom);

SKIKO_EXPORT KInt org_jetbrains_skia_Surface__1nGenerationId(KNativePointer ptr);

SKIKO_EXPORT KBoolean
org_jetbrains_skia_Surface__1nReadPixelsToPixmap(KNativePointer ptr, KNativePointer pixmapPtr,
                                                 KInt srcX, KInt srcY);

SKIKO_EXPORT KBoolean
org_jetbrains_skia_Surface__1nReadPixels(KNativePointer ptr, KNativePointer bitmapPtr, KInt srcX,
                                         KInt srcY);

SKIKO_EXPORT void
org_jetbrains_skia_Surface__1nWritePixelsFromPixmap(KNativePointer ptr, KNativePointer pixmapPtr,
                                                    KInt x, KInt y);

SKIKO_EXPORT void
org_jetbrains_skia_Surface__1nWritePixels(KNativePointer ptr, KNativePointer bitmapPtr, KInt x,
                                          KInt y);

SKIKO_EXPORT void
org_jetbrains_skia_Surface__1nFlushAndSubmit(KNativePointer ptr, KBoolean syncCpu);

SKIKO_EXPORT void org_jetbrains_skia_Surface__1nFlush(KNativePointer ptr);

SKIKO_EXPORT KBoolean org_jetbrains_skia_Surface__1nUnique(KNativePointer ptr);


SKIKO_EXPORT void
org_jetbrains_skia_Surface__1nGetImageInfo(KNativePointer ptr, KInt *imageInfoResult,
                                           KNativePointer *colorSpacePtrsArray);


SKIKO_EXPORT KNativePointer
org_jetbrains_skia_Surface__1nMakeSurface(KNativePointer ptr, KInt width, KInt height);

SKIKO_EXPORT KNativePointer
org_jetbrains_skia_Surface__1nMakeSurfaceI(KNativePointer ptr, KInt width, KInt height,
                                           KInt colorType, KInt alphaType,
                                           KNativePointer colorSpacePtr);

SKIKO_EXPORT void
org_jetbrains_skia_Surface__1nDraw(KNativePointer ptr, KNativePointer canvasPtr, KFloat x, KFloat y,
                                   KInt samplingModeValue1, KInt samplingModeValue2,
                                   KNativePointer paintPtr);

SKIKO_EXPORT KBoolean
org_jetbrains_skia_Surface__1nPeekPixels(KNativePointer ptr, KNativePointer dstPixmapPtr);

SKIKO_EXPORT void
org_jetbrains_skia_Surface__1nNotifyContentWillChange(KNativePointer ptr, KInt mode);

SKIKO_EXPORT KNativePointer org_jetbrains_skia_Surface__1nGetRecordingContext(KNativePointer ptr);

#endif // SKIKOBRIDGE_SURFACE__H

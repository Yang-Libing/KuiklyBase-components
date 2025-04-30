//
// Created on 2024/3/14.
//
// Node APIs are not fully supported. To solve the compilation error of the interface cannot be found,
// please include "napi/native_api.h".

#ifndef SKIKOBRIDGE_CANVAS_H
#define SKIKOBRIDGE_CANVAS_H

#include "types.h"

SKIKO_EXPORT KNativePointer org_jetbrains_skia_Canvas__1nGetFinalizer();

SKIKO_EXPORT KNativePointer
org_jetbrains_skia_Canvas__1nMakeFromBitmap(KNativePointer bitmapPtr, KInt flags,
                                            KInt pixelGeometry);

SKIKO_EXPORT void
org_jetbrains_skia_Canvas__1nDrawPoint(KNativePointer canvasPtr, KFloat x, KFloat y,
                                       KNativePointer paintPtr);


SKIKO_EXPORT void
org_jetbrains_skia_Canvas__1nDrawPoints(KNativePointer canvasPtr, int mode, KInt coordsCount,
                                        KFloat *coords, KNativePointer paintPtr);

SKIKO_EXPORT void
org_jetbrains_skia_Canvas__1nDrawLine(KNativePointer canvasPtr, KFloat x0, KFloat y0, KFloat x1,
                                      KFloat y1, KNativePointer paintPtr);

SKIKO_EXPORT void
org_jetbrains_skia_Canvas__1nDrawArc(KNativePointer canvasPtr, KFloat left, KFloat top,
                                     KFloat right,
                                     KFloat bottom, KFloat startAngle, KFloat sweepAngle,
                                     KBoolean includeCenter, KNativePointer paintPtr);

SKIKO_EXPORT void
org_jetbrains_skia_Canvas__1nDrawRect(KNativePointer canvasPtr, KFloat left, KFloat top,
                                      KFloat right,
                                      KFloat bottom, KNativePointer paintPtr);

SKIKO_EXPORT void
org_jetbrains_skia_Canvas__1nDrawOval(KNativePointer canvasPtr, KFloat left, KFloat top,
                                      KFloat right,
                                      KFloat bottom, KNativePointer paintPtr);

SKIKO_EXPORT void
org_jetbrains_skia_Canvas__1nDrawRRect(KNativePointer canvasPtr, KFloat left, KFloat top,
                                       KFloat right, KFloat bottom, KFloat *jradii, KInt jradiiSize,
                                       KNativePointer paintPtr);

SKIKO_EXPORT void
org_jetbrains_skia_Canvas__1nDrawDRRect(KNativePointer canvasPtr, KFloat ol, KFloat ot,
                                        KFloat oright,
                                        KFloat ob, KFloat *ojradii, KInt ojradiiSize, KFloat il,
                                        KFloat it, KFloat ir, KFloat ib, KFloat *ijradii,
                                        KInt ijradiiSize, KNativePointer paintPtr);

SKIKO_EXPORT void
org_jetbrains_skia_Canvas__1nDrawPath(KNativePointer canvasPtr, KNativePointer pathPtr,
                                      KNativePointer paintPtr);

SKIKO_EXPORT void
org_jetbrains_skia_Canvas__1nDrawImageRect(KNativePointer canvasPtr, KNativePointer imagePtr,
                                           KFloat sl, KFloat st, KFloat sr, KFloat sb, KFloat dl,
                                           KFloat dt, KFloat dr, KFloat db, KInt samplingModeVal1,
                                           KInt samplingModeVal2, KNativePointer paintPtr,
                                           KBoolean strict);

SKIKO_EXPORT void
org_jetbrains_skia_Canvas__1nDrawImageNine(KNativePointer canvasPtr, KNativePointer imagePtr,
                                           KInt cl,
                                           KInt ct, KInt cr, KInt cb, KFloat dl, KFloat dt,
                                           KFloat dr,
                                           KFloat db, KInt filterMode, KNativePointer paintPtr);

SKIKO_EXPORT void
org_jetbrains_skia_Canvas__1nDrawRegion(KNativePointer canvasPtr, KNativePointer regionPtr,
                                        KNativePointer paintPtr);

SKIKO_EXPORT void
org_jetbrains_skia_Canvas__1nDrawString(KNativePointer canvasPtr, KInteropPointer stringObj,
                                        KFloat x,
                                        KFloat y, KNativePointer skFontPtr,
                                        KNativePointer paintPtr);


SKIKO_EXPORT void
org_jetbrains_skia_Canvas__1nDrawTextBlob(KNativePointer canvasPtr, KNativePointer blobPtr,
                                          KFloat x,
                                          KFloat y, KNativePointer paintPtr);


SKIKO_EXPORT void
org_jetbrains_skia_Canvas__1nDrawPicture(KNativePointer ptr, KNativePointer picturePtr,
                                         KFloat *matrixArr, KNativePointer paintPtr);


SKIKO_EXPORT void
org_jetbrains_skia_Canvas__1nDrawVertices(KNativePointer ptr, KInt verticesMode, KInt vertexCount,
                                          KFloat *positionsArr, KInt *colorsArr,
                                          KFloat *texCoordsArr,
                                          KInt indexCount, KShort *indexArr, KInt blendMode,
                                          KNativePointer paintPtr);

SKIKO_EXPORT void
org_jetbrains_skia_Canvas__1nDrawPatch(KNativePointer ptr, KFloat *cubicsArr, KInt *colorsArr,
                                       KFloat *texCoordsArr, KInt blendMode,
                                       KNativePointer paintPtr);

SKIKO_EXPORT void
org_jetbrains_skia_Canvas__1nDrawDrawable(KNativePointer ptr, KNativePointer drawablePtr,
                                          KFloat *matrixArr);

SKIKO_EXPORT void org_jetbrains_skia_Canvas__1nClear(KNativePointer ptr, KInt color);

SKIKO_EXPORT void
org_jetbrains_skia_Canvas__1nDrawPaint(KNativePointer canvasPtr, KNativePointer paintPtr);

SKIKO_EXPORT void
org_jetbrains_skia_Canvas__1nSetMatrix(KNativePointer canvasPtr, KFloat *matrixArr);

SKIKO_EXPORT void org_jetbrains_skia_Canvas__1nResetMatrix(KNativePointer canvasPtr);


SKIKO_EXPORT void
org_jetbrains_skia_Canvas__1nGetLocalToDevice(KNativePointer canvasPtr, KFloat *resultingFloats);

SKIKO_EXPORT void
org_jetbrains_skia_Canvas__1nClipRect(KNativePointer canvasPtr, KFloat left, KFloat top,
                                      KFloat right,
                                      KFloat bottom, KInt mode, KBoolean antiAlias);

SKIKO_EXPORT void
org_jetbrains_skia_Canvas__1nClipRRect(KNativePointer canvasPtr, KFloat left, KFloat top,
                                       KFloat right, KFloat bottom, KFloat *jradii, KInt jradiiSize,
                                       KInt mode, KBoolean antiAlias);

SKIKO_EXPORT void
org_jetbrains_skia_Canvas__1nClipPath(KNativePointer canvasPtr, KNativePointer pathPtr, KInt mode,
                                      KBoolean antiAlias);

SKIKO_EXPORT void
org_jetbrains_skia_Canvas__1nClipRegion(KNativePointer canvasPtr, KNativePointer regionPtr,
                                        KInt mode);


SKIKO_EXPORT void org_jetbrains_skia_Canvas__1nConcat(KNativePointer ptr, KFloat *matrixArr);


SKIKO_EXPORT void org_jetbrains_skia_Canvas__1nConcat44(KNativePointer ptr, KFloat *matrixArr);


SKIKO_EXPORT void
org_jetbrains_skia_Canvas__1nTranslate(KNativePointer canvasPtr, KFloat dx, KFloat dy);


SKIKO_EXPORT void
org_jetbrains_skia_Canvas__1nScale(KNativePointer canvasPtr, KFloat sx, KFloat sy);


SKIKO_EXPORT void
org_jetbrains_skia_Canvas__1nRotate(KNativePointer canvasPtr, KFloat deg, KFloat x, KFloat y);


SKIKO_EXPORT void org_jetbrains_skia_Canvas__1nSkew(KNativePointer canvasPtr, KFloat sx, KFloat sy);


SKIKO_EXPORT KInt
org_jetbrains_skia_Canvas__1nReadPixels(KNativePointer ptr, KNativePointer bitmapPtr, KInt srcX,
                                        KInt srcY);

SKIKO_EXPORT KInt
org_jetbrains_skia_Canvas__1nWritePixels(KNativePointer ptr, KNativePointer bitmapPtr, KInt x,
                                         KInt y);

SKIKO_EXPORT KInt org_jetbrains_skia_Canvas__1nSave(KNativePointer ptr);

SKIKO_EXPORT KInt
org_jetbrains_skia_Canvas__1nSaveLayer(KNativePointer ptr, KNativePointer paintPtr);

SKIKO_EXPORT KInt
org_jetbrains_skia_Canvas__1nSaveLayerRect(KNativePointer ptr, KFloat left, KFloat top,
                                           KFloat right,
                                           KFloat bottom, KNativePointer paintPtr);

SKIKO_EXPORT KInt org_jetbrains_skia_Canvas__1nGetSaveCount(KNativePointer ptr);

SKIKO_EXPORT void org_jetbrains_skia_Canvas__1nRestore(KNativePointer ptr);

SKIKO_EXPORT void org_jetbrains_skia_Canvas__1nRestoreToCount(KNativePointer ptr, KInt saveCount);

#endif // SKIKOBRIDGE_CANVAS_H

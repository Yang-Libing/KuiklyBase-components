//
// Created on 2024/3/14.
//
// Node APIs are not fully supported. To solve the compilation error of the interface cannot be found,
// please include "napi/native_api.h".

#ifndef SKIKOBRIDGE_PATH_H
#define SKIKOBRIDGE_PATH_H

#include "types.h"

SKIKO_EXPORT KNativePointer org_jetbrains_skia_Path__1nGetFinalizer();

SKIKO_EXPORT KNativePointer org_jetbrains_skia_Path__1nMake();

SKIKO_EXPORT KNativePointer org_jetbrains_skia_Path__1nMakeFromSVGString(KInteropPointer d);

SKIKO_EXPORT KBoolean org_jetbrains_skia_Path__1nEquals(KNativePointer aPtr, KNativePointer bPtr);

SKIKO_EXPORT KBoolean
org_jetbrains_skia_Path__1nIsInterpolatable(KNativePointer ptr, KNativePointer comparePtr);

SKIKO_EXPORT KNativePointer
org_jetbrains_skia_Path__1nMakeLerp(KNativePointer ptr, KNativePointer endingPtr,
                                    KFloat weight);

SKIKO_EXPORT KInt org_jetbrains_skia_Path__1nGetFillMode(KNativePointer ptr);

SKIKO_EXPORT void org_jetbrains_skia_Path__1nSetFillMode(KNativePointer ptr, KInt fillMode);

SKIKO_EXPORT KBoolean org_jetbrains_skia_Path__1nIsConvex(KNativePointer ptr);


SKIKO_EXPORT KBoolean
org_jetbrains_skia_Path__1nIsOval(KNativePointer ptr, KInteropPointer resultArray);

SKIKO_EXPORT KBoolean
org_jetbrains_skia_Path__1nIsRRect(KNativePointer ptr, KInteropPointer resultArray);


SKIKO_EXPORT void org_jetbrains_skia_Path__1nReset(KNativePointer ptr);

SKIKO_EXPORT void org_jetbrains_skia_Path__1nRewind(KNativePointer ptr);

SKIKO_EXPORT KBoolean org_jetbrains_skia_Path__1nIsEmpty(KNativePointer ptr);

SKIKO_EXPORT KBoolean org_jetbrains_skia_Path__1nIsLastContourClosed(KNativePointer ptr);

SKIKO_EXPORT KBoolean org_jetbrains_skia_Path__1nIsFinite(KNativePointer ptr);

SKIKO_EXPORT KBoolean org_jetbrains_skia_Path__1nIsVolatile(KNativePointer ptr);

SKIKO_EXPORT void org_jetbrains_skia_Path__1nSetVolatile(KNativePointer ptr, KBoolean isVolatile);

SKIKO_EXPORT KBoolean
org_jetbrains_skia_Path__1nIsLineDegenerate(KFloat x0, KFloat y0, KFloat x1, KFloat y1,
                                            KBoolean exact);

SKIKO_EXPORT KBoolean
org_jetbrains_skia_Path__1nIsQuadDegenerate(KFloat x0, KFloat y0, KFloat x1, KFloat y1, KFloat x2,
                                            KFloat y2, KBoolean exact);

SKIKO_EXPORT KBoolean
org_jetbrains_skia_Path__1nIsCubicDegenerate(KFloat x0, KFloat y0, KFloat x1, KFloat y1,
                                             KFloat x2, KFloat y2, KFloat x3, KFloat y3,
                                             KBoolean exact);

SKIKO_EXPORT KBoolean
org_jetbrains_skia_Path__1nMaybeGetAsLine(KNativePointer ptr, KInteropPointer resultArray);


SKIKO_EXPORT KInt org_jetbrains_skia_Path__1nGetPointsCount(KNativePointer ptr);

SKIKO_EXPORT void
org_jetbrains_skia_Path__1nGetPoint(KNativePointer ptr, KInt index, KInteropPointer resultArray);

SKIKO_EXPORT KInt
org_jetbrains_skia_Path__1nGetPoints(KNativePointer ptr, KInteropPointer pointsArray, KInt max);

SKIKO_EXPORT KInt org_jetbrains_skia_Path__1nCountVerbs(KNativePointer ptr);

SKIKO_EXPORT KInt
org_jetbrains_skia_Path__1nGetVerbs(KNativePointer ptr, KByte *verbsArray, KInt max);


SKIKO_EXPORT KInt org_jetbrains_skia_Path__1nApproximateBytesUsed(KNativePointer ptr);

SKIKO_EXPORT void org_jetbrains_skia_Path__1nSwap(KNativePointer ptr, KNativePointer otherPtr);

SKIKO_EXPORT void
org_jetbrains_skia_Path__1nGetBounds(KNativePointer ptr, KInteropPointer resultArray);

SKIKO_EXPORT void org_jetbrains_skia_Path__1nUpdateBoundsCache(KNativePointer ptr);

SKIKO_EXPORT void
org_jetbrains_skia_Path__1nComputeTightBounds(KNativePointer ptr, KInteropPointer resultArray);

SKIKO_EXPORT KBoolean
org_jetbrains_skia_Path__1nConservativelyContainsRect(KNativePointer ptr, float l, float t,
                                                      float r, float b);

SKIKO_EXPORT void org_jetbrains_skia_Path__1nIncReserve(KNativePointer ptr, int extraPtCount);

SKIKO_EXPORT void org_jetbrains_skia_Path__1nMoveTo(KNativePointer ptr, KFloat x, KFloat y);

SKIKO_EXPORT void org_jetbrains_skia_Path__1nRMoveTo(KNativePointer ptr, KFloat dx, KFloat dy);

SKIKO_EXPORT void org_jetbrains_skia_Path__1nLineTo(KNativePointer ptr, KFloat x, KFloat y);

SKIKO_EXPORT void org_jetbrains_skia_Path__1nRLineTo(KNativePointer ptr, KFloat dx, KFloat dy);

SKIKO_EXPORT void
org_jetbrains_skia_Path__1nQuadTo(KNativePointer ptr, KFloat x1, KFloat y1, KFloat x2, KFloat y2);

SKIKO_EXPORT void
org_jetbrains_skia_Path__1nRQuadTo(KNativePointer ptr, KFloat dx1, KFloat dy1, KFloat dx2,
                                   KFloat dy2);

SKIKO_EXPORT void
org_jetbrains_skia_Path__1nConicTo(KNativePointer ptr, KFloat x1, KFloat y1, KFloat x2, KFloat y2,
                                   KFloat w);

SKIKO_EXPORT void
org_jetbrains_skia_Path__1nRConicTo(KNativePointer ptr, KFloat dx1, KFloat dy1, KFloat dx2,
                                    KFloat dy2, KFloat w);

SKIKO_EXPORT void
org_jetbrains_skia_Path__1nCubicTo(KNativePointer ptr, KFloat x1, KFloat y1, KFloat x2, KFloat y2,
                                   KFloat x3, KFloat y3);

SKIKO_EXPORT void
org_jetbrains_skia_Path__1nRCubicTo(KNativePointer ptr, KFloat dx1, KFloat dy1, KFloat dx2,
                                    KFloat dy2, KFloat dx3, KFloat dy3);

SKIKO_EXPORT void
org_jetbrains_skia_Path__1nArcTo(KNativePointer ptr, KFloat left, KFloat top, KFloat right,
                                 KFloat bottom, KFloat startAngle, KFloat sweepAngle,
                                 KBoolean forceMoveTo);

SKIKO_EXPORT void
org_jetbrains_skia_Path__1nTangentArcTo(KNativePointer ptr, KFloat x1, KFloat y1, KFloat x2,
                                        KFloat y2, KFloat radius);

SKIKO_EXPORT void
org_jetbrains_skia_Path__1nEllipticalArcTo(KNativePointer ptr, KFloat rx, KFloat ry,
                                           KFloat xAxisRotate, KInt size, KInt direction, KFloat x,
                                           float y);

SKIKO_EXPORT void
org_jetbrains_skia_Path__1nREllipticalArcTo(KNativePointer ptr, KFloat rx, KFloat ry,
                                            KFloat xAxisRotate, KInt size, KInt direction,
                                            KFloat dx,
                                            float dy);

SKIKO_EXPORT void org_jetbrains_skia_Path__1nClosePath(KNativePointer ptr);


SKIKO_EXPORT KInt
org_jetbrains_skia_Path__1nConvertConicToQuads(KFloat x0, KFloat y0, KFloat x1, KFloat y1,
                                               KFloat x2,
                                               KFloat y2, KFloat w, KInt pow2,
                                               KInteropPointer resultArray);


SKIKO_EXPORT KBoolean
org_jetbrains_skia_Path__1nIsRect(KNativePointer ptr, KInteropPointer resultArray);
SKIKO_EXPORT void
org_jetbrains_skia_Path__1nAddRect(KNativePointer ptr, KFloat l, KFloat t, KFloat r, KFloat b,
                                   KInt dirInt, KInt start);

SKIKO_EXPORT void
org_jetbrains_skia_Path__1nAddOval(KNativePointer ptr, KFloat l, KFloat t, KFloat r, KFloat b,
                                   KInt dirInt, KInt start);

SKIKO_EXPORT void
org_jetbrains_skia_Path__1nAddCircle(KNativePointer ptr, KFloat x, KFloat y, KFloat r, KInt dirInt);

SKIKO_EXPORT void
org_jetbrains_skia_Path__1nAddArc(KNativePointer ptr, KFloat l, KFloat t, KFloat r, KFloat b,
                                  KFloat startAngle, KFloat sweepAngle);

SKIKO_EXPORT void
org_jetbrains_skia_Path__1nAddRRect(KNativePointer ptr, KFloat l, KFloat t, KFloat r, KFloat b,
                                    KFloat *radii, KInt radiiSize, KInt dirInt, KInt start);

SKIKO_EXPORT void
org_jetbrains_skia_Path__1nAddPoly(KNativePointer ptr, KFloat *coords, KInt count, KBoolean close);


SKIKO_EXPORT void
org_jetbrains_skia_Path__1nAddPath(KNativePointer ptr, KNativePointer srcPtr, KBoolean extend);

SKIKO_EXPORT void
org_jetbrains_skia_Path__1nAddPathOffset(KNativePointer ptr, KNativePointer srcPtr, KFloat dx,
                                         KFloat dy, KBoolean extend);


SKIKO_EXPORT void
org_jetbrains_skia_Path__1nAddPathTransform(KNativePointer ptr, KNativePointer srcPtr,
                                            KFloat *matrixArr, KBoolean extend);


SKIKO_EXPORT void
org_jetbrains_skia_Path__1nReverseAddPath(KNativePointer ptr, KNativePointer srcPtr);

SKIKO_EXPORT void
org_jetbrains_skia_Path__1nOffset(KNativePointer ptr, KFloat dx, KFloat dy, KNativePointer dstPtr);


SKIKO_EXPORT void
org_jetbrains_skia_Path__1nTransform(KNativePointer ptr, KFloat *matrixArr, KNativePointer dstPtr,
                                     KBoolean pcBool);


SKIKO_EXPORT KBoolean
org_jetbrains_skia_Path__1nGetLastPt(KNativePointer ptr, KInteropPointer resultArray);


SKIKO_EXPORT void org_jetbrains_skia_Path__1nSetLastPt(KNativePointer ptr, KFloat x, KFloat y);

SKIKO_EXPORT KInt org_jetbrains_skia_Path__1nGetSegmentMasks(KNativePointer ptr);

SKIKO_EXPORT KBoolean org_jetbrains_skia_Path__1nContains(KNativePointer ptr, KFloat x, KFloat y);

SKIKO_EXPORT void org_jetbrains_skia_Path__1nDump(KNativePointer ptr);

SKIKO_EXPORT void org_jetbrains_skia_Path__1nDumpHex(KNativePointer ptr);


SKIKO_EXPORT KInt
org_jetbrains_skia_Path__1nSerializeToBytes(KNativePointer ptr, KInteropPointer dst);


SKIKO_EXPORT KNativePointer
org_jetbrains_skia_Path__1nMakeCombining(KNativePointer aPtr, KNativePointer bPtr,
                                         KInt jop);


SKIKO_EXPORT KNativePointer org_jetbrains_skia_Path__1nMakeFromBytes(KByte *bytesArray, KInt size);


SKIKO_EXPORT KInt org_jetbrains_skia_Path__1nGetGenerationId(KNativePointer ptr);

SKIKO_EXPORT KBoolean org_jetbrains_skia_Path__1nIsValid(KNativePointer ptr);


#endif // SKIKOBRIDGE_PATH_H

package com.tencent.qqlive.kmm.vbpbservice.export

import kotlin.experimental.ExperimentalObjCName
import kotlin.native.ObjCName

/**
 * Copyright (c) 2024 Tencent. All rights reserved
 *
 * PB 设备信息代理
 *
 * @author haibarawang
 * @date 2024/3/1 19:41
 */
@OptIn(ExperimentalObjCName::class)
@ObjCName("IVBPBDeviceInfo")
interface IVBPBDeviceInfo {
    // 屏幕宽度
    @ObjCName("pb_screenWidth")
    var screenWidth: Int

    // 屏幕高度
    @ObjCName("pb_screenHeight")
    var screenHeight: Int

    // 屏幕像素密度
    @ObjCName("pb_densityDpi")
    var densityDpi: Int

    // omgId
    @ObjCName("pb_omgId")
    var omgId: String

    // guid
    @ObjCName("pb_guid")
    var guid: String

    // 获取设备制造商信息
    @ObjCName("pb_manufacturer")
    var manufacturer: String

    // 设备ID
    @ObjCName("pb_deviceId")
    var deviceId: String

    // 设备类型
    @ObjCName("pb_deviceType")
    var deviceType: Int

    // 设备型号
    @ObjCName("pb_deviceModel")
    var deviceModel: String

    // 获取最大屏幕尺寸
    @ObjCName("pb_getMaxUiSizeByUiSizeType")
    fun getMaxUiSizeByUiSizeType(): Int

    // 获取当前屏幕尺寸
    @ObjCName("pb_getCurrentWindowUiSizeByUiSizeType")
    fun getCurrentWindowUiSizeByUiSizeType(): Int
}
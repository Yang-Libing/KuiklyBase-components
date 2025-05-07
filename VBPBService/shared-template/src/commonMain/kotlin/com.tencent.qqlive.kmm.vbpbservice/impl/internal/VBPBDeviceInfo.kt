package com.tencent.qqlive.kmm.vbpbservice.impl.internal

import com.tencent.qqlive.kmm.vbpbservice.export.IVBPBDeviceInfo

/**
 * Copyright (c) 2024 Tencent. All rights reserved
 *
 * PB 设备信息代理
 *
 * @author haibarawang
 * @date 2024/4/13 14:41
 */
object VBPBDeviceInfo : IVBPBDeviceInfo {

    var deviceInfo: IVBPBDeviceInfo? = null

    override var screenWidth: Int
        get() = deviceInfo?.screenWidth ?: 0
        set(value) {}

    override var screenHeight: Int
        get() = deviceInfo?.screenHeight ?: 0
        set(value) {}

    override var densityDpi: Int
        get() = deviceInfo?.densityDpi ?: 0
        set(value) {}

    override var omgId: String
        get() = deviceInfo?.omgId ?: ""
        set(value) {}

    override var guid: String
        get() = deviceInfo?.guid ?: ""
        set(value) {}

    override var manufacturer: String
        get() = deviceInfo?.manufacturer ?: ""
        set(value) {}

    override var deviceId: String
        get() = deviceInfo?.deviceId ?: ""
        set(value) {}

    override var deviceType: Int
        get() = deviceInfo?.deviceType ?: 0
        set(value) {}

    override var deviceModel: String
        get() = deviceInfo?.deviceModel ?: ""
        set(value) {}

    override fun getMaxUiSizeByUiSizeType(): Int = deviceInfo?.getMaxUiSizeByUiSizeType() ?: 0

    override fun getCurrentWindowUiSizeByUiSizeType(): Int =
        deviceInfo?.getCurrentWindowUiSizeByUiSizeType() ?: 0
}
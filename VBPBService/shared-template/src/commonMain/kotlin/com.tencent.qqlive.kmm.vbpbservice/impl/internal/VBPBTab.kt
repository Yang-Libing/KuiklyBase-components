package com.tencent.qqlive.kmm.vbpbservice.impl.internal

import com.tencent.qqlive.kmm.vbpbservice.export.IVBPBTab

/**
 * Copyright (c) 2025 Tencent. All rights reserved
 *
 * Tab 获取实例类
 *
 * @author berryyang
 * @date 2025/3/17 10:45
 */
object VBPBTab {
    const val pbUseCurlConfigKey = "tab_pbservice_use_curl"

    var tabImpl: IVBPBTab? = null

    fun getConfigString(configKey: String, defaultValue: String): String =
        tabImpl?.getConfigString(configKey, defaultValue) ?: ""

    fun getTabBooleanValue(key: String, defaultValue: Boolean): Boolean =
        tabImpl?.getTabBooleanValue(key, defaultValue) ?: false
}
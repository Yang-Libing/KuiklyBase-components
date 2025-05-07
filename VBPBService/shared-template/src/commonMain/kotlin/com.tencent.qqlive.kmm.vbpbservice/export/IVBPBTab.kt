package com.tencent.qqlive.kmm.vbpbservice.export

/**
 * Copyright (c) 2025 Tencent. All rights reserved
 *
 * Tab 接口
 *
 * @author berryyang
 * @date 2025/3/17 10:41
 */
interface IVBPBTab {
    fun getConfigString(configKey: String, defaultValue: String): String

    fun getTabBooleanValue(key: String, defaultValue: Boolean): Boolean
}
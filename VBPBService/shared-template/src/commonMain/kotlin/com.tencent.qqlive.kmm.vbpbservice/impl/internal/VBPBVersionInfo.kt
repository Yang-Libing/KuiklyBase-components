package com.tencent.qqlive.kmm.vbpbservice.impl.internal

import com.tencent.qqlive.kmm.vbpbservice.export.IVBPBVersionInfo


/**
 * Copyright (c) 2024 Tencent. All rights reserved
 *
 * PB 版本信息代理
 *
 * @author haibarawang
 * @date 2024/3/1 19:41
 */
object VBPBVersionInfo : IVBPBVersionInfo {

    var versionInfo: IVBPBVersionInfo? = null

    override fun getVersionName(): String = versionInfo?.getVersionName() ?: ""

    override fun getVersionCode(): Int = versionInfo?.getVersionCode() ?: 0

    override fun getPlatformId(): Int = versionInfo?.getPlatformId() ?: 0

    override fun getPlatformVersion(): String = versionInfo?.getPlatformVersion() ?: ""

    override fun getAppId(): Int = versionInfo?.getAppId() ?: 0

    override fun getAppNameId(): Int = versionInfo?.getAppNameId() ?: 0

    override fun getChannelId(): Int = versionInfo?.getChannelId() ?: 0
}
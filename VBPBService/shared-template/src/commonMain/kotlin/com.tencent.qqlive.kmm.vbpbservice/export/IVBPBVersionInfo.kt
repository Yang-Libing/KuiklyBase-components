package com.tencent.qqlive.kmm.vbpbservice.export

import kotlin.experimental.ExperimentalObjCName
import kotlin.native.ObjCName

/**
 * Copyright (c) 2024 Tencent. All rights reserved
 *
 * PB 版本信息代理
 *
 * @author haibarawang
 * @date 2024/3/1 19:41
 */
@OptIn(ExperimentalObjCName::class)
@ObjCName("IVBPBVersionInfo")
interface IVBPBVersionInfo {
    // 获取版本名称
    @ObjCName("pb_getVersionName")
    fun getVersionName(): String

    // 获取版本号
    @ObjCName("pb_getVersionCode")
    fun getVersionCode(): Int

    // 获取平台Id
    @ObjCName("pb_getPlatformId")
    fun getPlatformId(): Int

    // 获取系统版本号
    @ObjCName("pb_getPlatformVersion")
    fun getPlatformVersion(): String

    // 获取应用Id
    @ObjCName("pb_getAppId")
    fun getAppId(): Int

    // app的名称对应的编号。腾讯视频 app对应0、腾讯视频 HD app对应1
    @ObjCName("pb_getAppNameId")
    fun getAppNameId(): Int

    // 获取渠道号
    @ObjCName("pb_getChannelId")
    fun getChannelId(): Int
}

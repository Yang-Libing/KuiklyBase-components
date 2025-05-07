package com.tencent.qqlive.kmm.vbpbservice.export

import kotlin.experimental.ExperimentalObjCName
import kotlin.native.ObjCName

/**
 * Copyright (c) 2024 Tencent. All rights reserved
 *
 * PB 个人信息代理
 *
 * @author haibarawang
 * @date 2024/3/1 19:41
 */
@OptIn(ExperimentalObjCName::class)
interface IVBPBPersonalize {
    //  获取分桶信息
    @ObjCName("pb_getBucketInfo")
    fun getBucketInfo(): VBPBBucketInfo?

    // 获取审核状态信息
    @ObjCName("pb_getFlagInfo")
    fun getFlagInfo(): VBPBFlagInfo?

    // 获取用户画像信息
    @ObjCName("pb_getPortraitInfoList")
    fun getPortraitInfoList(): Array<VBPBPortraitInfo?>?

    //  获取用户特区信息
    @ObjCName("pb_getUserStatusInfo")
    fun getUserStatusInfo(): VBPBUserStatusInfo?
}
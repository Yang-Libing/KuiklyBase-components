package com.tencent.qqlive.kmm.vbpbservice.impl.internal

import com.tencent.qqlive.kmm.vbpbservice.export.IVBPBPersonalize
import com.tencent.qqlive.kmm.vbpbservice.export.VBPBBucketInfo
import com.tencent.qqlive.kmm.vbpbservice.export.VBPBFlagInfo
import com.tencent.qqlive.kmm.vbpbservice.export.VBPBPortraitInfo
import com.tencent.qqlive.kmm.vbpbservice.export.VBPBUserStatusInfo

/**
 * Copyright (c) 2024 Tencent. All rights reserved
 *
 * PB 个人信息代理
 *
 * @author haibarawang
 * @date 2024/3/1 19:41
 */
object VBPBPersonalize {

    var personalize: IVBPBPersonalize? = null

    // 获取分桶信息
    fun getBucketInfo(): VBPBBucketInfo? =
        if (personalize == null) VBPBBucketInfo() else personalize?.getBucketInfo()

    // 获取审核状态信息
    fun getFlagInfo(): VBPBFlagInfo = personalize?.getFlagInfo() ?: VBPBFlagInfo()

    // 获取用户画像信息
    fun getPortraitInfoList(): Array<VBPBPortraitInfo?> =
        personalize?.getPortraitInfoList() ?: emptyArray()

    // 获取用户特区信息
    fun getUserStatusInfo(): VBPBUserStatusInfo =
        personalize?.getUserStatusInfo() ?: VBPBUserStatusInfo()
}
package com.tencent.qqlive.kmm.vbpbservice.export;

/**
 * Copyright (c) 2024 Tencent. All rights reserved
 *
 * 用户特区信息
 *
 * @author haibarawang
 * @date 2024/3/1 19:41
 */
data class VBPBUserStatusInfo(
    // 青少年模式&推荐等使用到的加密字符串，给后台用于判断下发推荐等开关状态
    var mSessionCode: String? = null,
    // 特区用户 false 0：非特区用户  true 1：特区用户
    var mIsSpecialZone: Boolean = false,
    // 用户身份过期校验时间戳，linux，单位ms，13位，后台透传字段
    var mExpiredTime: Long = 0,
    // 标记用户状态，用于后台透传字段
    var mUserStatusKey: String? = null
)
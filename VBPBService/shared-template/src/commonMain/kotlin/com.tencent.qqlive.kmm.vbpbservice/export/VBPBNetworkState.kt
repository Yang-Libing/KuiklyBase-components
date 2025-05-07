package com.tencent.qqlive.kmm.vbpbservice.export;

import kotlin.experimental.ExperimentalObjCName
import kotlin.native.ObjCName

/**
 * Copyright (C) 2020 THL A29 Limited, a Tencent company. All rights reserved.
 *
 * 网络类型
 *
 * @author haibarawang
 * @date 2020/7/10 4:30 PM
 */
@OptIn(ExperimentalObjCName::class)
@ObjCName("VBPBNetworkState")
enum class VBPBNetworkState {
    NETWORK_STATE_UNKNOWN,                  // 未知类型
    NETWORK_STATE_DISCONNECT,               // 断开
    NETWORK_STATE_2G,                       // 2G
    NETWORK_STATE_3G,                       // 3G
    NETWORK_STATE_4G,                       // 4G
    NETWORK_STATE_5G,                       // 5G
    NETWORK_STATE_WIFI                      // WIFI
}

@OptIn(ExperimentalObjCName::class)
@ObjCName("VBNetworkState")
enum class VBNetworkState(private val content: Int) {
    NETWORK_STATE_DISCONNECT(-1),                  // 未知类型
    NETWORK_STATE_UNKNOWN(1),               // 断开
    NETWORK_STATE_2G(2),                       // 2G
    NETWORK_STATE_3G(3),                       // 3G
    NETWORK_STATE_4G(4),                       // 4G
    NETWORK_STATE_5G(5),                       // 5G
    NETWORK_STATE_WIFI(6);                    // WIFI

    @ObjCName("get")
    fun get(): Int = content
}
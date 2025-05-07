package com.tencent.qqlive.kmm.vbpbservice.impl.internal

import com.tencent.qqlive.kmm.vbpbservice.impl.platform.NETWORK_TYPE_NET_3G
import com.tencent.qqlive.kmm.vbpbservice.impl.platform.NETWORK_TYPE_NET_4G
import com.tencent.qqlive.kmm.vbpbservice.impl.platform.NETWORK_TYPE_NET_5G
import com.tencent.qqlive.kmm.vbpbservice.impl.platform.NETWORK_TYPE_NET_WIFI

/**
 * Copyright (c) 2024 Tencent. All rights reserved
 *
 * PB超时策略
 *
 * @author haibarawang
 * @date 2024/3/1 19:41
 */
object VBTransportTimeoutStrategy {

    private const val CONNECTION_TIMEOUT_WIFI = 5 // wifi环境超时配置 秒(s)
    private const val READ_WRITE_TIMEOUT_WIFI = 10

    private const val CONNECTION_TIMEOUT_4G = 10 // 4g环境超时配置 秒(s)
    private const val READ_WRITE_TIMEOUT_4G = 20

    private const val CONNECTION_TIMEOUT_3G = 10 // 3g环境超时配置 秒(s)
    private const val READ_WRITE_TIMEOUT_3G = 20

    private const val CONNECTION_TIMEOUT_2G = 20 // 2g环境超时配置 秒(s)
    private const val READ_WRITE_TIMEOUT_2G = 30

    fun getTimeoutInfo(networkState: Int): VBTransportTimeoutInfo {
        val readWriteTimeOut: Int
        val connTimeOut: Int
        when (networkState) {
            NETWORK_TYPE_NET_WIFI, NETWORK_TYPE_NET_5G -> {
                readWriteTimeOut = READ_WRITE_TIMEOUT_WIFI
                connTimeOut = CONNECTION_TIMEOUT_WIFI
            }

            NETWORK_TYPE_NET_4G -> {
                readWriteTimeOut = READ_WRITE_TIMEOUT_4G
                connTimeOut = CONNECTION_TIMEOUT_4G
            }

            NETWORK_TYPE_NET_3G -> {
                readWriteTimeOut = READ_WRITE_TIMEOUT_3G
                connTimeOut = CONNECTION_TIMEOUT_3G
            }

            else -> {
                readWriteTimeOut = READ_WRITE_TIMEOUT_2G
                connTimeOut = CONNECTION_TIMEOUT_2G
            }
        }
        VBPBLog.i(
            VBPBLog.TIMEOUT,
            "getTimeoutInfo() network state:" + networkState +
                    ",readWrite timeout:" + readWriteTimeOut +
                    ",connect timeout:" + connTimeOut
        )
        return VBTransportTimeoutInfo(readWriteTimeOut * 1000, connTimeOut * 1000)
    }
}
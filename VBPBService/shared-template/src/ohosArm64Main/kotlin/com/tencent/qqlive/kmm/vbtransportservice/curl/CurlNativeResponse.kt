package com.tencent.qqlive.kmm.vbtransportservice.curl

import com.tencent.qqlive.kmm.vbtransportservice.export.VBTransportElapseStatistics

/**
 * Copyright (c) 2025 Tencent. All rights reserved
 *
 * libcurl so库响应回调 kotlin 侧数据封装
 *
 * @author berryyang
 * @date 2025/3/6 9:58
 */
data class CurlNativeResponse(
    var code: Int = 0,
    var errorMsg: String = "",
    var headers: String = "",
    var data: ByteArray? = null,
    var redirectUrl: String = "",
    var elapse: VBTransportElapseStatistics = VBTransportElapseStatistics()
)

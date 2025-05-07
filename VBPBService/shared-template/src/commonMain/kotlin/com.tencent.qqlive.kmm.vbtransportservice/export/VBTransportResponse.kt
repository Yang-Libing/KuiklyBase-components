package com.tencent.qqlive.kmm.vbtransportservice.export

/**
 * Copyright (c) 2024 Tencent. All rights reserved
 *
 * 普通网络响应结构
 *
 * @author haibarawang
 * @date 2024/3/1 19:41
 */
open class VBTransportBaseResponse {
    var errorCode = 0
    var errorMessage: String = ""
    var header: Map<String, List<String>> = mutableMapOf()
    var serverIP: String = ""
    var serverPort: String = ""
    var elapseStatis: VBTransportElapseStatistics = VBTransportElapseStatistics()
}

class VBTransportStringResponse : VBTransportBaseResponse() {
    var data: String = ""
    lateinit var request: VBTransportStringRequest
}

class VBTransportBytesResponse : VBTransportBaseResponse() {
    var data: ByteArray? = null
    lateinit var request: VBTransportBytesRequest
}

class VBTransportPostResponse : VBTransportBaseResponse() {
    var data: Any? = null
    lateinit var request: VBTransportPostRequest
}

class VBTransportGetResponse : VBTransportBaseResponse() {
    var data: Any? = null
    lateinit var request: VBTransportGetRequest
}


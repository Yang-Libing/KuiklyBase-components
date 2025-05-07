package com.tencent.qqlive.kmm.vbtransportservice.export

import com.tencent.qqlive.kmm.vbpbservice.impl.internal.VBPBTab
import com.tencent.qqlive.kmm.vbpbservice.impl.internal.VBPBTab.pbUseCurlConfigKey

/**
 * Copyright (c) 2024 Tencent. All rights reserved
 *
 * 普通网络请求结构
 *
 * @author haibarawang
 * @date 2024/3/1 19:41
 */
enum class VBTransportMethod {
    GET, POST
}

enum class VBTransportContentType(private val description: String) {
    JSON("application/json"),
    BYTE("application/octet-stream");

    override fun toString(): String = description
}

open class VBTransportBaseRequest {
    var requestId: Int = 0
    var header = mutableMapOf<String, String>()
    var logTag: String = ""
    var url: String = ""
    var quicForceQuic = false
    var totalTimeout: Long = 0L
    // 底层是否使用 libcurl 进行请求
    var useCurl: Boolean = VBPBTab.getTabBooleanValue(pbUseCurlConfigKey, false)
}

class VBTransportStringRequest : VBTransportBaseRequest() {
}

class VBTransportBytesRequest : VBTransportBaseRequest() {
    var data: ByteArray = byteArrayOf()
}

class VBTransportPostRequest : VBTransportBaseRequest() {
    lateinit var data: Any

    fun isDataInitialize(): Boolean = this::data.isInitialized
}

class VBTransportGetRequest : VBTransportBaseRequest() {
}
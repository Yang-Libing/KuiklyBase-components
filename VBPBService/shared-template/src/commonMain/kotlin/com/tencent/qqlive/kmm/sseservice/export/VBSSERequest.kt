package com.tencent.qqlive.kmm.sseservice.export

/**
 * Copyright (c) 2025 Tencent. All rights reserved
 *
 * SSE 网络请求结构
 *
 * @author berryyang
 * @date 2025/4/15 19:06
 */
class VBSSERequest {
    var logTag: String = ""
    var url: String = ""
    var headers: Map<String, String> = mapOf()
    var data: ByteArray = byteArrayOf()
    var totalTimeout: Long = 0L
    // 仅内部访问
    internal var requestId: Int = 0
}

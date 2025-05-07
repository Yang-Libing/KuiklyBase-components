package com.tencent.qqlive.kmm.vbpbservice.impl.internal

import com.squareup.wire.kmm.Message
import com.squareup.wire.kmm.ProtoAdapter
import com.tencent.qqlive.kmm.vbpbservice.export.VBPBResultCode

/**
 * Copyright (c) 2024 Tencent. All rights reserved
 *
 * PB 业务数据结构打包/解包实现
 *
 * @author haibarawang
 * @date 2024/3/1 19:41
 */
class VBPBMessagePackage<REQUEST : Message<*, *>, RESPONSE : Message<*, *>>(
    private val responseAdapter: ProtoAdapter<RESPONSE>,
    private val logTag: String,
) {
    lateinit var requestMessage: REQUEST

    // PB Message 转 字节数组
    fun packageMessage(requestMessage: REQUEST): ByteArray {
        logI("package pb message")
        this.requestMessage = requestMessage
        return requestMessage.encode()
    }

    // 字节数组 转 PB Message
    fun unpackageMessage(responseBytes: ByteArray?): VBPBUnPackageMessageResult<RESPONSE> {
        logI("unpackage pb message")
        val unPackageMessageResult = VBPBUnPackageMessageResult<RESPONSE>()
        try {
            val responseMessage = responseAdapter.decode(responseBytes ?: ByteArray(0))
            unPackageMessageResult.responseMessage = responseMessage
        } catch (e: Throwable) {
            logI("unpackage pb message fail, decode fail:${e.message}")
            unPackageMessageResult.errorCode = VBPBResultCode.ERR_RESPONSE_PARSE_EXCEPTION
            unPackageMessageResult.errorMessage = "byte[] 转 PB Message,Response 解析错误"
            return unPackageMessageResult
        }
        unPackageMessageResult.errorCode = VBPBResultCode.CODE_OK
        return unPackageMessageResult
    }

    private fun logI(content: String) {
        VBPBLog.i(VBPBLog.MESSAGE_PACKAGE, "$logTag $content")
    }
}
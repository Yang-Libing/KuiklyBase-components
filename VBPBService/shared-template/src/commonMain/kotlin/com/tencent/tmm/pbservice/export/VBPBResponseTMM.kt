package com.tencent.tmm.pbservice.export

import com.squareup.wire.kmm.Message

data class VBPBResponseTMM<T : Message<*, *>?>(
    var errorCode: Long? = null,                 // 错误码
    var businessErrorCode: Long? = null,         // 业务错误码
    var responseMessage: T? = null               // 响应的协议
)

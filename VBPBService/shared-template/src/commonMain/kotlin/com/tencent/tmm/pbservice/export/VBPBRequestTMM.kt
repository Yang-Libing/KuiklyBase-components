package com.tencent.tmm.pbservice.export
import com.squareup.wire.kmm.Message

data class VBPBRequestTMM<R : Message<*, *>?>(
    var requestId: Long? = null,                // 请求 id
    var requestMessage: R? = null               // 请求的协议
)

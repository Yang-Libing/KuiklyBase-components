package com.tencent.qqlive.kmm.vbpbservice.impl.internal


/**
 * Copyright (c) 2024 Tencent. All rights reserved
 *
 * PB请求网络协议类型
 *
 * @author haibarawang
 * @date 2024/3/1 19:41
 */
enum class VBPBProtocolType {
    // QUIC协议(会根据底层QUIC探测成功与否自动降级走HTTP协议)
    QUIC,

    // HTTP协议
    HTTP,

    // 强制QUIC协议(不用等探测QUIC连通性结果)
    FORCEQUIC,
}
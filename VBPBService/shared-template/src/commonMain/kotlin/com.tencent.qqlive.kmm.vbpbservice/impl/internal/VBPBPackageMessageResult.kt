package com.tencent.qqlive.kmm.vbpbservice.impl.internal

import com.tencent.qqlive.protocol.vb.pb.kmm.RequestHead

/**
 * Copyright (c) 2024 Tencent. All rights reserved
 *
 * PB Message 打包结果
 *
 * @author haibarawang
 * @date 2024/3/1 19:41
 */
class VBPBPackageMessageResult {
    lateinit var packageBytes: ByteArray // 打包数据结果
    lateinit var requestHead: RequestHead // Header
}
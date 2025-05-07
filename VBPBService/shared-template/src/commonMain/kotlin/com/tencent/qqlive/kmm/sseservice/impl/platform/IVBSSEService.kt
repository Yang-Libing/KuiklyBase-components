package com.tencent.qqlive.kmm.sseservice.impl.platform

import com.tencent.qqlive.kmm.sseservice.export.IVBSSEListener
import com.tencent.qqlive.kmm.sseservice.export.VBSSERequest

/**
 * Copyright (c) 2025 Tencent. All rights reserved
 *
 * 跨端 SSE 传输服务接口
 *
 * @author berryyang
 * @date 2025/4/15 18:53
 */
interface IVBSSEService {
    fun send(request: VBSSERequest, listener: IVBSSEListener)
    fun cancel(requestId: Int)
}

expect fun getVBSSEService(): IVBSSEService
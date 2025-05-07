package com.tencent.qqlive.kmm.sseservice.export

/**
 * Copyright (c) 2025 Tencent. All rights reserved
 *
 * SSE 网络响应监听接口
 *
 * @author berryyang
 * @date 2025/4/15 19:06
 */
interface IVBSSEListener {
    fun onOpen()

    fun onEvent(id: String?, event: String?, data: String)

    fun onClosed()

    fun onFailure(code: Int, message: String)
}
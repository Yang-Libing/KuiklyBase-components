package com.tencent.qqlive.kmm.vbpbservice.impl.internal

import com.squareup.wire.kmm.Message

/**
 * Copyright (c) 2024 Tencent. All rights reserved
 *
 * PB 请求任务生命周期接口
 *
 * @author haibarawang
 * @date 2024/3/1 19:41
 */
internal interface IVBPBTaskListener {

    /**
     * 任务执行开始
     *
     * @param task 网络任务实例
     */
    fun onTaskBegin(task: VBPBTask<out Message<*, *>, out Message<*, *>>)

    /**
     * 任务执行结束
     *
     * @param requestId 请求任务id
     */
    fun onTaskFinish(requestId: Int)
}
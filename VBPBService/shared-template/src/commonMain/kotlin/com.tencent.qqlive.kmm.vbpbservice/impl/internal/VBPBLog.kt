package com.tencent.qqlive.kmm.vbpbservice.impl.internal

import com.tencent.qqlive.kmm.vbpbservice.export.IVBPBLog

/**
 * Copyright (c) 2024 Tencent. All rights reserved
 *
 * PB 日志代理
 *
 * @author haibarawang
 * @date 2024/3/1 19:41
 */
object VBPBLog {
    private const val SERVICE_NAME = "NXNetwork_"
    const val HEADER_CONFIG = SERVICE_NAME + "HeaderConfig"
    const val MESSAGE_PACKAGE = SERVICE_NAME + "MessagePackage"
    const val HEADER_PACKAGE = SERVICE_NAME + "HeaderPackage"
    const val PBFRAME_PACKAGE = SERVICE_NAME + "PBFramePackage"
    const val PACKAGE_IMPL = SERVICE_NAME + "PBPackageImpl"
    const val PACKAGE_TASK = SERVICE_NAME + "Task"
    const val TASK_MANAGER = SERVICE_NAME + "TaskManager"
    const val INIT_TASK = SERVICE_NAME + "InitTask"
    const val SENDER = SERVICE_NAME + "Sender"
    const val TIMEOUT = SERVICE_NAME + "Timeout"
    const val HMTRANSPORTIMPL = SERVICE_NAME + "HmTransportImpl"
    const val SSE_TASK = SERVICE_NAME + "SSE_TASK"
    const val SSE_TASK_MANAGER = SERVICE_NAME + "SSE_TaskManager"

    var logImpl: IVBPBLog? = null

    fun i(tag: String?, content: String?) {
        logImpl?.i("[$tag]", content)
    }

    fun e(tag: String?, content: String?, throwable: Throwable? = null) {
        logImpl?.e("[$tag]", content, throwable)
    }
}

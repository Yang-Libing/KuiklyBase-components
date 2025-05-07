package com.tencent.qqlive.kmm.vbpbservice.service

import com.squareup.wire.kmm.Message
import com.tencent.qqlive.kmm.vbpbservice.export.VBPBAutoRetryFlag
import com.tencent.qqlive.kmm.vbpbservice.export.VBPBCompletionHandler
import com.tencent.qqlive.kmm.vbpbservice.export.VBPBRequest
import com.tencent.qqlive.kmm.vbpbservice.impl.internal.VBPBConfig
import com.tencent.qqlive.kmm.vbpbservice.impl.internal.VBPBLog
import com.tencent.qqlive.kmm.vbpbservice.impl.internal.VBPBPackage
import com.tencent.qqlive.kmm.vbpbservice.impl.internal.VBPBReportManager
import com.tencent.qqlive.kmm.vbpbservice.impl.internal.VBPBRequestIdGenerator
import com.tencent.qqlive.kmm.vbpbservice.impl.internal.VBPBTask
import com.tencent.qqlive.kmm.vbpbservice.impl.internal.VBPBTaskManager
import com.tencent.qqlive.kmm.vbpbservice.impl.internal.VBTransportState
import com.tencent.qqlive.kmm.vbpbservice.impl.internal.getTimestamp
import kotlin.experimental.ExperimentalObjCName
import kotlin.native.ObjCName

/**
 * Copyright (c) 2024 Tencent. All rights reserved
 *
 * PB 请求服务
 *
 * @author haibarawang
 * @date 2024/3/1 19:41
 */
@OptIn(ExperimentalObjCName::class)
@ObjCName("VBPBService")
object VBPBService {

    /**
     * 发送请求
     *
     * @param request 请求信息对象
     * @param succHandler 请求成功处理函数
     * @param failHandler 请求失败处理函数
     * @return 请求id
     */
    @ObjCName("send")
    fun <R : Message<*, *>, T : Message<*, *>> send(
        request: VBPBRequest<R, T>,
        succHandler: VBPBCompletionHandler<R, T>?,
        failHandler: VBPBCompletionHandler<R, T>?
    ): Int {
        val startTs = getTimestamp()
        // 获取当前类日志Tag
        var requestId = request.requestId
        if (requestId == 0) { // 如果 requestId 为 0，那么需要生成一个，否则使用外部传入的值
            requestId = VBPBRequestIdGenerator.getRequestId()
            request.requestId = requestId
        }
        // 注入主端统一干预逻辑(域名调度/Quic调度)
        VBPBConfig.onInterceptRequestConfig(request.func, request.requestConfig)
        val autoRetryFlag = request.requestConfig.autoRetryFlag
        val logTag =
            "[${request.requestMessage::class.simpleName ?: "none"}" +
                    "-$requestId${if (autoRetryFlag != VBPBAutoRetryFlag.NO_RETRY) "-${autoRetryFlag}" else ""}]"
        // 上报采集
        VBPBReportManager.addReportInfo(requestId, logTag, startTs)
        // 构造打包对象
        val packageImpl = VBPBPackage<R, T>(request.responseAdapter, logTag)
        // 构造请求任务
        val task = VBPBTask(requestId, request, packageImpl, succHandler, failHandler, logTag)
        // 执行请求任务
        VBPBTaskManager.execute(task)
        VBPBLog.i(VBPBLog.SENDER, "$logTag pbservice send() send request, id:$requestId")
        return requestId
    }

    /**
     * 取消请求
     *
     * @param requestId 待取消的请求id
     */
    @ObjCName("cancel")
    fun cancel(requestId: Int) {
        VBPBTaskManager.cancel(requestId)
    }

    /**
     *  获取http请求状态
     * @param requestId 请求编号
     * @return 请求状态
     *         Create - 已创建
     *         Running - 正在执行
     *         Canceled - 已取消
     *         Done - 已完成
     *         Unknown - 已取消后删除
     */
    @ObjCName("getState")
    fun getState(requestId: Int): VBTransportState = VBPBTaskManager.getState(requestId)
}
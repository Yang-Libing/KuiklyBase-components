package com.tencent.tmm.pbservice

import com.squareup.wire.kmm.Message
import com.tencent.qqlive.modules.vb.pbkmm.export.*
import com.tencent.qqlive.modules.vb.pbkmm.service.IVBPBService
import com.tencent.raft.raftframework.RAFT
import com.tencent.tmm.pbservice.export.VBPBDataTypeTMM
import com.tencent.tmm.pbservice.export.VBPBProtocolTypeTMM
import com.tencent.tmm.pbservice.export.VBPBRequestConfigTMM
import com.tencent.tmm.pbservice.export.VBPBRequestTMM
import com.tencent.tmm.pbservice.export.VBPBResponseTMM
import java.util.concurrent.locks.ReentrantLock

object VBPBServiceImplTMM : VBPBServiceTMM {
    /**
     * 底层的VBPBSender单例
     */
    private val pbAndService by lazy {
        RAFT.get(IVBPBService::class.java)
    }

    /**
     * 存储listener的map
     */
    private val listenerMap = HashMap<Long, Any>()

    /**
     * 读写锁
     */
    private val lock = ReentrantLock()

    override fun <R : Message<*, *>?, T : Message<*, *>?> sendRequest(
        request: R,
        config: VBPBRequestConfigTMM<T>,
        callee: String,
        func: String,
        successHandler: SuccessHandler<R,T>?,
        failHandler: FailHandler<R,T>?
    ): Long {
        val listener: IVBPBExtendListener<R, T> = generateListener(successHandler, failHandler)
        val pbConfig: VBPBRequestConfig = covertToConfigAndroid(config)
        checkNotNull(config.kotlinResponseClass) { "config.kotlinResponseClass == null" }
        val requestId = pbAndService.send(request, callee, func, config.kotlinResponseClass!!, pbConfig, listener).toLong()
        cacheListener(requestId, listener)
        return requestId
    }

    override fun cancel(requestId: Long) {
        pbAndService.cancel(requestId.toInt())
    }

    private fun <R : Message<*, *>?, T : Message<*, *>?> covertToConfigAndroid(config: VBPBRequestConfigTMM<T>): VBPBRequestConfig {
        return VBPBRequestConfig().apply {
            this.domain = config.domain
            this.url = config.url
            this.httpHeaderMap = config.httpHeaderMap
            this.extraDataMap = config.extraDataMap
            this.pbDataType = generateVBPBDataType(config.pbDataType)
            this.isRetryEnable = config.isRetryEnable
            this.pageParams = extraPageParams(config.pageParams)
            this.protocolType = generateVBPBProtocolType(config.protocolType)
            this.isResponseEmptyAllowed = config.responseEmptyAllowed
            this.tryUseCellularNetwork = config.isTryUseCellularNetwork
            this.scene = config.scene
            this.customTraceMap = config.customTraceMap
            this.isQuicUseConnAndSend = config.quicUseConnAndSend
            this.isQuicForceQuic = config.quicForceQuic
            this.isEnableServerCurrentLimit = config.enableServerCurrentLimit
            this.cacheTimeStamp = config.cacheTimeStamp
            this.isEnhanceThreadPriority = config.enhanceThreadPriority
        }
    }

    private fun generateVBPBProtocolType(protocolType: VBPBProtocolTypeTMM): VBPBProtocolType {
        return when (protocolType) {
            VBPBProtocolTypeTMM.HTTP -> VBPBProtocolType.HTTP
            VBPBProtocolTypeTMM.QUIC -> VBPBProtocolType.QUIC
        }
    }

    private fun generateVBPBDataType(pbDataType: VBPBDataTypeTMM): VBPBDataType {
        return when (pbDataType) {
            VBPBDataTypeTMM.DEFT -> VBPBDataType.DEF
            VBPBDataTypeTMM.QMF -> VBPBDataType.QMF
            else -> VBPBDataType.TRPC
        }
    }

    private fun <R : Message<*, *>?, T : Message<*, *>?> generateListener(
        successHandle: SuccessHandler<R,T>?,
        failHandle: FailHandler<R,T>?
    ): IVBPBExtendListener<R, T> {

        val listener = object :
            IVBPBExtendListener<R, T> {
            override fun onSuccess(
                request: VBPBRequest<R>?,
                response: VBPBResponse<T?>
            ) {
                var requestTMM =
                    this@VBPBServiceImplTMM.covertToVBPBRequestTMM(request)
                var responseTMM =
                    this@VBPBServiceImplTMM.covertToVBPBResponseTMM(response)
                successHandle?.invoke(requestTMM, responseTMM)
                clearListenerMap(request)
            }

            override fun onFailure(
                request: VBPBRequest<R>?,
                response: VBPBResponse<T?>
            ) {
                var requestTMM =
                    this@VBPBServiceImplTMM.covertToVBPBRequestTMM(request)
                var responseTMM: VBPBResponseTMM<T> =
                    this@VBPBServiceImplTMM.covertToVBPBResponseTMM(response)
                failHandle?.invoke(requestTMM, responseTMM)
                clearListenerMap(request)
            }
        }
        return listener
    }

    private fun <R : Message<*, *>?> covertToVBPBRequestTMM(request: VBPBRequest<R>?): VBPBRequestTMM<R> {
        val requestId: Long = request?.requestId?.toLong() ?: 0
        return VBPBRequestTMM(requestId, null)
    }

    private fun <T : Message<*, *>?> covertToVBPBResponseTMM(response: VBPBResponse<T?>?): VBPBResponseTMM<T> {
        val error: Long? = response?.errorCode?.toLong()
        return VBPBResponseTMM(error, null, response?.responseMessage)
    }

    private fun extraPageParams(originParams: Map<String, String>?): Map<String, String> {
        var pageParamsMap: HashMap<String, String> = HashMap()
        // 标记请求来自KMM
        pageParamsMap["fromKMM"] = "1"
        if (originParams != null) {
            pageParamsMap.putAll(originParams)
        }
        return pageParamsMap
    }

    private fun cacheListener(identifier: Long, listener: Any) {
        lock.lock()
        listenerMap[identifier] = listener
        lock.unlock()
    }

    private fun getListener(requestId: Long): Any? {
        var listener: Any? = null
        lock.lock()
        listener = listenerMap[requestId]
        lock.unlock()
        return listener
    }

    private fun <R : Message<*, *>?> clearListenerMap(request: VBPBRequest<R>?) {
        val requestId: Long? = request?.requestId?.toLong()
        if (request == null) {
            return
        }
        lock.lock()
        if (listenerMap.containsKey(requestId)) {
            listenerMap.remove(requestId)
        }
        lock.unlock()
    }
}
package com.tencent.qqlive.kmm.vbpbservice.export

import co.touchlab.stately.collections.ConcurrentMutableList
import okio.ByteString
import kotlin.experimental.ExperimentalObjCName
import kotlin.native.ObjCName

@OptIn(ExperimentalObjCName::class)
@ObjCName("VBPBTransInfoManager")
object VBPBTransInfoManager {
    private val transInfoListeners: ConcurrentMutableList<IVBPBTransInfoListener> = ConcurrentMutableList()

    @ObjCName("registerTransInfoListener")
    fun registerTransInfoListener(listener: IVBPBTransInfoListener) {
        if (transInfoListeners.contains(listener)) {
            return
        }
        transInfoListeners.add(listener)
    }

    @ObjCName("unregisterTransInfoListener")
    fun unregisterTransInfoListener(listener: IVBPBTransInfoListener) {
        if (!transInfoListeners.contains(listener)) {
            return
        }
        transInfoListeners.remove(listener)
    }

    @ObjCName("onReqTransInfo")
    fun onReqTransInfo(requestInfo: VBPBRequestInfo, reqTransInfo: MutableMap<String, ByteString>) {
        transInfoListeners.forEach {
            it.onReqTransInfo(requestInfo, reqTransInfo)
        }
    }

    @ObjCName("onRspTransInfo")
    fun onRspTransInfo(requestInfo: VBPBRequestInfo, reqTransInfo: MutableMap<String, ByteString>?) {
        transInfoListeners.forEach {
            it.onRspTransInfo(requestInfo, reqTransInfo)
        }
    }

}
package com.tencent.qqlive.kmm.vbpbservice.export

import okio.ByteString
import kotlin.experimental.ExperimentalObjCName
import kotlin.native.ObjCName

@OptIn(ExperimentalObjCName::class)
@ObjCName("IVBPBTransInfoListener")
interface IVBPBTransInfoListener {
    @ObjCName("onReqTransInfo")
    fun onReqTransInfo(requestInfo: VBPBRequestInfo, reqTransInfo: MutableMap<String, ByteString>)
    @ObjCName("onRspTransInfo")
    fun onRspTransInfo(requestInfo: VBPBRequestInfo, rspTransInfo: MutableMap<String, ByteString>?)
}
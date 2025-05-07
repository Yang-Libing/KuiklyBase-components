package com.tencent.qqlive.kmm.vbpbservice.export

import kotlin.experimental.ExperimentalObjCName
import kotlin.native.ObjCName

@OptIn(ExperimentalObjCName::class)
@ObjCName("VBPBRequestInfo")
class VBPBRequestInfo {
    @ObjCName("callee")
    var callee: String = ""
    @ObjCName("func")
    var func: String = ""
    @ObjCName("uniqueId")
    var uniqueId: String = ""
}
package com.tencent.qqlive.kmm.vbpbservice.export

import com.squareup.wire.kmm.Message
import kotlin.experimental.ExperimentalObjCName
import kotlin.native.ObjCName

@OptIn(ExperimentalObjCName::class)
@ObjCName("IVBPBQUICConfig")
interface IVBPBQUICConfig {

    @ObjCName("pb_shouldTryToUseQUIC")
    fun <REQUEST : Message<*, *>, RESPONSE : Message<*, *>>shouldTryToUseQUIC(
        request: VBPBRequest<REQUEST, RESPONSE>
    ): Boolean

}
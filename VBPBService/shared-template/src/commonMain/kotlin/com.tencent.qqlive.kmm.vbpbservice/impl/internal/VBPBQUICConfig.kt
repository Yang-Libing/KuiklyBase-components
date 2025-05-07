package com.tencent.qqlive.kmm.vbpbservice.impl.internal

import com.squareup.wire.kmm.Message
import com.tencent.qqlive.kmm.vbpbservice.export.IVBPBQUICConfig
import com.tencent.qqlive.kmm.vbpbservice.export.VBPBRequest

object VBPBQUICConfig {

    var quicConfig: IVBPBQUICConfig? = null

    fun <REQUEST : Message<*, *>, RESPONSE : Message<*, *>>shouldTryToUseQUIC(request: VBPBRequest<REQUEST, RESPONSE>): Boolean {
        val config = quicConfig ?: return false
        return config.shouldTryToUseQUIC(request)
    }

}
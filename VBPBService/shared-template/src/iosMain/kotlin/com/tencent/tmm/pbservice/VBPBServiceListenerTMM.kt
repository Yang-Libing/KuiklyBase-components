package com.tencent.tmm.pbservice

import com.squareup.wire.kmm.Message

open class TMMListenerModel {

    var request: Any? = null
    var response: Any? = null

    companion object {
        open fun <R : Message<*, *>?, T : Message<*, *>?> generateListenerModel(
            request: R,
            response: T
        ): TMMListenerModel {
            val model = TMMListenerModel()
            model.request = request
            model.response = response
            return model
        }
    }
}
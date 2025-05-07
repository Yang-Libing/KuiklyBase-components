package com.tencent.qqlive.kmm.vbpbservice.export

interface IVBPBRequestIdGenerator {

    /**
     * 获取请求 id
     */
    fun getRequestId(): Int

}
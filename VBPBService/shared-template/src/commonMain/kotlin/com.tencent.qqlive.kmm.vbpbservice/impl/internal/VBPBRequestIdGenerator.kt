package com.tencent.qqlive.kmm.vbpbservice.impl.internal

import co.touchlab.stately.concurrency.AtomicInt
import com.tencent.qqlive.kmm.vbpbservice.export.IVBPBRequestIdGenerator
import kotlin.experimental.ExperimentalObjCName
import kotlin.native.ObjCName

/**
 * 请求 id 生成
 */
@OptIn(ExperimentalObjCName::class)
object VBPBRequestIdGenerator {

    var requestIdGenerator: IVBPBRequestIdGenerator? = null

    private var requestId: AtomicInt = AtomicInt(0)


    /**
     * 创建请求id
     *
     * @return 自增请求id
     */
    @ObjCName("pb_getRequestId")
    fun getRequestId(): Int {
        return requestIdGenerator?.getRequestId() ?: run {
            if (requestId.get() == Int.MAX_VALUE) {
                requestId.set(0)
            }
            requestId.incrementAndGet()
        }
    }

}
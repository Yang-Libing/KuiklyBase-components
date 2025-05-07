package com.tencent.qqlive.kmm.vbpbservice.impl.internal

import com.tencent.qqlive.kmm.vbpbservice.export.IVBPBAutoRetry
import com.tencent.qqlive.kmm.vbpbservice.export.VBPBAutoRetryFailureRequestParams
import com.tencent.qqlive.kmm.vbpbservice.export.VBPBAutoRetryStrategy
import com.tencent.qqlive.kmm.vbpbservice.export.VBPBAutoRetrySuccessRequestParams

/**
 * Copyright (c) 2024 Tencent. All rights reserved.
 * 类功能描述:重试策略代理
 *
 * @author woodyjjli
 * @date 2024/9/2
 */
object VBPBAutoRetry {

    var autoRetry: IVBPBAutoRetry? = null

    fun getEnabled(): Boolean = (autoRetry?.getEnabled() == true)

    fun onRequestSuc(pbAutoRetryRequestParams: VBPBAutoRetrySuccessRequestParams) {
        autoRetry?.onRequestSuc(pbAutoRetryRequestParams)
    }

    fun onRequestFail(
        pbAutoRetryRequestParams: VBPBAutoRetryFailureRequestParams
    ): VBPBAutoRetryStrategy? = autoRetry?.onRequestFail(pbAutoRetryRequestParams)
}
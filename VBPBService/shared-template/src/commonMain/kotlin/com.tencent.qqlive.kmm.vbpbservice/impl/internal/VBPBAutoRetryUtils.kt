package com.tencent.qqlive.kmm.vbpbservice.impl.internal

import com.tencent.qqlive.kmm.vbpbservice.export.VBPBAutoRetryErrorCategory
import com.tencent.qqlive.kmm.vbpbservice.export.VBPBResultCode
import com.tencent.qqlive.kmm.vbpbservice.export.VBPBResultCodeType


/**
 * Copyright (c) 2024 Tencent. All rights reserved.
 * 类功能描述: 自动重试工具类
 *
 * @author woodyjjli
 * @date 2024/9/2
 */
object VBPBAutoRetryUtils {

    private val canRetryNetErrorTypes = setOf(VBPBResultCodeType.ERROR_CODE_TYPE_NET, VBPBResultCodeType.ERROR_CODE_TYPE_HTTP)
    private val canRetryBizErrorTypes = setOf(VBPBResultCodeType.ERROR_CODE_TYPE_ACCESS_SVR_ERR, VBPBResultCodeType.ERROR_CODE_TYPE_BUSI_RS_ERR)
    fun canRetry(errorCode: Int, errorType: String): Boolean {
        if (errorCode == VBPBResultCode.CODE_OK) { // 外部逻辑错误
            return false
        }
        return canRetryNetErrorTypes.contains(errorType) || canRetryBizErrorTypes.contains(errorType)
    }

    /**
     * 获取自动重试错误类型
     * 如果pb层成功，进行解包，这里的resultCode和businessErrorCode都会传业务返回的值，即解包的值
     */
    fun getAutoRetryErrorCategory(errorCode: Int, errorType: String): VBPBAutoRetryErrorCategory {
        if (canRetryNetErrorTypes.contains(errorType)) {
            return VBPBAutoRetryErrorCategory.NET
        }
        if (canRetryBizErrorTypes.contains(errorType)) {
            return VBPBAutoRetryErrorCategory.BIZ
        }
        return VBPBAutoRetryErrorCategory.UNKNOWN
    }

    /**
     * 获取pageParams中加的pageType标示信息
     *
     * @return pageType标示信息
     */
     fun getAutoRetryPageType(pageParams: Map<String, String>?): String? {
        if (pageParams.isNullOrEmpty()) {
            return null
        }
        val autoRetryPageType = pageParams[EXTRA_REQUEST_HEAD_PAGE_TYPE]
        return if (autoRetryPageType.isNullOrEmpty()) null else autoRetryPageType
    }

}
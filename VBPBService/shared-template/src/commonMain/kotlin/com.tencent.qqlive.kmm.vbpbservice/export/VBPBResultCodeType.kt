package com.tencent.qqlive.kmm.vbpbservice.export

object VBPBResultCodeType {
    // 网络产生的错误
    const val ERROR_CODE_TYPE_NET = "001"

    // HTTP产生的错误
    const val ERROR_CODE_TYPE_HTTP = "002"

    // 接入层产生的错误
    const val ERROR_CODE_TYPE_ACCESS_SVR_ERR = "003"

    // 具体业务后台返回的错误,需要联系具体业务后台查询
    const val ERROR_CODE_TYPE_BUSI_RS_ERR = "004"

    // QMF格式协议解包错误（当前 PB 不会使用）
    const val ERROR_CODE_TYPE_QMF_PACK_ERR = "005"

    // Fame格式协议解包错误
    const val ERROR_CODE_TYPE_FRAME_PACK_ERR = "006"

    // Trpc格式协议解包错误
    const val ERROR_CODE_TYPE_TRPC_PACK_ERR = "007"

    // PB/JCE解包错误,联系对应业务后台
    const val ERROR_CODE_TYPE_BUSI_PACK_ERR = "008"

    // 网络库本身的错误码
    const val ERROR_CODE_TYPE_NET_LIB_ERR = "009"

    // 后端返回的数据与业务期望不符合
    const val ERROR_CODE_TYPE_CLIENT_ERR = "0010"
}
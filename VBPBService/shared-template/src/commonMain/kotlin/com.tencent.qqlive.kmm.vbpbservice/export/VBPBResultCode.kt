package com.tencent.qqlive.kmm.vbpbservice.export

/**
 * Copyright (c) 2024 Tencent. All rights reserved
 *
 * PB 结果码
 *
 * @author haibarawang
 * @date 2024/3/1 19:41
 */
object VBPBResultCode {
    // 正常
    const val CODE_OK = 0
    // 无网
    const val NETWORK_DISCONNECT = -800
    // 魔数不一致
    const val CODE_PBERR_PB_FRAME_MAGIC_DISMATCH = -852
    // qmf包数据为空
    const val CODE_PBERR_EXCLUDE_QMF_DATA_EMPTY = -861
    // CMD不一致
    const val CODE_PBERR_QMF_CMD_MISMATCH = -851
    // 回包包体为空
    const val CODE_HTTP_ENTITYNULL = -840
    // pb 数据非gzip压缩
    const val CODE_PBERR_NOGZIPERR = -867
    // pb 的外层包裹数据出错
    const val CODE_PBERR_EXT_ERR = -869
    // pb 数据为空
    const val CODE_PBERR_QMFDATAERR = -868
    // 解压失败
    const val CODE_UNZIPERR = -871
    // pb包体解析失败
    const val CODE_PB_ERR_BODY = -862
    // 客户端错误，请检查请求的callee和func。出现该问题时，请求还未发出
    const val CODE_PBERR_ROUTEERR = -864
    // pb 请求对象为空时的ReuqestId
    const val REQUEST_ERROR_ID = -1
    // pb 请求对象为空
    const val CODE_REQUEST_DATA_EMPTY = -1000
    // Response 解析时byte[]为空
    const val ERR_RESPONSE_PARSE_DATA_EMPTY = -1001
    // Response 解析器为空
    const val ERR_RESPONSE_PARSE_PARSER_EMPTY = -1002
    // Response 解析时异常
    const val ERR_RESPONSE_PARSE_EXCEPTION = -1003
    // qmf 魔数不一致
    const val ERR_RESPONSE_QMF_MAGIC_MISMATCH = -1004
    // qmf appid不一致
    const val ERR_RESPONSE_QMF_APPID_MISMATCH = -1005
    // trpc-head中request_id空
    const val ERR_RESPONSE_REQUEST_ID_NULL = -1006
    // trpc-head中request_id不一致
    const val ERR_RESPONSE_REQUEST_ID_CHANGE = -1007
    // 不支持的压缩算法
    const val ERR_RESPONSE_UNSUPPORTED_COMPRESS_TYPE = -1008
    // 解压trpc-body失败
    const val ERR_RESPONSE_UNCOMPRESS_FAIL = -1009
    // 任务已被取消
    const val CODE_CANCELED = -10001
    // 后台在qmf 头里填的总包长度跟终端收到的长度不一致
    const val CODE_PBERR_QMF_LEN_MISMATCH_RECEIVED_LEN = -850
    // 接入层请求限流处理的异常code
    const val CODE_REQUEST_LIMIT = -1010
}
package com.tencent.qqlive.kmm.vbpbservice.export


/**
 * 网络请求传输相关信息
 *
 * https://doc.weixin.qq.com/sheet/e3_AGEAaAYvACocIRRYcehS0qmAMf5pO?scode=AJEAIQdfAAoa0Qrw6EAGIAYwaOACc&tab=BB08J2
 */
data class VBTransportReportInfo(
    var errorCode: String? = null,
    var errorMessage: String? = null,
    var errorType: String? = null,
    var url: String? = null,
    var domain: String? = null,
    var totalCost: String? = null,
    var dnsCost: String? = null,
    var connCost: String? = null,
    var sslCost: String? = null,
    var queueCost: String? = null,
    var sendCost: String? = null,
    // 首字节接收耗时,统计的是: 发送完毕到首字节返回耗时
    var firstByteCost: String? = null,
    // 新版首字节接收耗时,统计的是: 数据发送耗时 和 数据发送完毕到首字节返回耗时 之和
    // 主要用于 libcurl 请求场景
    var firstByteCostNew: String? = null,
    var recvCost: String? = null,
    var preTransferTime: String? = null,
    var redirectTime: String? = null,
    var transProtocol: String? = null,
    var transProtocolStrategy: String? = null,
    var transDegradationType: String? = null,
    var reusedConnection: String? = null,
    var localNetStackType: String? = null,
    var useNetCardType: String? = null,
    var respContentType: String? = null,
    var httpStatusCode: String? = null,
    var isHttps: String? = null,
    var httpVer: String? = null,
    var tlsVer: String? = null,
    var targetIp: String? = null,
    var ipType: String? = null,
    var upstreamSize: String? = null,
    var downstreamSize: String? = null,
    var requestBodySize: String? = null,
    var responseBodySize: String? = null,
    var useQuicClient: String? = null,
    var disableQuicReason: String? = null,
    var serverIP: String? = null,
    var serverPort: String? = null,
    var useCurl: String? = "-1",
)
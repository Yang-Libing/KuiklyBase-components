package com.tencent.qqlive.kmm.vbpbservice.export

import com.tencent.qqlive.kmm.vbpbservice.impl.internal.VBPBProtocolType
import kotlin.experimental.ExperimentalObjCName
import kotlin.native.ObjCName

/**
 * Copyright (c) 2024 Tencent. All rights reserved
 *
 * PB请求附加配置參數
 *
 * @author haibarawang
 * @date 2024/3/1 19:41
 */
@OptIn(ExperimentalObjCName::class)
@ObjCName("VBPBRequestConfig")
class VBPBRequestConfig {

    // 请求域名
    @ObjCName("domain")
    var domain: String? = null

    // 请求完整URL（在请求根域名时可不填）
    @ObjCName("url")
    var url: String = ""

    // 总超时时长(单位毫秒)，该设置优先于 下方单个的链接/读写/DNS超时
    @ObjCName("totalTimeout")
    var totalTimeout = 0L

    // 链接超时(单位毫秒)
    @ObjCName("connTimeOut")
    var connTimeOut = 0

    // Socket读超时(单位毫秒)
    @ObjCName("readTimeOut")
    var readTimeOut = 0

    // Socket写超时(单位毫秒)
    @ObjCName("writeTimeOut")
    var writeTimeOut = 0

    // DNS超时(单位毫秒)
    @ObjCName("dnsTimeOut")
    var dnsTimeOut = 0

    // PB协议RequestHeader.extra_request_head添加数据
    @ObjCName("extraDataMap")
    var extraDataMap: Map<String?, String>? = null

    // 添加Http协议的header数据
    @ObjCName("httpHeaderMap")
    var httpHeaderMap: Map<String, String>? = null

    // 页面参数
    @ObjCName("pageParams")
    var pageParams: Map<String, String>? = null

    // 是否尝试使用移动蜂窝网络(用于wifi和移动网络同时可用时优先使用移动网络场景)
    @ObjCName("isTryUseCellularNetwork")
    var isTryUseCellularNetwork = false

    // 选择通讯协议，默认HTTP
    @ObjCName("protocolType")
    var protocolType: VBPBProtocolType = VBPBProtocolType.HTTP

    // 接口标识，用于使用了 callee 和 func 依旧不好区分接口的场景
    @ObjCName("serviceTag")
    var serviceTag: String? = null

    // 业务自定义的trace上报字段，在染色用户触发天机阁上报时会携带
    @ObjCName("customTraceMap")
    var customTraceMap: Map<String, String>? = null

    // 是否取消流水自动上报
    @ObjCName("isReportDisable")
    var isReportDisable = false

    // 该业务场景的缓存时间戳
    @ObjCName("cacheTimeStamp")
    var cacheTimeStamp: Long = 0

    // 当协议使用http时，强制走80端口，不加密
    @ObjCName("httpUseHttp1")
    var httpUseHttp1 = false

    // 本次请求在建联时优先使用ipv4，仅个别特殊场景使用
    @ObjCName("ipv4First")
    var ipv4First = false

    // 提升本请求的线程优先级，一般用于CPU高负载场景提升请求线程被调度的优先级
    @ObjCName("enhanceThreadPriority")
    var enhanceThreadPriority = false

    // 是否是https
    @ObjCName("isHttps")
    var isHttps = true

    // 重试标记
    // 注意：一般情况下，业务无需使用该字段
    @ObjCName("autoRetryFlag")
    var autoRetryFlag: VBPBAutoRetryFlag = VBPBAutoRetryFlag.NO_RETRY

    // 使用quic时，是否使用一次性收发特殊接口，仅用于小数据包且耗时极其敏感场景
    @ObjCName("quicUseConnAndSend")
    var quicUseConnAndSend = false

    // 当协议使用quic时，是否强制走quic，而不依赖quic探测
    @ObjCName("quicForceQuic")
    var quicForceQuic = false

    // 是否支持后台限流
    @ObjCName("enableServerCurrentLimit")
    var enableServerCurrentLimit = false
}
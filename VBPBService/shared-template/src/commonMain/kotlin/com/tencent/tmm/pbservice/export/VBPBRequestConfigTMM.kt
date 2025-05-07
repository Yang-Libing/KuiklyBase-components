package com.tencent.tmm.pbservice.export

import com.squareup.wire.kmm.Message

enum class VBPBDataTypeTMM {
    DEFT, QMF, TRPC
}

enum class VBPBProtocolTypeTMM {
    // 走QUIC协议(会根据底层QUIC探测成功与否自动降级走HTTP协议)
    QUIC,
    // 走HTTP协议
    HTTP
}

class VBPBRequestConfigTMM<T : Message<*, *>?> {
    var domain: String? = null  // 请求域名
    var url: String? = null // 请求完整URL（在请求根域名时可不填）
    var pbDataType: VBPBDataTypeTMM = VBPBDataTypeTMM.DEFT  // 数据协议类型，0：默认，1：旧qmf，2：新trpc
    var connTimeOut: Double = 0.0  // 链接超时(单位秒)
    var readTimeOut: Double = 0.0   // Socket读超时(单位秒)
    var writeTimeOut: Double = 0.0 // Socket写超时(单位秒)
    var DNSTimeOut: Double = 0.0   // DNS超时(单位秒)
    var isRetryEnable: Boolean = true  // 是否自动重试(默认开启)
    var extraDataMap: Map<String, String>? = null  // PB协议RequestHeader.extra_request_head添加数据
    var httpHeaderMap: Map<String, String>? = null  // 添加Http协议的header数据
    var pageParams: Map<String, String>? = null    // 页面参数
    var responseEmptyAllowed: Boolean = false       // 是否允许响应数据包为空 如果响应包除Qmf帧头、PB帧头外没有业务数据响应数据，请设置为true，默认false
    var isTryUseCellularNetwork: Boolean = false     // 是否尝试使用移动蜂窝网络(用于wifi和移动网络同时可用时优先使用移动网络场景)
    var protocolType: VBPBProtocolTypeTMM = VBPBProtocolTypeTMM.HTTP // 选择通讯协议，默认HTTP
    var scene: String? = null                       // 网络限流场景信息
    var customTraceMap: Map<String, String>? = null // 业务自定义的trace上报字段，在染色用户触发天机阁上报时会携带
    var quicUseConnAndSend: Boolean = false           // 使用quic时，是否使用一次性收发特殊接口，仅用于小数据包且耗时极其敏感场景
    var quicForceQuic: Boolean = false                // 当协议使用quic时，是否强制走quic，而不依赖quic探测
    var enableServerCurrentLimit: Boolean = false    // 是否支持后台限流
    var cacheTimeStamp: Long = 0                    // 该业务场景的缓存时间戳
    var enhanceThreadPriority: Boolean = false      // android：提高本次请求执行线程的优先级，仅适用于个别耗时敏感场景
    var responseClassNameFromKMM: String? = null    // iOS专用，响应的Kotlin协议的ClassName
    var kotlinResponseClass: T? = null              // KMM专用，响应的Kotlin协议的ClassName

    // iOS的特有属性
    var serviceTag: String? = null       // 基于callee+func的pb协议无法区分具体页面场景, 用于为业务定制核心下钻的页面场景
    var verboseTrace: Boolean = false    // 是否开启详细的打点数据
    var cacheId: String? = null          // Cache的唯一ID,业务需要自己定义
    var enablePreCache: Boolean = false  // Cache的唯一ID,业务需要自己定义
    var cacheDuration: Int = 0           // 前置Cache的缓存时长
}

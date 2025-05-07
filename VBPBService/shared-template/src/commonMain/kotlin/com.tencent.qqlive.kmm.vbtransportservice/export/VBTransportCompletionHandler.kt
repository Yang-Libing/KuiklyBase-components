package com.tencent.qqlive.kmm.vbtransportservice.export

/**
 * Copyright (c) 2024 Tencent. All rights reserved.
 *
 * 网络请求完成后的处理器类型
 *
 * @author haibarawang
 * @date 2024/3/1
 */
typealias VBTransportStringCompletionHandler = (response: VBTransportStringResponse) -> Unit
typealias VBTransportBytesCompletionHandler = (response: VBTransportBytesResponse) -> Unit
typealias VBTransportPostHandler = (response: VBTransportPostResponse) -> Unit
typealias VBTransportGetHandler = (response: VBTransportGetResponse) -> Unit

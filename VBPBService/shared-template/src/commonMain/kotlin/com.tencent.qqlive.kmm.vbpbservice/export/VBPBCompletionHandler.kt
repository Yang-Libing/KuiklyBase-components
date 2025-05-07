package com.tencent.qqlive.kmm.vbpbservice.export

/**
 * Copyright (c) 2024 Tencent. All rights reserved.
 *
 * 网络请求完成后的处理器类型
 *
 * @author haibarawang
 * @date 2024/3/1
 */
typealias VBPBCompletionHandler<R, T> = (request: VBPBRequest<R, T>, response: VBPBResponse<T>) -> Unit

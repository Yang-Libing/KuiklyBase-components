/*
 * Tencent is pleased to support the open source community by making KuiklyBase available.
 * Copyright (C) 2025 THL A29 Limited, a Tencent company. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.tencent.kmm.network.export

/**
 * Copyright (c) 2024 Tencent. All rights reserved
 *
 * 通用请求 结果码
 *
 * @author haibarawang
 * @date 2024/3/1 19:41
 */
object VBTransportResultCode {

    const val CODE_OK = 0
    // 任务已被取消
    const val CODE_CANCELED = -10001
    // 强制超时
    const val CODE_FORCE_TIMEOUT = -2001
}
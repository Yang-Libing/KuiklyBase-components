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

import kotlin.experimental.ExperimentalObjCName
import kotlin.native.ObjCName

@OptIn(ExperimentalObjCName::class)
@ObjCName("IVBPBLog")
interface IVBPBLog {

    /**
     * Debug 级别日志输出
     *
     * @param tag     tag
     * @param content 日志内容
     */
    @ObjCName("pb_d")
    fun d(tag: String?, content: String?)

    /**
     * Info 级别日志输出
     *
     * @param tag     tag
     * @param content 日志内容
     */
    @ObjCName("pb_i")
    fun i(tag: String?, content: String?)

    /**
     * Error 级别日志输出
     *
     * @param tag     tag
     * @param content 日志内容
     */
    @ObjCName("pb_e")
    fun e(tag: String?, content: String?, throwable: Throwable? = null)

}
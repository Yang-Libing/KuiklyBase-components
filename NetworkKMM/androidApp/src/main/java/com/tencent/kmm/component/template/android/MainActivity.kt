/*
 * Tencent is pleased to support the open source community by making KuiklyBase available.
 * Copyright (C) 2025 Tencent. All rights reserved.
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
package com.tencent.kmm.component.template.android

import android.app.Activity
import android.os.Bundle
import android.view.View
import com.tencent.kmm.network.service.VBTransportServiceTest

class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Get请求测试
        findViewById<View>(R.id.requestWithGet).setOnClickListener {
            VBTransportServiceTest.testSendGetRequest()
        }

        // Post请求测试
        findViewById<View>(R.id.requestWithPost).setOnClickListener {
            VBTransportServiceTest.testSendPostRequest()
        }

        // Bytes请求测试
        findViewById<View>(R.id.requestWithBytes).setOnClickListener {
            VBTransportServiceTest.testSendByteRequest()
        }

        // String请求测试
        findViewById<View>(R.id.requestWithString).setOnClickListener {
            VBTransportServiceTest.testSendStringRequest()
        }
    }
}

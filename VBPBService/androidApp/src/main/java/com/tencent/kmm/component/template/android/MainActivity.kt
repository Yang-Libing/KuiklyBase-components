package com.tencent.kmm.component.template.android

import android.app.Activity
import android.os.Bundle
import android.view.View
import com.tencent.qqlive.kmm.sseservice.VBSSEServiceTest
import com.tencent.qqlive.kmm.vbpbservice.export.IVBPBAutoRetry
import com.tencent.qqlive.kmm.vbpbservice.export.IVBPBReport
import com.tencent.qqlive.kmm.vbpbservice.export.IVBPBTransInfoListener
import com.tencent.qqlive.kmm.vbpbservice.export.VBPBAutoRetryFailureRequestParams
import com.tencent.qqlive.kmm.vbpbservice.export.VBPBAutoRetryStrategy
import com.tencent.qqlive.kmm.vbpbservice.export.VBPBAutoRetrySuccessRequestParams
import com.tencent.qqlive.kmm.vbpbservice.export.VBPBInitConfig
import com.tencent.qqlive.kmm.vbpbservice.export.VBPBRequestInfo
import com.tencent.qqlive.kmm.vbpbservice.export.VBPBTransInfoManager
import com.tencent.qqlive.kmm.vbpbservice.service.VBPBServiceInitHelper
import com.tencent.qqlive.kmm.vbpbservice.service.VBPBServiceTest
import com.tencent.qqlive.kmm.vbtransportservice.service.VBTransportServiceTest
import okio.ByteString
import okio.ByteString.Companion.encodeUtf8

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 注入自定义transinfo信息
        VBPBTransInfoManager.registerTransInfoListener(object : IVBPBTransInfoListener {
            override fun onReqTransInfo(
                requestInfo: VBPBRequestInfo,
                reqTransInfo: MutableMap<String, ByteString>
            ) {
                reqTransInfo["req_test_key"] = "req_test_value".encodeUtf8()
            }

            override fun onRspTransInfo(
                requestInfo: VBPBRequestInfo,
                rspTransInfo: MutableMap<String, ByteString>?
            ) {
                rspTransInfo?.let {
                    it["req_test_key"] = "req_test_value".encodeUtf8()
                }
            }
        })

        // PB 请求测试
        findViewById<View>(R.id.requestWithPB).setOnClickListener {
            VBPBServiceTest.testSendRequest()
        }

        // Get请求测试
        findViewById<View>(R.id.requestWithGet).setOnClickListener {
            VBTransportServiceTest.testSendGetRequest()
        }

        // Post请求测试
        findViewById<View>(R.id.requestWithPost).setOnClickListener {
            VBTransportServiceTest.testSendPostRequest()
        }

        // 重定向请求测试
        findViewById<View>(R.id.requestWithRedirect).setOnClickListener {
            VBTransportServiceTest.testSend302Request()
        }

        // 重试请求初始化
        findViewById<View>(R.id.initAutoRetry).setOnClickListener {
            val config = VBPBInitConfig().apply {
                autoRetry = object : IVBPBAutoRetry {
                    override fun getEnabled(): Boolean = true

                    override fun onRequestSuc(pbAutoRetryRequestParams: VBPBAutoRetrySuccessRequestParams) {
                    }

                    override fun onRequestFail(
                        pbAutoRetryRequestParams: VBPBAutoRetryFailureRequestParams
                    ): VBPBAutoRetryStrategy? = VBPBAutoRetryStrategy(canRetry = true)

                }
                reportImpl = object : IVBPBReport {

                    override fun reportSampleRate(cmd: String, isSuccess: Boolean): Int =
                        if (isSuccess) 1 else 1

                    override fun report(reportInfo: Map<String, String>) {
                        reportInfo.forEach {
                            println("reportImpl: $it")
                        }
                    }

                }
            }
            VBPBServiceInitHelper.init(config)
        }

        // SSE 请求测试
        findViewById<View>(R.id.requestWithSSE).setOnClickListener {
            VBSSEServiceTest.testSSEService()
        }
    }
}

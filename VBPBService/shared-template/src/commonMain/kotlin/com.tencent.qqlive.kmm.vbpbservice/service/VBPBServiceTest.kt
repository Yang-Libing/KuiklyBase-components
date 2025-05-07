package com.tencent.qqlive.kmm.vbpbservice.service

import com.tencent.qqlive.kmm.vbpbservice.export.IVBPBLog
import com.tencent.qqlive.kmm.vbpbservice.export.VBPBRequest
import trpc.ias.accessDispQuery.kmm.DispatchRequest
import trpc.ias.accessDispQuery.kmm.DispatchResponse
import kotlin.experimental.ExperimentalObjCName
import kotlin.native.ObjCName

@OptIn(ExperimentalObjCName::class)
@ObjCName("VBPBServiceTest")
object VBPBServiceTest {

    // TODO 跨端测试方法，仅用于网络库自己测试
    @ObjCName("testSendRequest")
    fun testSendRequest(
        logTag: String = "testSendPbRequest",
        useCurl: Boolean = false
    ): Int {

        val domains: MutableList<String> = ArrayList()
        domains.add("a.video.qq.com")

        // 请求对象拼装
        val pbRequest = VBPBRequest(
            DispatchRequest(
                appKey = "tencentVideo.VBPBService",
                uuid = "e206381a-2d5b-49e2-8d91-de066ce9d169",
                dispUnits = domains
            ),
            DispatchResponse.ADAPTER,
            "trpc.ias.accessDispQuery.DispServiceV1",
            "/trpc.ias.accessDispQuery.DispServiceV1/dispatch"
        )

        pbRequest.useCurl = useCurl

        // 发起请求
        return VBPBService.send(
            // 请求参数(必填)
            pbRequest,
            // 成功回调(非必填)
            succHandler = { request, response ->
                println("[${logTag}] OnSuccess, response message:${response.message}")
            },
            // 失败回调(非必填)
            failHandler = { request, response ->
                println("[${logTag}] OnFailure, error code:${response.errorCode}, " +
                        "error message:${response.errorMessage}")
            })
    }

    fun testCancle(requestId: Int) {
        VBPBService.cancel(requestId)
    }

    @ObjCName("testServiceInit")
    fun testServiceInit() {
        val logImpl = object : IVBPBLog {

            override fun d(tag: String?, content: String?) {
                print("[$tag] $content\n")
            }

            override fun i(tag: String?, content: String?) {
                print("[$tag] $content\n")
            }

            override fun e(tag: String?, content: String?, throwable: Throwable?) {
                print("[$tag] $content\n")
            }
        }
        VBPBServiceInitHelper.debugInit(logImpl)
    }
}
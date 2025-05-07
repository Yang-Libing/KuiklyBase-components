package com.tencent.qqlive.kmm.sseservice.impl.platform

import com.tencent.qqlive.kmm.sseservice.VBSSEService
import com.tencent.qqlive.kmm.sseservice.export.IVBSSEListener
import com.tencent.qqlive.kmm.sseservice.export.VBSSERequest
import com.tencent.qqlive.kmm.vbpbservice.impl.internal.VBPBLog
import com.tencent.qqlive.protocol.vb.pb.kmm.LoginToken
import com.tencent.qqlive.protocol.vb.pb.kmm.RequestHead
import com.tencent.qqlive.protocol.vb.pb.kmm.VersionInfo
import com.tencent.tmm.platform_utils.TextManager
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.buildJsonObject

object VBSSEServiceTest {

    private fun buildRequestHeaders(): Map<String, String> {
        val loginTokenList = mutableListOf<LoginToken>()
        val loginToken = LoginToken(
            is_main_login = true,
            type = 9,
            account = "12345678"
        )
        loginTokenList.add(loginToken)
        // 拼接 HTTP 请求头
        val requestHead = RequestHead(
            request_id = 100,
            callee = "trpc.videosearch.assistant.http",
            func = "/chat",
            login_token = loginTokenList,
            version_info = VersionInfo(app_id = "1000005"),
            extra_request_head = mutableMapOf("QIMEI36" to "765a906821ee0af2b281b30710001d014911")
        )
        val headBytes: ByteArray = requestHead.encode()
        // 公参信息转Base64(后台要求的)
        val qqliveHeadBase64String = TextManager.byteArrayToBase64(headBytes)
        // 特性分支信息
        val envInfoBase64String = TextManager.byteArrayToBase64("bb917fd5".encodeToByteArray())
        // 自定义公参信息
        return mapOf(
            "trpc-trans-info" to buildJsonObject {
                put("qqlive_head", JsonPrimitive(qqliveHeadBase64String))
                put("trpc-env", JsonPrimitive(envInfoBase64String))
            }.toString()
        )
    }

    fun test() {
        val sseRequest = VBSSERequest()
        sseRequest.logTag = "SSETest"
        sseRequest.url = "https://ovb-galaxy-sse.testsite.woa.com/chat"
        sseRequest.headers = buildRequestHeaders()
        /*
            CopilotSSERequest(
                scene_type = CopilotSceneType.Copilot_SCENE_TYPE_INPUT_DIALOGUE,
                input = "请给我推荐电影"
            ).encode()
         */
        sseRequest.data = byteArrayOf(8, 1, 18, 21, -24, -81, -73, -25, -69, -103, -26, -120, -111, -26, -114, -88, -24, -115, -112, -25, -108, -75, -27, -67, -79)
        val listener = object : IVBSSEListener {
            override fun onOpen() {
                VBPBLog.i("[SSE]", "onOpen()")
            }

            override fun onEvent(id: String?, event: String?, data: String) {
                VBPBLog.i("[SSE]", "onEvent() id:$id, event:$event, data:$data")
            }

            override fun onClosed() {
                VBPBLog.i("[SSE]", "onClosed()")
            }

            override fun onFailure(code: Int, message: String) {
                VBPBLog.i("[SSE]", "onFailure() code:$code, message:$message")
            }
        }
        VBSSEService.send(sseRequest, listener)
    }
}

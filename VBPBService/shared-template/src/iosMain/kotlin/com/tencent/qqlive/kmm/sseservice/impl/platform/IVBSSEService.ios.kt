package com.tencent.qqlive.kmm.sseservice.impl.platform

import com.tencent.qqlive.kmm.sseservice.export.IVBSSEListener
import com.tencent.qqlive.kmm.sseservice.export.VBSSERequest
import cocoapods.VBTransportServiceiOS.TVSSESession
import cocoapods.VBTransportServiceiOS.TVSSESessionConfig
import cocoapods.VBTransportServiceiOS.TVSSESessionDelegateProtocol
import com.tencent.tmm.pbservice.toNSData
import kotlinx.cinterop.ExperimentalForeignApi
import platform.Foundation.NSError
import platform.Foundation.NSURL
import platform.darwin.NSInteger
import platform.darwin.NSObject
import kotlinx.atomicfu.atomic
import kotlinx.atomicfu.getAndUpdate
import kotlinx.atomicfu.update

//TODO: 线程安全
@OptIn(ExperimentalForeignApi::class)
object SSEServiceIOS : IVBSSEService {
//    private var sessions = mutableMapOf<Int, TVSSESession>()
    private var sessions = mutableMapOf<Int, SessionContext>()

    private data class SessionContext(
        val session: TVSSESession,
        var delegate: TVSSESessionDelegateProtocol
    )

    override fun send(request: VBSSERequest, listener: IVBSSEListener) {
        if (sessions.containsKey(request.requestId)) {
            print("Session with requestId ${request.requestId} already exists")
            return
        }

        val config = TVSSESessionConfig(NSURL(string = request.url)).apply {
            method = "POST"
            headers = request.headers.mapKeys { it.key as Any? }
            body = request.data.toNSData()
            detailMode = false
        }

        val delegate = object : TVSSESessionDelegateProtocol, NSObject() {
            override fun onOpened() {
                listener.onOpen()
            }

            override fun onError(error: NSError) {
                val errorCode = error.code.toInt()
                val errorMessage = error.localizedDescription
                listener.onFailure(errorCode, errorMessage)
            }

            override fun onEventWithID(eventID: String?, eventType: String?, data: String?) {
                listener.onEvent(id = eventID, event = eventType, data = data ?: "null")
            }

            override fun onClosedWithReason(reason: NSInteger) {
                listener.onClosed()
                sessions.remove(request.requestId)
                print("SSESession close for reason : $reason")
            }
        }

        val session = TVSSESession.sessionWithConfig(config, delegate)

        val context = SessionContext(session, delegate)
        sessions[request.requestId] = context
//        sessions[request.requestId] = session
        session.start()
    }

    override fun cancel(requestId: Int) {
//        sessions[requestId]?.stop()
        sessions[requestId]?.session?.stop()
    }
}

actual fun getVBSSEService(): IVBSSEService {
    return  SSEServiceIOS
}
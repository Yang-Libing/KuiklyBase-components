package com.tencent.qqlive.kmm.vbpbservice.impl.internal

import com.ditchoom.buffer.PlatformBuffer
import com.ditchoom.buffer.allocate
import com.ditchoom.buffer.wrap
import com.tencent.qqlive.kmm.vbpbservice.export.VBPBResultCode

/**
 * Copyright (c) 2024 Tencent. All rights reserved
 *
 * PB帧协议打包/解包实现 长度:16B
 *
 * @author haibarawang
 * @date 2024/3/1 19:41
 */
class VBPBFramePackage(
    private val logTag: String,
) {
    var responseHeadLen = 0 // 响应头长度
    var responseBodyLen = 0 // 响应体长度

    // 对pb请求添加pb协议
    fun packagePBRequest(headerLen: Short, requestDataBytes: ByteArray): ByteArray {
        val totalLen = getPBRequestPackageLen(requestDataBytes.size)
        logI("package pb frame, total size:$totalLen")
        val pbFrame = createPBFrame(headerLen, totalLen)
        return packagePBFrame(pbFrame, requestDataBytes, totalLen)
    }

    // 获取pb请求加上pb协议后的长度
    private fun getPBRequestPackageLen(dataBytesLen: Int): Int = PB_FRAME_LEN + dataBytesLen

    // 构造pb协议
    private fun createPBFrame(headLen: Short, totalLen: Int): ByteArray {
        // 16B
        val byteBuffer: PlatformBuffer = PlatformBuffer.allocate(16)
        // 2B 魔数
        byteBuffer.writeShort(MAGIC_PB_REQUEST_MAGIC.toShort(16))
        // 1B
        byteBuffer.writeByte(0.toByte())
        // 1B
        byteBuffer.writeByte(0.toByte())
        // 4B 总长
        byteBuffer.writeInt(totalLen)
        // 2B 头长
        byteBuffer.writeShort(headLen)
        // 2B
        byteBuffer.writeShort(0.toShort())
        // 4B
        byteBuffer.writeInt(0)
        byteBuffer.position(0)
        return byteBuffer.readByteArray(16)
    }

    // 将业务数据加上PB协议帧
    private fun packagePBFrame(
        pbFrame: ByteArray,
        requestBytes: ByteArray,
        totalLen: Int
    ): ByteArray {
        val byteBuffer: PlatformBuffer = PlatformBuffer.allocate(totalLen)
        byteBuffer.writeBytes(pbFrame)
        byteBuffer.writeBytes(requestBytes)
        byteBuffer.position(0)
        return byteBuffer.readByteArray(totalLen)
    }

    // 对pb响应解除pb协议
    fun unpackagePBResponse(responseBytes: ByteArray): VBPBUnPackageResult {
        val responseBytesSize = responseBytes.size
        logI("unpackage pb frame, total size:${responseBytesSize}")
        val unPackageResult = VBPBUnPackageResult()
        val responseBuffer = PlatformBuffer.wrap(responseBytes)
        // 2B 响应魔数校对
        val pbMagicFrame = responseBuffer.readShort().toString()
        if (MAGIC_PB_RESPONSE_MAGIC != pbMagicFrame) {
            logI("unpackage pb frame fail, magic:${pbMagicFrame} is incorrect")
            unPackageResult.errorCode = VBPBResultCode.CODE_PBERR_PB_FRAME_MAGIC_DISMATCH
            return unPackageResult
        }
        // 跳过2B
        responseBuffer.readByte()
        responseBuffer.readByte()
        // 获取总长度  16B帧头 + TRPC回包头[长度] + 业务数据[长度]
        val totalLen = responseBuffer.readInt()
        // 防止异常数据，如果后台指定响应长度比实际返回数据长度要大，视为异常数据，正常情况应该相等
        if (totalLen > responseBytesSize) {
            logI("unpackage pb frame fail, read totalLen:$totalLen > response size: $responseBytesSize")
            unPackageResult.errorCode = VBPBResultCode.CODE_PB_ERR_BODY
            return unPackageResult
        }
        // TRPC回包头长度
        responseHeadLen = responseBuffer.readShort().toInt()
        // 业务数据长度
        responseBodyLen = totalLen - responseHeadLen - PB_FRAME_LEN
        // 跳过2B
        responseBuffer.readShort()
        // 跳过4B
        responseBuffer.readInt()
        // 指定位置到16B，跳过PB Frame
        responseBuffer.position(PB_FRAME_LEN)
        // 读取公共包头 + 业务的字节数组
        val resultBytes = responseBuffer.readByteArray(totalLen - PB_FRAME_LEN)
        // 获取除去 PB Frame 后的业务数据
        val resultBuffer = PlatformBuffer.allocate(totalLen - PB_FRAME_LEN)
        resultBuffer.writeBytes(resultBytes)
        resultBuffer.position(0)
        // 返回对象
        unPackageResult.businessCode = VBPBResultCode.CODE_OK
        unPackageResult.messageBytes = resultBuffer.readByteArray(resultBuffer.capacity);
        return unPackageResult
    }

    private fun logI(content: String) {
        VBPBLog.i(VBPBLog.PBFRAME_PACKAGE, "$logTag $content")
    }

    companion object {
        const val PB_FRAME_LEN = 16 // PB 协议长度 16B
        const val MAGIC_PB_REQUEST_MAGIC = "930" // PB协议请求魔数
        const val MAGIC_PB_RESPONSE_MAGIC = "2352" // PB协议响应魔数
    }
}

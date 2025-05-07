package com.tencent.qqlive.kmm.vbpbservice.impl.internal

import okio.Buffer
import okio.GzipSink

object GzipUtils {
    fun gzipCompress(input: ByteArray): ByteArray {
        val buffer = Buffer()
        val gzipSink = GzipSink(buffer)

        // 将输入数据写入 GzipSink
        gzipSink.write(Buffer().write(input), input.size.toLong())
        gzipSink.close() // 关闭 GzipSink，确保所有数据都被写入

        return buffer.readByteArray() // 返回压缩后的 ByteArray
    }
}
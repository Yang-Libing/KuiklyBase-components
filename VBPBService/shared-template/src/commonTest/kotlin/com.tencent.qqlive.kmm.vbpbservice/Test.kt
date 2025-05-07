package com.tencent.qqlive.kmm.vbpbservice

import com.ditchoom.buffer.Charset
import com.ditchoom.buffer.PlatformBuffer
import com.ditchoom.buffer.WriteBuffer
import com.ditchoom.buffer.allocate
import com.ditchoom.buffer.wrap
import com.tencent.qqlive.kmm.vbpbservice.export.VBPBRequest
import com.tencent.qqlive.kmm.vbpbservice.impl.internal.encodeToJsonString
import com.tencent.qqlive.kmm.vbpbservice.impl.internal.parseToJsonObject
import com.tencent.qqlive.kmm.vbpbservice.service.VBPBService
import com.tencent.trpc.proto.standard.common.vbpb.kmm.ResponseProtocol
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import kotlinx.serialization.json.JsonPrimitive
import okio.ByteString
import okio.ByteString.Companion.encodeUtf8
import trpc.ias.accessDispQuery.kmm.DispatchRequest
import trpc.ias.accessDispQuery.kmm.DispatchResponse
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test

class CommonGreetingTest {

    private lateinit var scope: CoroutineScope

    private val byteArrTestData = byteArrayOf(
        0,
        1,
        2,
        3,
        1,
        1,
        0,
        0,
        0,
        9,
        0,
        0,
        0,
        8,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        12,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        13,
        0,
        0,
        0,
        1,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        2,
        0,
        0,
        0,
        1
    )

    @BeforeTest
    fun before() {
        scope = CoroutineScope(EmptyCoroutineContext)
    }

    @AfterTest
    fun after() {
        scope.cancel()
    }

    @Test
    fun testSendRequest() {
        val domains: MutableList<String> = ArrayList<String>()
        domains.add("acc.qq.com")

        // 请求对象拼装
        val pbRequest = VBPBRequest<DispatchRequest, DispatchResponse>(
            DispatchRequest(
                appKey = "tencentVideo.VBPBService",
                uuid = "9df1c7aa-6d80-4a5b-890f-c9d2d358f070",
                dispUnits = domains
            ),
            DispatchResponse.ADAPTER,
            "trpc.ias.accessDispQuery.DispServiceV1",
            "/trpc.ias.accessDispQuery.DispServiceV1/dispatch"
        )

        // 发起请求
        val requestId = VBPBService.send(
            // 请求参数(必填)
            pbRequest,
            // 成功回调(非必填)
            succHandler = { request, response ->
                println("OnSuccess, response message:${response.message}")
            },
            // 失败回调(非必填)
            failHandler = { request, response ->
                println("OnFailure, error code:${response.errorCode}, error message:${response.errorMessage}")
            })
    }

    @Test
    fun testByteBuffer() {
        // 根据已知创建
        val byteArray = byteArrayOf(1, 2, 3, 4)
        var buffer = PlatformBuffer.wrap(byteArray)

        // 创建空
        buffer = PlatformBuffer.allocate(100)

        // 顺序写数据，自动向后拼接
        buffer.writeByte(5.toByte())
        buffer.writeUByte(5.toUByte())

        buffer.writeShort(5.toShort())
        buffer.writeUShort(5.toUShort())

        buffer.writeInt(5)
        buffer.writeUInt(5.toUInt())

        buffer.writeLong(5.toLong())
        buffer.writeULong(5.toULong())

        buffer.writeFloat(123.456f)
        buffer.writeDouble(123.456)

        buffer.writeString("5", Charset.UTF8)

        val otherBuffer = PlatformBuffer.wrap(byteArrayOf(1, 2, 3, 4))
        buffer.write(otherBuffer)

        buffer.writeBytes(byteArrayOf(1, 2, 3, 4))
        buffer.writeBytes(byteArrayOf(1, 2, 3, 4), 2, 2)
        println(buffer)

        // 指定位置写字节数据
        val writeBuffer: WriteBuffer = PlatformBuffer.allocate(10)
        writeBuffer[9] = 9.toByte()
        writeBuffer[8] = 8.toUByte()
        writeBuffer[6] = 7.toShort()
        writeBuffer[4] = 5.toUShort()
        writeBuffer[0] = 1
        writeBuffer[0] = 128
        println(writeBuffer)

        // 顺序读字节数据 read 相当于自动后移指针
        var readBuffer: PlatformBuffer =
        // 测试数据                                   b  b      s      s                i              i                             l                               l             f                       d          i
        PlatformBuffer.wrap(byteArrTestData)
        val byte = readBuffer.readByte()
        val uByte = readBuffer.readUnsignedByte()
        val short = readBuffer.readShort()
        val uShort = readBuffer.readUnsignedShort()
        val int = readBuffer.readInt()
        val uInt = readBuffer.readUnsignedInt()
        val long = readBuffer.readLong()
        val uLong = readBuffer.readUnsignedLong()
        val float = readBuffer.readFloat()
        val double = readBuffer.readDouble()
        val intEnd = readBuffer.readInt()

        // 指定位置读数据  get 相当于指定指针位置
        readBuffer =
        // 测试数据                                   b  b      s      s                i              i                             l                               l             f                       d          i
        PlatformBuffer.wrap(byteArrTestData)
        val byteWithIndex = readBuffer.get(0)
        val uByteWithIndex = readBuffer.getUnsignedByte(1)
        val shortWithIndex = readBuffer.getShort(2)
        val uShortWithIndex = readBuffer.getUnsignedShort(4)
        val intWithIndex = readBuffer.getInt(6)
        val uIntWithIndex = readBuffer.getUnsignedInt(10)
        val longWithIndex = readBuffer.getLong(14)
        val uLongWithIndex = readBuffer.getUnsignedLong(22)
        val floatWithIndex = readBuffer.getFloat(26)
        val doubleWithIndex = readBuffer.getDouble(34)
        val intEndWithIndex = readBuffer.getInt(42)
        println(readBuffer)
    }

    @Test
    fun testByteString() {
        val call: ByteString = "com.tencent.qqlive.protocol.pb.EmptyService".encodeUtf8()
        println("ByteString:$call")
    }

    @Test
    fun testPlatformBuffer2ByteArray() {
        val byteArray = byteArrayOf(1, 2, 3, 4)
        var buffer = PlatformBuffer.wrap(byteArray)
        val convertByteArray = buffer.readByteArray(buffer.capacity)
        println("convertByteArray:$convertByteArray")
    }

    @Test
    fun testStringToShort() {
        val MAGIC_PB_FRAME = "930"
        val magicFrameShort = MAGIC_PB_FRAME.toShort(16)
        // 和Java的(short) Integer.parseInt(MAGIC_PB_FRAME, 16)对比是否相同，java的结果是2352
        println("magic frame short :$magicFrameShort")
    }

    @Test
    fun testPlatformBufferToByteArray() {
        val bytes1 = byteArrayOf(1)
        val buffer1 = PlatformBuffer.wrap(bytes1)

        val bytes2 = byteArrayOf(2)
        val buffer2 = PlatformBuffer.wrap(bytes2)

        val byteBuffer: PlatformBuffer = PlatformBuffer.allocate(2)

        byteBuffer.writeBytes(buffer1.readByteArray(buffer1.capacity))
        byteBuffer.writeBytes(buffer2.readByteArray(buffer2.capacity))
        byteBuffer.position(0)
        val readByteArray = byteBuffer.readByteArray(2)

        println("convert buffer to bytearray :$readByteArray")
    }

    @Test
    fun testUnpackage() {
        val responseBytes = byteArrayOf(
            9,
            48,
            0,
            0,
            0,
            0,
            1,
            124,
            0,
            41,
            0,
            0,
            0,
            0,
            0,
            0,
            24,
            1,
            66,
            37,
            10,
            15,
            113,
            113,
            108,
            105,
            118,
            101,
            95,
            114,
            115,
            112,
            95,
            104,
            101,
            97,
            100,
            18,
            18,
            74,
            16,
            8,
            2,
            24,
            -99,
            -102,
            -49,
            -71,
            -29,
            49,
            32,
            -97,
            -102,
            -49,
            -71,
            -29,
            49,
            18,
            -118,
            2,
            10,
            10,
            97,
            99,
            99,
            46,
            113,
            113,
            46,
            99,
            111,
            109,
            18,
            -5,
            1,
            26,
            65,
            50,
            53,
            52,
            48,
            54,
            95,
            104,
            83,
            100,
            75,
            115,
            80,
            90,
            55,
            71,
            121,
            69,
            78,
            54,
            98,
            115,
            106,
            81,
            120,
            120,
            70,
            104,
            65,
            61,
            61,
            95,
            50,
            47,
            50,
            53,
            52,
            48,
            54,
            95,
            104,
            83,
            100,
            75,
            115,
            80,
            90,
            55,
            71,
            121,
            69,
            78,
            54,
            98,
            115,
            106,
            81,
            120,
            120,
            70,
            104,
            65,
            61,
            61,
            95,
            50,
            34,
            10,
            97,
            99,
            99,
            46,
            113,
            113,
            46,
            99,
            111,
            109,
            42,
            21,
            10,
            15,
            49,
            49,
            54,
            46,
            49,
            51,
            48,
            46,
            50,
            50,
            56,
            46,
            49,
            57,
            49,
            16,
            1,
            24,
            80,
            42,
            21,
            10,
            15,
            49,
            49,
            54,
            46,
            49,
            51,
            48,
            46,
            50,
            50,
            56,
            46,
            49,
            57,
            50,
            16,
            1,
            24,
            80,
            42,
            19,
            10,
            13,
            49,
            49,
            50,
            46,
            56,
            48,
            46,
            49,
            56,
            51,
            46,
            53,
            50,
            16,
            2,
            24,
            80,
            42,
            19,
            10,
            13,
            49,
            50,
            50,
            46,
            49,
            57,
            51,
            46,
            55,
            46,
            49,
            56,
            51,
            16,
            2,
            24,
            80,
            50,
            26,
            10,
            20,
            50,
            52,
            48,
            56,
            58,
            56,
            55,
            49,
            49,
            58,
            49,
            48,
            58,
            49,
            48,
            53,
            58,
            58,
            49,
            101,
            16,
            1,
            24,
            80,
            50,
            26,
            10,
            20,
            50,
            52,
            48,
            56,
            58,
            56,
            55,
            51,
            100,
            58,
            97,
            48,
            48,
            58,
            49,
            53,
            58,
            58,
            50,
            51,
            16,
            2,
            24,
            80,
            50,
            26,
            10,
            20,
            50,
            52,
            48,
            56,
            58,
            56,
            55,
            51,
            100,
            58,
            97,
            48,
            48,
            58,
            49,
            49,
            58,
            58,
            53,
            102,
            16,
            2,
            24,
            80,
            26,
            30,
            -28,
            -72,
            -83,
            -27,
            -101,
            -67,
            35,
            -27,
            -116,
            -105,
            -28,
            -70,
            -84,
            -27,
            -72,
            -126,
            35,
            -28,
            -72,
            -83,
            -27,
            -101,
            -67,
            -24,
            -127,
            -108,
            -23,
            -128,
            -102,
            35,
            34,
            20,
            10,
            2,
            105,
            112,
            18,
            14,
            49,
            49,
            49,
            46,
            50,
            48,
            54,
            46,
            57,
            52,
            46,
            49,
            52,
            57
        )

        val responseBuffer = PlatformBuffer.wrap(responseBytes)
        // magic
        val magicPBFrameInit = responseBuffer.readShort().toString()
        println("is magic pb frame ok:${"2352" == magicPBFrameInit}")
        // 跳过2B
        responseBuffer.readByte()
        responseBuffer.readByte()
        // 获取总长度
        val totalLen = responseBuffer.readInt();
        println("total len:${totalLen}")
        // Header长度
        val headLen = responseBuffer.readShort()
        val bodyLen = totalLen - headLen - 16
        println("head len:${headLen}, bodyLen:${bodyLen}")
        // 跳过2B
        responseBuffer.readShort()
        // 跳过4B
        responseBuffer.readInt()
        responseBuffer.position(16)
        val resultBytes = responseBuffer.readByteArray(totalLen - 16)
        println("result bytes len:${resultBytes.size}")

        // ---- 进行Header解析 ------
        val busiBuffer = PlatformBuffer.wrap(resultBytes)
        val headerBytes = busiBuffer.readByteArray(headLen.toInt())
        println("header bytes len:${headerBytes.size}")

        // 反序列化Header
        val trpcHeader = ResponseProtocol.ADAPTER.decode(headerBytes)
        println("trpc header:${trpcHeader.toString()}")

        // 反序列化Body
        val busiBody = busiBuffer.readByteArray(bodyLen)
        val dispatchResponse = DispatchResponse.ADAPTER.decode(busiBody)
        println("busi body:${dispatchResponse.toString()}")
    }

    @Test
    fun testJson2Map() {
        val mapString = "{\"test_key\":\"test_value\"}"
        val map = try {
            val jsonObj = mapString.parseToJsonObject()
            mutableMapOf<String, String>().also { map ->
                jsonObj?.entries?.forEach { entry ->
                    map[entry.key] = entry.value.toString()
                }
            }
        } catch (e: Throwable) {
            emptyMap()
        }
        println(map)
    }

    @Test
    fun testMap2Json() {
        val testmap = mutableMapOf(
            "test_key1" to JsonPrimitive("test_value_1"),
            "test_key2" to JsonPrimitive("test_value_2"),
            "test_key3" to JsonPrimitive("test_value_3")
        )
        val jsonStr = testmap.encodeToJsonString()
        print(jsonStr)
    }

}
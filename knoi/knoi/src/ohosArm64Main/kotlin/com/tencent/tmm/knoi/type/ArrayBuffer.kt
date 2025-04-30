package com.tencent.tmm.knoi.type

import com.tencent.tmm.knoi.getEnv
import com.tencent.tmm.knoi.metric.trace
import kotlinx.cinterop.CPointed
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.addressOf
import kotlinx.cinterop.memScoped
import kotlinx.cinterop.reinterpret
import kotlinx.cinterop.toCValues
import kotlinx.cinterop.usePinned
import platform.ohos.knoi.createArrayBuffer
import platform.ohos.knoi.createTypedArray
import platform.ohos.knoi.getArrayBufferLength
import platform.ohos.knoi.getArrayBufferValue
import platform.ohos.knoi.getTypeArrayLength
import platform.ohos.knoi.getTypeArrayType
import platform.ohos.knoi.getTypeArrayValue
import platform.ohos.knoi.getTypedArrayItemSize
import platform.ohos.knoi.isArrayBuffer
import platform.ohos.napi_typedarray_type
import platform.ohos.napi_value
import platform.posix.memcpy
import platform.posix.uint8_tVar

class ArrayBuffer() {
    private var type = napi_typedarray_type.napi_uint8_array
    private var length = 0L
    private var jsValue: napi_value? = null

    // JS 指向的内存区域
    private var data: CPointer<uint8_tVar>? = null
    private var dataInKN: UByteArray? = null

    var handle: napi_value? = null
        get() = getOrCreateHandle()

    /**
     * 以 napi_value 构造需在 JS 线程中
     */
    constructor(handle: napi_value?) : this() {
        this.jsValue = handle
        initData()
    }

    constructor(
        data: CPointer<uint8_tVar>,
        length: Long,
        type: napi_typedarray_type = napi_typedarray_type.napi_uint8_array
    ) : this() {
        this.dataInKN = data.toByteArray(length.toInt()).asUByteArray()
        this.type = type
        this.length = length
    }

    constructor(
        data: UByteArray
    ) : this() {
        this.dataInKN = data
        this.type = napi_typedarray_type.napi_uint8_array
        this.length = data.size.toLong()
    }

    constructor(
        data: ByteArray,
    ) : this(data.toUByteArray())

    private fun getOrCreateHandle(): napi_value? {
        if (jsValue != null) {
            return jsValue
        }
        if (dataInKN == null) {
            return null
        }
        memScoped {
            val ptr = dataInKN!!.toCValues().ptr
            jsValue = if (type != napi_typedarray_type.napi_uint8_array) {
                createTypedArray(getEnv(), ptr, length, type)
            } else {
                createArrayBuffer(getEnv(), ptr, length)
            }
        }

        initData()
        return jsValue
    }

    private fun initData() {
        if (isArrayBuffer(getEnv(), jsValue)) {
            length = getArrayBufferLength(getEnv(), jsValue).toLong()
            type = napi_typedarray_type.napi_uint8_array
            data = getArrayBufferValue(getEnv(), jsValue)
        } else {
            type = getTypeArrayType(getEnv(), jsValue)
            length = getTypeArrayLength(getEnv(), jsValue).toLong()
            data = getTypeArrayValue(getEnv(), jsValue)
        }
    }

    /**
     * 获取 JS ArrayBuffer 指向的数据指针
     */
    fun <T : CPointed> getData(): CPointer<T>? {
        return data?.reinterpret()
    }

    /**
     * 获取 JS ArrayBuffer 指向的数据指针
     */
    fun getByteArray(): ByteArray {
        return trace("ArrayBuffer:getByteArray") {
            data?.toByteArray(getCount().toInt()) ?: ByteArray(getCount().toInt())
        }
    }

    fun getCount(): Long {
        return length / getTypedArrayItemSize(type)
    }
}

fun CPointer<uint8_tVar>.toByteArray(count: Int): ByteArray {
    val result = ByteArray(count)
    if (result.isEmpty()) return result
    result.usePinned {
        memcpy(it.addressOf(0), this, count.toULong())
    }
    return result
}
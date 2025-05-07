package com.tencent.qqlive.kmm.imagefetchservice.service

import com.tencent.qqlive.kmm.native.libcurl.CreateCurlClient
import com.tencent.qqlive.kmm.native.libcurl.CurlCallback
import com.tencent.qqlive.kmm.native.libcurl.CurlRequest
import com.tencent.qqlive.kmm.native.libcurl.CurlResponse
import com.tencent.qqlive.kmm.native.libcurl.DeleteCurlClient
import com.tencent.qqlive.kmm.native.libcurl.StartRequest
import com.tencent.qqlive.kmm.native.libcurl.StringDic
import com.tencent.qqlive.kmm.native.libcurl.StringPair
import com.tencent.qqlive.kmm.vbpbservice.impl.internal.VBPBLog
import kotlinx.cinterop.ByteVar
import kotlinx.cinterop.CFunction
import kotlinx.cinterop.COpaquePointer
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.CValue
import kotlinx.cinterop.MemScope
import kotlinx.cinterop.StableRef
import kotlinx.cinterop.alloc
import kotlinx.cinterop.allocArray
import kotlinx.cinterop.asStableRef
import kotlinx.cinterop.cValue
import kotlinx.cinterop.cstr
import kotlinx.cinterop.get
import kotlinx.cinterop.memScoped
import kotlinx.cinterop.nativeHeap
import kotlinx.cinterop.pointed
import kotlinx.cinterop.ptr
import kotlinx.cinterop.readBytes
import kotlinx.cinterop.staticCFunction
import kotlin.reflect.KFunction1

// 图片加载鸿蒙平台实现
object ImageFetchServiceHM : IImageFetchService {

    // String kmm -> c
    private fun toCSTR(string: String?, memScope: MemScope): CPointer<ByteVar> =
        string?.cstr?.getPointer(memScope) ?: "".cstr.getPointer(memScope)

    // Map kmm -> c
    fun toStringDic(stringPair: Map<String, String>, memScope: MemScope): CPointer<StringDic> {
        val listSize = stringPair.size
        val stringPairList = stringPair.toList()
        val stringPairsNative = memScope.allocArray<StringPair>(stringPairList.size)
        for (i in stringPairList.indices) {
            val (key, value) = stringPairList[i]
            stringPairsNative[i].first = toCSTR(key, memScope)
            stringPairsNative[i].second = toCSTR(value, memScope)
        }
        return memScope.alloc<StringDic> {
            size = listSize
            this.stringPairs = stringPairsNative
        }.ptr
    }

    fun createCurlRequest(
        url: String,
        headers: StringDic,
        memScope: MemScope
    ): CValue<CurlRequest> {
        return cValue {
            this.url = toCSTR(url, memScope) // 将 Kotlin String 转换为 C 字符串指针
            this.headers = headers.ptr
            this.timeout = 5000
        }
    }

    // 加载图片
    override fun fetchImage(options: ImageFetchOptions): ImageFetchResult {
        memScoped {
            // header kmm-> c
            val headers = toStringDic(options.header, memScope)
            // 图片加载结果 Kotlin
            var code = 0
            var byteArray: ByteArray? = null
            // 图片加载callback
            val imageFetchCallback = object : IImageFetchCallback {
                override fun onImageFetch(result: CurlResponse) {
                    print("[ImageFetchService] fetch image callback")
                    //  图片加载结果 c->kmm
                    code = result.code;
                    byteArray = result.data?.let { data ->
                        memScoped {
                            val size = result.dataLen
                            if (size > 0) data.readBytes(size) else ByteArray(0)
                        }
                    }
                    VBPBLog.i("[ImageFetchService]", "fetch image result code:" +
                            "${result.code} ,size:{${result.dataLen}}")
                }
            }
            // 图片加载
            val callbackWrapper = ImageFetchCallbackWrapper(imageFetchCallback)
            val callbackWrapperPtr = callbackWrapper.getCallbackNativePtr()
            val curlRequest = createCurlRequest(options.url, headers.pointed, memScope)
            val handle = CreateCurlClient("ImageFetchService")
            StartRequest(handle, curlRequest, callbackWrapperPtr)
            DeleteCurlClient(handle)
            callbackWrapper.release()
            return ImageFetchResult(code, byteArray)
        }
    }
}

interface IImageFetchCallback {
    fun onImageFetch(result: CurlResponse)
}

// 图片加载回调 kotlin->c
class ImageFetchCallbackWrapper(private val imageFetchCallback: IImageFetchCallback) {
    private var callbackPtr: CPointer<CFunction<(COpaquePointer?, CPointer<CurlResponse>?) -> Unit>>
    private var callbackStableRef: StableRef<KFunction1<CPointer<CurlResponse>, Unit>>
    private var callBlackNative: CurlCallback

    init {
        callbackStableRef = StableRef.create(::onImageFetch)
        callbackPtr = staticCFunction(::createStableRef)
        callBlackNative = nativeHeap.alloc()
        callBlackNative.callbackRef = callbackStableRef.asCPointer()
        callBlackNative.callback = callbackPtr
    }

    private fun onImageFetch(result: CPointer<CurlResponse>) =
        imageFetchCallback.onImageFetch(result.pointed)

    fun getCallbackNativePtr(): CPointer<CurlCallback> = callBlackNative.ptr

    fun release() {
        callbackStableRef.dispose()
    }
}

internal fun createStableRef(
    callbackRef: COpaquePointer?,
    result: CPointer<CurlResponse>?
) {
    callbackRef?.asStableRef<(CPointer<CurlResponse>?) -> Unit>()?.get()?.invoke(result)
}

actual fun getImageFetchService(): IImageFetchService = ImageFetchServiceHM
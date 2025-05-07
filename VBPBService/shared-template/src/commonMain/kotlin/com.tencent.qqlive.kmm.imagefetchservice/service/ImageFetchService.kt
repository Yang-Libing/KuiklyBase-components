package com.tencent.qqlive.kmm.imagefetchservice.service

// 图片加载可配置项
data class ImageFetchOptions(val url: String, val header: Map<String, String> = mutableMapOf())
// 图片加载结果
data class ImageFetchResult(val code: Int, val byteArray: ByteArray? = null)

// 图片加载服务
object ImageFetchService {
    // 加载图片
    suspend fun fetchImage(options: ImageFetchOptions): ImageFetchResult =
        getImageFetchService().fetchImage(options)
}

// 跨端图片加载服务接口
interface IImageFetchService {
    fun fetchImage(options: ImageFetchOptions): ImageFetchResult;
}

// 获取跨端图片加载接口
expect fun getImageFetchService(): IImageFetchService;

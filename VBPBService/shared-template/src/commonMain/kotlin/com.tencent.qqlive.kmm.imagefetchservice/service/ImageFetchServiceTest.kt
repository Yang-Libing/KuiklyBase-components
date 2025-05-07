package com.tencent.qqlive.kmm.imagefetchservice.service

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

// 图片加载测试
object ImageFetchServiceTest {
    // 388KB
//    val url = "https://tv.puui.qpic.cn/tv/0/mz_tv_image_frontend_fbd86c-1_187062212_1709005178829330_pic_1280x720/0?imageView2/format/png";
//    // 673KB
//    val url = "https://vcover-vt-pic.puui.qpic.cn/vcover_vt_pic/0/mzc00200g84o1hl1709172542707/450?imageView2/format/png";
//    // 319 KB
    val url =  "http://tv.puui.qpic.cn/tv/0/mz_tv_image_frontend_442f1e-8_125949261_1729071575050184_pic_1280x720/0?imageView2/format/webp";
//    // 3.2M
//    val url = "https://video-public-1258344701.shiply-cdn.qq.com/reshub/qqvideo_test/pag_component_test1/formal/20241204161035/Production/nature-6565499.jpg";
//    // 4.6M
//    val url =  "https://video-public-1258344701.shiply-cdn.qq.com/reshub/qqvideo_test/pag_component_test1/formal/20241204161313/Production/mountains-8451480.jpg";
//    // 12.6M
//    val url = "https://video-public-1258344701.shiply-cdn.qq.com/reshub/qqvideo_test/pag_component_test1/formal/20241204161456/Production/sunset-7760143.jpg";
//    // 14M
//    val url = "https://video-public-1258344701.shiply-cdn.qq.com/reshub/qqvideo_test/pag_component_test1/formal/20241204152942/Production/Fronalpstock_big.jpg";
//    // 47M
//    val url =  "https://video-public-1258344701.shiply-cdn.qq.com/reshub/qqvideo_test/pag_component_test1/formal/20241204163258/Production/023A7786.png";

    // 测试图片加载
    fun testImageFetch() {
        CoroutineScope(Dispatchers.Default).launch {
            // 测试header
            val headers = mutableMapOf(
                "Accept" to "image/*",
                "Content-Type" to "image/jpeg",
                "Key1" to "Value1",
                "Key2" to "Value2",
            )
            val fetchImageResult =
                ImageFetchService.fetchImage(ImageFetchOptions(url = url, header = headers))
            // 加载结果
            print("[ImageFetchService] fetch image result code:${fetchImageResult.code} size:${fetchImageResult.byteArray?.size}")
            // 保存图片加载结果到本地
            fetchImageResult.byteArray?.let {
                testSaveByteArray(it)
                print("[ImageFetchService] save bytearray done")
            }
        }
    }
}

// 测试图片保存到文件，用于本地验证图片加载结果是否正确
expect fun testSaveByteArray(data: ByteArray, imgFilename: String = "")


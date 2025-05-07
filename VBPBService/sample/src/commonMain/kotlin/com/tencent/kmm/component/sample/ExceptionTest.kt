package com.tencent.kmm.component.sample

import com.tencent.qqlive.kmm.imagefetchservice.service.ImageFetchServiceTest
import com.tencent.qqlive.kmm.vbpbservice.impl.internal.getTimestamp
import com.tencent.qqlive.kmm.vbpbservice.service.VBPBServiceTest
import com.tencent.qqlive.kmm.vbtransportservice.service.VBTransportServiceTest
import com.tencent.tmm.knoi.annotation.KNExport

@KNExport
fun testPBNetwork() {
    println("testPBNetwork" + getTimestamp())
    VBPBServiceTest.testServiceInit();
    VBPBServiceTest.testSendRequest()
}

@KNExport
fun testTransportBytesRequest() {
    println("testTransportBytesRequest")
    VBPBServiceTest.testServiceInit();
    VBTransportServiceTest.testSendByteRequest()
}

@KNExport
fun testTransportStringRequest() {
    println("testTransportStringRequest")
    VBPBServiceTest.testServiceInit();
    VBTransportServiceTest.testSendStringRequest()
}

@KNExport
fun testTransportPostRequest() {
    println("testTransportPostRequest")
    VBPBServiceTest.testServiceInit();
    VBTransportServiceTest.testSendPostRequest()
}

@KNExport
fun testTransportGetRequest() {
    println("testTransportGetRequest")
    VBPBServiceTest.testServiceInit();
    VBTransportServiceTest.testSendGetRequest()
}

@KNExport
fun testSend302Request() {
    println("testSend302Request")
    VBPBServiceTest.testServiceInit();
    VBTransportServiceTest.testSend302Request()
}

@KNExport
fun testImageFetch() {
    println("testImageFetch")
    VBPBServiceTest.testServiceInit();
    ImageFetchServiceTest.testImageFetch();
}

@KNExport
fun testSendStringRequestWithCurl() {
    println("testSendStringRequestWithCurl")
    VBPBServiceTest.testServiceInit()
    VBTransportServiceTest.testSendStringRequestWithCurl()
}

@KNExport
fun testSendByteRequestWithCurl() {
    println("testSendByteRequestWithCurl")
    VBPBServiceTest.testServiceInit()
    VBTransportServiceTest.testSendByteRequestWithCurl()
}

@KNExport
fun testSendGetRequestForByteContentTypeWithCurl() {
    println("testSendGetRequestForByteContentTypeWithCurl")
    VBPBServiceTest.testServiceInit()
    VBTransportServiceTest.testSendGetRequestForByteContentTypeWithCurl()
}

@KNExport
fun testSendGetRequestForJsonContentTypeWithCurl() {
    println("testSendGetRequestForJsonContentTypeWithCurl")
    VBPBServiceTest.testServiceInit()
    VBTransportServiceTest.testSendGetRequestForJsonContentTypeWithCurl()
}

@KNExport
fun testSendGetRequestForImageWithCurlV1() {
    println("testSendGetRequestForImageWithCurlV1")
    VBPBServiceTest.testServiceInit()
    VBTransportServiceTest.testSendGetRequestForImageWithCurlV1()
}

@KNExport
fun testSendGetRequestForImageWithCurlV2() {
    println("testSendGetRequestForImageWithCurlV2")
    VBPBServiceTest.testServiceInit()
    VBTransportServiceTest.testSendGetRequestForImageWithCurlV2()
}

@KNExport
fun testSendGetRequestForImageWithCurlV3() {
    println("testSendGetRequestForImageWithCurlV3")
    VBPBServiceTest.testServiceInit()
    VBTransportServiceTest.testSendGetRequestForImageWithCurlV3()
}

@KNExport
fun testSendGetRequestForImageWithCurlV4() {
    println("testSendGetRequestForImageWithCurlV4")
    VBPBServiceTest.testServiceInit()
    VBTransportServiceTest.testSendGetRequestForImageWithCurlV4()
}

@KNExport
fun testSend302RequestWithCurl() {
    println("testSend302RequestWithCurl")
    VBPBServiceTest.testServiceInit()
    VBTransportServiceTest.testSend302RequestWithCurl()
}

@KNExport
fun testSendPostRequestForJsonDataWithCurl() {
    println("testSendPostRequestForJsonDataWithCurl")
    VBPBServiceTest.testServiceInit()
    VBTransportServiceTest.testSendPostRequestForJsonDataWithCurl()
}

@KNExport
fun testSendPostRequestForEchoStringDataWithCurl() {
    println("testSendPostRequestForEchoStringDataWithCurl")
    VBPBServiceTest.testServiceInit()
    VBTransportServiceTest.testSendPostRequestForEchoStringDataWithCurl()
}

@KNExport
fun testSendPostRequestForByteDataWithCurl() {
    println("testSendPostRequestForByteDataWithCurl")
    VBPBServiceTest.testServiceInit()
    VBTransportServiceTest.testSendPostRequestForByteDataWithCurl()
}

@KNExport
fun testSendPbRequestWithCurl() {
    println("testSendPbRequestWithCurl")
    VBPBServiceTest.testServiceInit()
    VBTransportServiceTest.testSendPbRequestWithCurl()
}

@KNExport
fun testSendPbRequestWithCurlAndCancel() {
    println("testSendPbRequestWithCurlAndCancel")
    VBPBServiceTest.testServiceInit()
    VBTransportServiceTest.testSendPbRequestWithCurlAndCancel()
}

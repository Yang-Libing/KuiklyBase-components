package com.tencent.kmm.component.template

class OHOSPlatform : Platform {
    override val name: String = "ohos"
    override fun initPlatform() {

    }

}
actual fun getPlatform(): Platform {
    return OHOSPlatform()
}
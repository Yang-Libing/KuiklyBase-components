package com.tencent.kmm.component.template

class AndroidPlatform : Platform {
    override val name: String = "Android ${android.os.Build.VERSION.SDK_INT}"
    override fun initPlatform() {

    }
}

actual fun getPlatform(): Platform = AndroidPlatform()
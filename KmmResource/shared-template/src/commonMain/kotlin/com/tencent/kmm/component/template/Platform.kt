package com.tencent.kmm.component.template

interface Platform {
    val name: String

    fun initPlatform()
}

expect fun getPlatform(): Platform
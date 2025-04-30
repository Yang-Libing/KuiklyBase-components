package com.tencent.kmm.component.template

class Greeting {
    private val platform: Platform = getPlatform()

    fun greet(): String {
        return platform.name
    }

    fun initPlatform(){
        //platform.initPlatform()
    }
}
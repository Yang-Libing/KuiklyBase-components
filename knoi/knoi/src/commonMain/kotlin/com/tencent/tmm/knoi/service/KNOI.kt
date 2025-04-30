package com.tencent.tmm.knoi.service

object KNOI {
    inline fun <reified T> get(): T {
        return com.tencent.tmm.knoi.definder.getService<T>()
    }

    inline fun <reified T> get(name: String): T {
        return com.tencent.tmm.knoi.definder.getService<T>(name)
    }
}
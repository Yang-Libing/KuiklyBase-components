package com.tencent.tmm.kmmresource.utils

import kotlinx.atomicfu.locks.SynchronizedObject
import kotlinx.atomicfu.locks.synchronized

abstract class OhosResourceCache<T> {
    private val cacheMap = mutableMapOf<String, T>()
    private val cacheLock = SynchronizedObject()

    fun getResourceByCache(resName: String): T {
        if (!cacheMap.containsKey(resName)) {
            synchronized(cacheLock) {
                if (!cacheMap.containsKey(resName)) {
                    val data = getResource(resName) ?: throw RuntimeException("resource $resName not found")
                    cacheMap[resName] = data
                }
            }
        }
        return cacheMap[resName]!!
    }

    abstract fun getResource(resName: String): T?

}
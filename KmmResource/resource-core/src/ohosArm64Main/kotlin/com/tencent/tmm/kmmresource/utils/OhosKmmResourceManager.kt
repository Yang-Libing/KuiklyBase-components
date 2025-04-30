package com.tencent.tmm.kmmresource.utils

import com.tencent.tmm.kmmresource.utils.cache.ColorCache
import com.tencent.tmm.kmmresource.utils.cache.FontCache
import com.tencent.tmm.kmmresource.utils.cache.StringCache
import com.tencent.tmm.knoi.type.ArrayBuffer

object OhosKmmResourceManager {

    private val fontCache = FontCache()
    private val colorCache = ColorCache()
    private val stringCache = StringCache()

    internal val resourceService = getOhosResourceServiceApi()

    fun getString(resName: String): String? {
        val resResult = stringCache.getResourceByCache(resName)
        return resResult
    }

    fun getString(resName: String, vararg args: Any): String? {
        val resResult = resourceService.getString(resName, args)
        return resResult
    }


    fun getPlural(resName: String, args: Int): String? {
        val resResult = getPluralStringByName(resName, args.toUInt())
        return resResult
    }

    @Deprecated(message = "please use getImageNative")
    fun getImage(resName: String): ArrayBuffer? {
        val resResult = resourceService.getImage(resName)
        return resResult
    }

    fun getImageNative(resName: String): ByteArray? {
        val resResult = processMediaInfo(resName)
        return resResult
    }

    fun getColor(resName: String): Int = colorCache.getResourceByCache(resName)

    fun getImageBase64(resName: String): String? {
        val resResult = try {
            getImageBase64ByName(resName)
        } catch (e: Exception) {
            throw e
        }
        return resResult
    }

    fun getFont(resName: String): ByteArray? = fontCache.getResourceByCache(resName)

    fun getFile(resName: String): ArrayBuffer? {
        val resResult = resourceService.getFile(resName)
        return resResult
    }

}
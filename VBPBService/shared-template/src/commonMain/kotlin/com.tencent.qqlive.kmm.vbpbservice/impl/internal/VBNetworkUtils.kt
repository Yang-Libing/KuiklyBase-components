package com.tencent.qqlive.kmm.vbpbservice.impl.internal

/**
 * Copyright (c) 2025 Tencent. All rights reserved
 *
 * 跨端公共网络请求处理辅助类
 *
 * @author berryyang
 * @date 2025/3/5 10:43
 */
internal object VBNetworkUtils {
    fun getRedirectedUrl(originalUrl: String, location: String): String {
        val protocol = originalUrl.substringBefore("://")
        val hostWithPort = originalUrl.substringAfter("://").substringBefore("/")
        val host = hostWithPort.substringBefore(":")
        val port =
            hostWithPort.substringAfter(":", "").takeIf { it.isNotEmpty() }?.let { ":$it" } ?: ""

        return when {
            // 绝对路径
            location.startsWith("http://") || location.startsWith("https://") || location.startsWith("//") ->
                if (location.startsWith("//")) {
                    "$protocol:$location"
                } else {
                    location
                }

            // 相对路径
            location.startsWith("/") -> "$protocol://$host$port$location"

            else -> {
                val originalPath = originalUrl.substringAfter(hostWithPort)
                val newPath = originalPath.substringBeforeLast("/") + "/" + location
                "$protocol://$host$port$newPath"
            }
        }
    }
}
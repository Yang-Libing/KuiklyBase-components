/*
 * Copyright 2020 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package com.tencent.tmm.kmmresource.resource.utils

import com.tencent.tmm.knoi.logger.info
import platform.CoreFoundation.CFAbsoluteTimeGetCurrent
import platform.Foundation.NSBundle
import platform.Foundation.NSDirectoryEnumerator
import platform.Foundation.NSFileManager
import platform.Foundation.NSURL

var podName = "QLMM"
    set(value) {
        field = value
        podBundlePath = NSBundle.mainBundle.pathForResource(value, "bundle")
        kmmBundle = podBundlePath?.let { NSBundle.bundleWithPath(it) }
    }
var isDemoTest = false
private var podBundlePath = NSBundle.mainBundle.pathForResource(podName, "bundle")
private var kmmBundle = podBundlePath?.let { NSBundle.bundleWithPath(it) }

private fun currentTimeMillis(): Long = (CFAbsoluteTimeGetCurrent() * 1000).toLong()

fun NSBundle.Companion.loadableBundle(identifier: String): NSBundle {
    info("loadableBundle start  " + currentTimeMillis())
    var bundle = getBundleWithIdentifier(identifier)
    if (bundle != null) {
        info("loadableBundle end1  " + currentTimeMillis())
        return bundle
    }
    // 兜底逻辑
    bundle = loadBundle(identifier)
    if (bundle != null) {
        info("loadableBundle end2  " + currentTimeMillis())
        return bundle
    }
    throw IllegalArgumentException("bundle with identifier $identifier not found")
}

private fun loadBundle(identifier: String): NSBundle? {
    val bundlePath: String =
        if (isDemoTest) NSBundle.mainBundle.bundlePath else kmmBundle?.bundlePath ?: return null

    val enumerator: NSDirectoryEnumerator = requireNotNull(
        NSFileManager.defaultManager.enumeratorAtPath(bundlePath)
    ) { "can't get enumerator" }

    while (true) {
        val relativePath: String = enumerator.nextObject() as? String ?: break
        val url = NSURL(fileURLWithPath = relativePath)
        if (url.pathExtension == "bundle") {
            val fullPath = "$bundlePath/$relativePath"
            val foundedBundle: NSBundle? = NSBundle.bundleWithPath(fullPath)
            val loadedIdentifier: String? = foundedBundle?.bundleIdentifier

            if (isBundleSearchLogEnabled) {
                // NSLog to see this logs in Console app when debug SwiftUI previews or release apps
                info("kmm-resources auto-load bundle with identifier $loadedIdentifier at path $fullPath")
            }
            if (foundedBundle?.bundleIdentifier == identifier) return foundedBundle
        }
    }
    return null
}

private fun getBundleWithIdentifier(identifier: String): NSBundle? {
    val bundleUrl = kmmBundle?.pathForResource(identifier, "bundle")
    if (bundleUrl != null) {
        return NSBundle.bundleWithPath(bundleUrl)
    }
    return null
}

var isBundleSearchLogEnabled = false

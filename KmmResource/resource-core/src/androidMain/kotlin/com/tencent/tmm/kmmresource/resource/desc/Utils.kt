/*
 * Copyright 2020 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package com.tencent.tmm.kmmresource.resource.desc

import android.content.Context
import android.content.res.Resources
import android.os.Build

object Utils {
    fun processArgs(args: List<Any>, context: Context): Array<out Any> =
        args.map { (it as? StringDesc)?.toString(context) ?: it }.toTypedArray()

    fun resourcesForContext(context: Context): Resources = localizedContext(context).resources

    private fun localizedContext(context: Context): Context {
        val localType = StringDesc.localeType ?: return context
        if (localType.systemLocale == null) return context

        val resources = context.resources
        val config = resources.configuration

        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            config.setLocale(localType.systemLocale)
            context.createConfigurationContext(config)
        } else {
            @Suppress("DEPRECATION")
            config.locale = localType.systemLocale
            @Suppress("DEPRECATION")
            resources.updateConfiguration(config, resources.displayMetrics)
            context
        }
    }
}

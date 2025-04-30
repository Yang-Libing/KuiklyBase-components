/*
 * Copyright 2020 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package com.tencent.tmm.kmmgradle.generator.apple

import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.PropertySpec
import com.squareup.kotlinpoet.TypeSpec
import com.tencent.tmm.kmmgradle.generator.ObjectBodyExtendable
import com.tencent.tmm.kmmgradle.utils.Constants

class AppleGeneratorHelper : ObjectBodyExtendable {

    override fun extendObjectBodyAtStart(classBuilder: TypeSpec.Builder) {
        val bundleProperty =
            PropertySpec.builder(Constants.platformInfoName, Constants.resourcePlatformInfoName)
                .addModifiers(KModifier.OVERRIDE)
                .addModifiers(KModifier.ACTUAL)
                .initializer(Constants.platformInitializeIOS)
                .build()
        classBuilder.addProperty(bundleProperty)
    }

    override fun extendObjectBodyAtEnd(classBuilder: TypeSpec.Builder) {
    }
}

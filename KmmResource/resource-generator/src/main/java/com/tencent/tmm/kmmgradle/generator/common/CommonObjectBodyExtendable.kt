package com.tencent.tmm.kmmgradle.generator.common

import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.PropertySpec
import com.squareup.kotlinpoet.TypeSpec
import com.tencent.tmm.kmmgradle.generator.ObjectBodyExtendable
import com.tencent.tmm.kmmgradle.utils.Constants

class CommonObjectBodyExtendable() : ObjectBodyExtendable {
    override fun extendObjectBodyAtStart(classBuilder: TypeSpec.Builder) {
        val bundleProperty =
            PropertySpec.builder(Constants.platformInfoName, Constants.resourcePlatformInfoName)
                .addModifiers(KModifier.OVERRIDE)
                .build()
        classBuilder.addProperty(bundleProperty)
    }

    override fun extendObjectBodyAtEnd(classBuilder: TypeSpec.Builder) {
    }
}
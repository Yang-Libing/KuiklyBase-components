/*
 * Copyright 2020 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package com.tencent.tmm.kmmgradle.generator

import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy
import com.squareup.kotlinpoet.TypeSpec

interface ObjectBodyExtendable {
    fun extendObjectBodyAtStart(classBuilder: TypeSpec.Builder)
    fun extendObjectBodyAtEnd(classBuilder: TypeSpec.Builder)
}


internal fun TypeSpec.Builder.addOverrideAbstractValuesFunction(
    classType: ClassName,
): TypeSpec.Builder {
    val valuesFun: FunSpec = FunSpec.builder("values")
        .addModifiers(KModifier.OVERRIDE)
        .returns(
            ClassName("kotlin.collections", "List")
                .parameterizedBy(classType)
        )
        .build()

    return addFunction(valuesFun)
}
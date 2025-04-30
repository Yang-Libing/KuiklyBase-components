package com.tencent.tmm.knoi.annotation

import kotlin.reflect.KClass

@Target(AnnotationTarget.FUNCTION, AnnotationTarget.FIELD)
annotation class KNExport(val name: String = "")

@Target(AnnotationTarget.CLASS)
annotation class ServiceConsumer(val name: String = "")

@Target(AnnotationTarget.CLASS)
annotation class ServiceProvider(
    val name: String = "",
    val bind: KClass<out Any> = Unit::class,
    val singleton: Boolean = false
)

@Target(AnnotationTarget.FUNCTION)
annotation class Hidden

@Target(AnnotationTarget.CLASS)
annotation class KNCallback

@Target(AnnotationTarget.FUNCTION)
annotation class ModuleInitializer()
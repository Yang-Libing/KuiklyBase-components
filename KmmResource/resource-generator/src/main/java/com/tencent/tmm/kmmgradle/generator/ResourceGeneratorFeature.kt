/*
 * Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package com.tencent.tmm.kmmgradle.generator

interface ResourceGeneratorFeature<T : MRGenerator.Generator> {
    fun createCommonGenerator(): T
    fun createAndroidGenerator(): T

    fun createOhosGenerator(): T

    fun createIosGenerator(): T
}


/*
 * Copyright 2021 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package com.tencent.tmm.kmmgradle.utils

/**
 * Replace all new lines including space characters before and after.
 * This is required to remove the IDE indentation.
 */
internal fun String.removeLineWraps(): String = replace(Regex("\\s*\n\\s*"), " ")

internal val String.withoutScale get() =
    substringBefore("@")

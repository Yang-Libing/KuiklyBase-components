/*
 * Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package com.tencent.tmm.kmmresource.resource

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat

actual class ImageResource(
    @DrawableRes val drawableResId: Int
) {

    fun getDrawable(context: Context): Drawable? = ContextCompat.getDrawable(context, drawableResId)
}

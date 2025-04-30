package com.tencent.tmm.kmmresource.utils.cache

import com.tencent.tmm.kmmresource.utils.OhosResourceCache
import com.tencent.tmm.kmmresource.utils.getColorByName

class ColorCache : OhosResourceCache<Int>() {
    override fun getResource(resName: String): Int = getColorByName(resName)

}
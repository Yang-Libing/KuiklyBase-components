package com.tencent.tmm.kmmresource.utils.cache

import com.tencent.tmm.kmmresource.utils.OhosResourceCache
import com.tencent.tmm.kmmresource.utils.getNormalStringByName

class StringCache : OhosResourceCache<String>() {
    override fun getResource(resName: String): String? = getNormalStringByName(resName)

}
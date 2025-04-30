package com.tencent.tmm.kmmresource.utils.cache

import com.tencent.tmm.kmmresource.utils.OhosKmmResourceManager
import com.tencent.tmm.kmmresource.utils.OhosResourceCache

class FontCache : OhosResourceCache<ByteArray>() {
    override fun getResource(resName: String): ByteArray? {
        val ohosPointer = OhosKmmResourceManager.resourceService.getFile(resName)
        val result = ohosPointer?.getByteArray()
        return result
    }

}
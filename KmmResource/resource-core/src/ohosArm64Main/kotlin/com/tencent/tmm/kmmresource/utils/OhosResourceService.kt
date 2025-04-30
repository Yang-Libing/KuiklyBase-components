package com.tencent.tmm.kmmresource.utils

import com.tencent.tmm.knoi.annotation.ServiceConsumer
import com.tencent.tmm.knoi.type.ArrayBuffer

@ServiceConsumer
interface OhosResourceService {
    fun getString(resName: String): String?

    fun getString(resName: String, vararg args: Any): String?

    fun getPlural(resName: String, args: Number): String?

    fun getImage(resName: String): ArrayBuffer?

    fun getColor(resName: String): Int?

    fun getImageBase64(resName: String): String?

    fun getFile(resName: String): ArrayBuffer?

}
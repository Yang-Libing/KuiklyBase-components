package com.tencent.tmm.kmmgradle.generator.ohos.data

open class OhosPluralInfo {
    var name: String = ""
    var value = HashSet<OhosPluralQuantity>()

    override fun hashCode(): Int = name.hashCode()

    override fun equals(other: Any?): Boolean {
        if (other == null || other !is OhosPluralInfo) {
            return false
        }
        return other.name == this.name
    }
}
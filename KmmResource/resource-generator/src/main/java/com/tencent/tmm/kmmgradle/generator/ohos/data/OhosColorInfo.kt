package com.tencent.tmm.kmmgradle.generator.ohos.data

open class OhosColorInfo {
    var name: String = ""
    var value: String = ""

    override fun hashCode(): Int = "${name}".hashCode()

    override fun equals(other: Any?): Boolean {
        if (other == null || other !is OhosColorInfo) {
            return false
        }
        return other.name == this.name
    }
}
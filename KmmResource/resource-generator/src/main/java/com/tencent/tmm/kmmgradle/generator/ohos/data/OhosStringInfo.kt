package com.tencent.tmm.kmmgradle.generator.ohos.data

open class OhosStringInfo {
    var name: String = ""
    var value: String = ""

    override fun hashCode(): Int = "${name}".hashCode()

    override fun equals(other: Any?): Boolean {
        if (other == null) {
            return false
        }
        if (other !is OhosStringInfo) {
            return false
        }
        return other.name == this.name
    }
}
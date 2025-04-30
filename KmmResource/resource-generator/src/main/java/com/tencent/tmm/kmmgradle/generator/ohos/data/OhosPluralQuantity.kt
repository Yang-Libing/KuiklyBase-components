package com.tencent.tmm.kmmgradle.generator.ohos.data

open class OhosPluralQuantity {
    var quantity: String = ""
    var value: String = ""

    override fun hashCode(): Int = "${quantity}:${value}".hashCode()

    override fun equals(other: Any?): Boolean {
        if (other == null || other !is OhosPluralQuantity) {
            return false
        }
        return other.quantity == this.quantity && other.value == this.value
    }
}
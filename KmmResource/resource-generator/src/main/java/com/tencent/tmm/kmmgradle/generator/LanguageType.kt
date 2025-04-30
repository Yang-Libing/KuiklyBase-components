/*
 * Copyright 2022 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package com.tencent.tmm.kmmgradle.generator

import java.util.Locale as JvmLocale

sealed interface LanguageType {

    val androidResourcesDir: String
    val appleResourcesDir: String
    val ohosResourcesDir: String
    val jsResourcesSuffix: String
    val jvmResourcesSuffix: String

    object Base : LanguageType {
        override val androidResourcesDir: String = "values"
        override val appleResourcesDir: String = "Base.lproj"
        override val ohosResourcesDir: String = "base/element"
        override val jsResourcesSuffix: String = ""
        override val jvmResourcesSuffix: String = ""
    }

    class Locale(languageTag: String) : LanguageType {

        private val jvmLocale: JvmLocale = JvmLocale.forLanguageTag(languageTag)

        override val androidResourcesDir: String = buildString {
            append("values")
            append("-")
            append(jvmLocale.getISO639Language())
            if (jvmLocale.country.isNotBlank()) {
                append("-r")
                append(jvmLocale.country)
            }
        }

        override val appleResourcesDir: String = "${toBcpString()}.lproj"
        override val ohosResourcesDir: String = "${toBcpString()}/element"
        override val jsResourcesSuffix: String = "_${toBcpString()}"

        // JVM ResourceBundle uses locale format, eg `en_US`, instead of BCP format
        // like `en-US`.
        override val jvmResourcesSuffix: String = "_${toLocaleString()}"

        fun toBcpString(): String = jvmLocale.toLanguageTag()

        private fun toLocaleString(): String = jvmLocale.toString()

        /**
         * Throw an error here so that we safeguard ourselves from implcitly calling `Local.toString`.
         * You should always use the more explicit methods defined above.
         */
        override fun toString(): String = TODO("Use toLocaleString or toBcpString instead!")

        override fun hashCode(): Int = jvmLocale.hashCode()
        override fun equals(other: Any?): Boolean = other is Locale && other.jvmLocale == jvmLocale

        /**
         * For android we should use ISO-639-1 language code
         * https://developer.android.com/guide/topics/resources/providing-resources
         *
         * @see java.util.Locale#convertOldISOCodes
         */
        private fun JvmLocale.getISO639Language(): String {
            return when (language) {
                "he" -> "iw"
                "yi" -> "ji"
                "id" -> "in"
                else -> language
            }
        }
    }

    companion object {

        private const val BASE = "base"

        fun fromFileName(fileName: String): LanguageType = when (fileName) {
            BASE -> Base
            else -> Locale(fileName.replace("-r", "-"))
        }
    }
}

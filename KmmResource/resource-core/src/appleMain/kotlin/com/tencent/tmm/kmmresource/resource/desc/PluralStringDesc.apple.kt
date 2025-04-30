package com.tencent.tmm.kmmresource.resource.desc

import com.tencent.tmm.kmmresource.internal.Utils.BASE_LOCALIZATION
import com.tencent.tmm.kmmresource.resource.PluralsResource
import platform.Foundation.NSBundle
import platform.Foundation.NSLocale
import platform.Foundation.NSString
import platform.Foundation.create

actual data class PluralStringDesc actual constructor(
    val pluralsRes: PluralsResource,
    val number: Int
) : StringDesc {
    override fun localized(): String {
        return pluralizedString(
            bundle = StringDesc.localeType.getLocaleBundle(pluralsRes.bundle),
            baseBundle = pluralsRes.bundle,
            locale = StringDesc.localeType.locale,
            resourceId = pluralsRes.resourceId,
            number = number
        )
    }
}

internal fun pluralizedString(
    bundle: NSBundle,
    baseBundle: NSBundle,
    locale: NSLocale,
    resourceId: String,
    number: Int
): String {
    val fallbackLocale = bundle.developmentLocalization ?: BASE_LOCALIZATION
    val localized = bundle
        .localizedStringForKey(resourceId, null, null)
        .takeUnless { it == resourceId }
        ?: baseBundle.localizedStringForKey(resourceId, null, null)
            .takeUnless { it == resourceId } ?: StringDesc.LocaleType.Custom(fallbackLocale)
            .getLocaleBundle(bundle).localizedStringForKey(resourceId, null, null)
    @Suppress("CAST_NEVER_SUCCEEDS")
    return NSString.create(
        format = localized,
        locale = locale,
        args = arrayOf(number)
    ) as String
}
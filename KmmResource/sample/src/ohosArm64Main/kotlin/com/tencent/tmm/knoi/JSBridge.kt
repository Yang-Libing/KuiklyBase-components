package com.tencent.tmm.knoi


import com.tencent.tmm.kmmresource.utils.OhosKmmResourceManager
import com.tencent.tmm.kmmresource.utils.getOhosResourceServiceApi
import com.tencent.tmm.knoi.annotation.KNExport
import com.tencent.tmm.knoi.logger.info
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.readBytes
import platform.ohos.LOG_APP
import platform.ohos.LOG_INFO
import platform.ohos.OH_LOG_Print
import platform.posix.uint8_tVar


@OptIn(ExperimentalForeignApi::class)
@KNExport("JSBridge")
fun bridge() {
    info("JSBridge string hello_ohos ${OhosKmmResourceManager.getString("hello_ohos")}")

    info("JSBridge color textColor${OhosKmmResourceManager.getColor("textColor")}")

    OhosKmmResourceManager.getImageBase64("startIcon")

    OhosKmmResourceManager.getPlural("eat_apple", 3)
}

@OptIn(ExperimentalForeignApi::class)
fun test(vararg params: Any?) {
    val color = getOhosResourceServiceApi().getColor(MR.colors.textColor.resourceName).toString()
    OH_LOG_Print(
        LOG_APP, LOG_INFO, 1u, "color", color
    )
    val string =
        getOhosResourceServiceApi().getString(MR.strings.hello_ohos.resourceName).toString()
    OH_LOG_Print(
        LOG_APP, LOG_INFO, 1u, "string", string
    )
    val stringargs =
        getOhosResourceServiceApi().getString("EntryAbility_label", "123", 5).toString()
    OH_LOG_Print(
        LOG_APP, LOG_INFO, 1u, "string s d ", stringargs
    )
    val background = getOhosResourceServiceApi().getImage("background")?.getCount()?.toString()
    OH_LOG_Print(
        LOG_APP, LOG_INFO, 1u, "image", background
    )

    val stringImage = getOhosResourceServiceApi().getImageBase64("background").toString()
    OH_LOG_Print(
        LOG_APP, LOG_INFO, 1u, "image64", stringImage
    )


    val file = getOhosResourceServiceApi().getFile("layered_image.json")?.getData<uint8_tVar>()
        ?.readBytes(500)?.decodeToString()
    OH_LOG_Print(
        LOG_APP, LOG_INFO, 1u, "layered_image", file
    )


}
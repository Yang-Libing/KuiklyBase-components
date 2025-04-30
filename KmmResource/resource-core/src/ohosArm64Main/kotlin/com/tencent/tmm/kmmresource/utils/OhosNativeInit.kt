package com.tencent.tmm.kmmresource.utils

import com.tencent.tmm.kmmresource.ohos_init_resource_manager
import com.tencent.tmm.knoi.annotation.KNExport
import com.tencent.tmm.knoi.getEnv
import com.tencent.tmm.knoi.type.JSValue
import kotlinx.cinterop.ExperimentalForeignApi

@OptIn(ExperimentalForeignApi::class)
@KNExport("init_resource_compose")
fun initResourceCompose(resourceManager: JSValue) {
    ohos_init_resource_manager(getEnv(), resourceManager.handle)
}
package com.tencent.kmm.component.template

import com.tencent.tmm.kmmresource.resource.ImageResource
import com.tencent.tmm.kmmresource.resource.utils.podName
import com.tencent.tmm.kmmresource.sample.MR

actual fun getPlatform(): Platform {
    return object : Platform {
        override val name: String
            get() = kotlin.run {
                podName = "shared_template"
                "ios" + MR.images.webp.run {
                    bundle.pathForResource(
                        assetImageName,
                        ofType = suffix,
                        inDirectory = ImageResource.X_DPI_PATH
                    )
                }
            }

        override fun initPlatform() {
        }
        // get() = "ios" + MR.assets.anc.some_asset.path
    }
}
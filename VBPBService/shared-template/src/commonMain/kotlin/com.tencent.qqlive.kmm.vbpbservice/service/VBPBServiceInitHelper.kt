package com.tencent.qqlive.kmm.vbpbservice.service

import com.squareup.wire.kmm.Message
import com.tencent.auth_center.auth_center.kmm.RefreshTokenRequestV2
import com.tencent.auth_center.auth_center.kmm.RefreshTokenResponseV2
import com.tencent.qqlive.kmm.vbpbservice.export.IVBPBConfig
import com.tencent.qqlive.kmm.vbpbservice.export.IVBPBDeviceInfo
import com.tencent.qqlive.kmm.vbpbservice.export.IVBPBLog
import com.tencent.qqlive.kmm.vbpbservice.export.IVBPBQUICConfig
import com.tencent.qqlive.kmm.vbpbservice.export.IVBPBReport
import com.tencent.qqlive.kmm.vbpbservice.export.IVBPBVersionInfo
import com.tencent.qqlive.kmm.vbpbservice.export.VBPBInitConfig
import com.tencent.qqlive.kmm.vbpbservice.export.VBPBNetworkState
import com.tencent.qqlive.kmm.vbpbservice.export.VBPBRequest
import com.tencent.qqlive.kmm.vbpbservice.export.VBPBRequestConfig
import com.tencent.qqlive.kmm.vbpbservice.impl.internal.VBPBAutoRetry
import com.tencent.qqlive.kmm.vbpbservice.impl.internal.VBPBConfig
import com.tencent.qqlive.kmm.vbpbservice.impl.internal.VBPBDeviceInfo
import com.tencent.qqlive.kmm.vbpbservice.impl.internal.VBPBLog
import com.tencent.qqlive.kmm.vbpbservice.impl.internal.VBPBPersonalize
import com.tencent.qqlive.kmm.vbpbservice.impl.internal.VBPBProtocolType
import com.tencent.qqlive.kmm.vbpbservice.impl.internal.VBPBQUICConfig
import com.tencent.qqlive.kmm.vbpbservice.impl.internal.VBPBReport
import com.tencent.qqlive.kmm.vbpbservice.impl.internal.VBPBRequestIdGenerator
import com.tencent.qqlive.kmm.vbpbservice.impl.internal.VBPBTab
import com.tencent.qqlive.kmm.vbpbservice.impl.internal.VBPBVersionInfo
import com.tencent.qqlive.kmm.vbpbservice.impl.platform.getIVBTransportService
import com.tencent.qqlive.kmm.vbtransportservice.curl.CurlRequestService
import com.tencent.qqlive.protocol.vb.pb.kmm.EnvInfo
import com.tencent.qqlive.protocol.vb.pb.kmm.LoginToken
import com.tencent.qqlive.protocol.vb.pb.kmm.UserStatusInfo
import kotlin.experimental.ExperimentalObjCName
import kotlin.native.ObjCName

@OptIn(ExperimentalObjCName::class)
@ObjCName("VBPBServiceInitHelper")
object VBPBServiceInitHelper {

    @ObjCName("init")
    fun init(initConfig: VBPBInitConfig) {
        initConfig.logImpl?.let {
            VBPBLog.logImpl = it
            // 透传日志实现到ArkTS
            getIVBTransportService().setLogImpl { tag, content ->
                VBPBLog.i(tag, content)
            }

            // 注入 libcurl 日志实现
            CurlRequestService.initNativeCurlLog(it)
        }
        VBPBReport.reportImpl = initConfig.reportImpl
        VBPBConfig.config = initConfig.config
        VBPBDeviceInfo.deviceInfo = initConfig.deviceInfo
        VBPBVersionInfo.versionInfo = initConfig.versionInfo
        VBPBPersonalize.personalize = initConfig.personalizeInfo
        VBPBAutoRetry.autoRetry = initConfig.autoRetry
        VBPBQUICConfig.quicConfig = initConfig.quicConfig
        VBPBRequestIdGenerator.requestIdGenerator = initConfig.requestIdGenerator
        VBPBTab.tabImpl = initConfig.tabProxy
        VBPBLog.i(VBPBLog.INIT_TASK, "PBService init")
    }

    /**
     * 用默认参数初始化 PB 组件
     * NOTE: 仅供 腾讯视频 debug 使用
     */
    @ObjCName("debugInit")
    fun debugInit(logImpl: IVBPBLog) {
        val defaultInitConfig = generateDefaultInitConfig()
        defaultInitConfig.logImpl = logImpl
        defaultInitConfig.reportImpl = object : IVBPBReport {
            override fun reportSampleRate(cmd: String, isSuccess: Boolean) = 1  // 全部上报

            override fun report(reportInfo: Map<String, String>) {
                reportInfo.forEach { (k, v) ->
                    logImpl.i("[NXNetwork]", "[$k]:$v")
                }
            }
        }
        init(defaultInitConfig)
    }

    fun generateDefaultInitConfig(): VBPBInitConfig {
        return VBPBInitConfig().apply {
            versionInfo = object : IVBPBVersionInfo {
                override fun getAppId() = 1000005
                override fun getAppNameId() = 0
                override fun getChannelId() = -1
                override fun getPlatformId() = 3
                override fun getPlatformVersion() = "SM-F9000,32,12"
                override fun getVersionCode() = 47410
                override fun getVersionName() = "8.11.55.47410"
            }
            config = object : IVBPBConfig {
                override fun getQQAppId() = "101795054"
                override fun getWxAppId() = "wxca942bbff22e0e51"
                override fun getQmfAppId() = 10012
                override fun getQmfPlatform(): Byte = 1
                override fun getOmgId() = "10012"
                override fun getGuid() = "81a12133f65f11e9bd90487b6b85e315"
                override fun getVNVersion() = 0
                override fun getOpenId() = ""
                override fun getEnvInfo() = EnvInfo()
                override fun getDomain() = null
                override fun getExtraRequestHeadMap() = emptyMap<String, String>()
                override fun getQIMEI() = "6f3a83000f8c1eb2b438c876100018c14911"
                override fun isMainProcess() = true
                override fun getUserStatusInfo() = UserStatusInfo()
                override fun setUserStatusInfo(userStatusInfo: UserStatusInfo) {}
                override fun getLoginTokenList() = listOf<LoginToken>()
                override fun getOAID(): String = ""
                override fun getRefreshTokenRequest(): RefreshTokenRequestV2? = null
                override fun setRefreshTokenResponse(refreshTokenResponse: RefreshTokenResponseV2) {}
                override fun getCAID(): String = ""
                override fun getCAIDVersion(): String = ""
                override fun getOperator(): Int = 0
                override fun getNetworkState() = VBPBNetworkState.NETWORK_STATE_WIFI
                override fun getClientIPv4(): String = ""
                override fun getVuid(): String = "test_vuid_123"
                override fun getQimei36(): String = "test_qimei36_abcd"
                override fun onInterceptRequestConfig(func: String, requestConfig: VBPBRequestConfig) {
                    requestConfig.protocolType = VBPBProtocolType.HTTP
                }
            }
            deviceInfo = object : IVBPBDeviceInfo {
                override var screenWidth = 2265
                override var screenHeight = 1080
                override var densityDpi = 480
                override var omgId = "9e99112b3f79b643cfa988a1491f64d6c5e80010214918"
                override var guid = "81a12133f65f11e9bd90487b6b85e315"
                override var manufacturer = "HUAWEI"
                override var deviceId = ""
                override var deviceType = 1
                override var deviceModel = "VOG-AL10"
                override fun getMaxUiSizeByUiSizeType(): Int = 0
                override fun getCurrentWindowUiSizeByUiSizeType(): Int = 0
            }
            quicConfig = object  : IVBPBQUICConfig {
                override fun <REQUEST : Message<*, *>, RESPONSE : Message<*, *>> shouldTryToUseQUIC(
                    request: VBPBRequest<REQUEST, RESPONSE>
                ): Boolean = true
            }
        }
    }
}
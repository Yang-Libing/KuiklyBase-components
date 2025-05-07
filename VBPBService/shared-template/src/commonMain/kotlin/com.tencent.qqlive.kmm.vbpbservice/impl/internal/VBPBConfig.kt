package com.tencent.qqlive.kmm.vbpbservice.impl.internal

import com.tencent.auth_center.auth_center.kmm.RefreshTokenRequestV2
import com.tencent.auth_center.auth_center.kmm.RefreshTokenResponseV2
import com.tencent.qqlive.kmm.vbpbservice.export.IVBPBConfig
import com.tencent.qqlive.kmm.vbpbservice.export.VBPBNetworkState
import com.tencent.qqlive.kmm.vbpbservice.export.VBPBRequestConfig
import com.tencent.qqlive.kmm.vbpbservice.export.VBPBRequestInfo
import com.tencent.qqlive.protocol.vb.pb.kmm.EnvInfo
import com.tencent.qqlive.protocol.vb.pb.kmm.LoginToken
import com.tencent.qqlive.protocol.vb.pb.kmm.RefreshRequest
import com.tencent.qqlive.protocol.vb.pb.kmm.RefreshResponse
import com.tencent.qqlive.protocol.vb.pb.kmm.UserStatusInfo

/**
 * Copyright (c) 2024 Tencent. All rights reserved
 *
 * PB 配置信息代理
 *
 * @author haibarawang
 * @date 2024/3/1 19:41
 */
object VBPBConfig : IVBPBConfig {

    var config: IVBPBConfig? = null

    override fun getQQAppId(): String = config?.getQQAppId() ?: ""

    override fun getWxAppId(): String = config?.getWxAppId() ?: ""

    override fun getQmfAppId(): Int = config?.getQmfAppId() ?: 0

    override fun getQmfPlatform(): Byte = config?.getQmfPlatform() ?: 0

    override fun getOmgId(): String = config?.getOmgId() ?: ""

    override fun getGuid(): String = config?.getGuid() ?: ""

    override fun getVNVersion(): Int = config?.getVNVersion() ?: 0

    override fun getOpenId(): String = config?.getOpenId() ?: ""

    override fun getEnvInfo(): EnvInfo? = config?.getEnvInfo()

    override fun getDomain(): String? = config?.getDomain()

    override fun getExtraRequestHeadMap(): Map<String, String> =
        config?.getExtraRequestHeadMap() ?: emptyMap()

    override fun getExtraRequestHeadMapByRequestInfo(requestInfo: VBPBRequestInfo): Map<String, String> =
        config?.getExtraRequestHeadMapByRequestInfo(requestInfo) ?: emptyMap()

    override fun getQIMEI(): String = config?.getQIMEI() ?: ""

    override fun isMainProcess(): Boolean = config?.isMainProcess() ?: true

    override fun getUserStatusInfo(): UserStatusInfo? = config?.getUserStatusInfo()

    override fun setUserStatusInfo(userStatusInfo: UserStatusInfo) {
        config?.setUserStatusInfo(userStatusInfo)
    }

    override fun getLoginTokenList(): List<LoginToken> = config?.getLoginTokenList() ?: listOf()

    override fun getOAID(): String = config?.getOAID() ?: ""

    override fun getRefreshTokenRequest(): RefreshTokenRequestV2? =
        config?.getRefreshTokenRequest()

    override fun setRefreshTokenResponse(refreshTokenResponse: RefreshTokenResponseV2) {
        config?.setRefreshTokenResponse(refreshTokenResponse)
    }

    override fun getRefreshRequest(): RefreshRequest? = config?.getRefreshRequest()

    override fun setRefreshResponse(refreshResponse: RefreshResponse) {
        config?.setRefreshResponse(refreshResponse)
    }

    override fun getCAID(): String = config?.getCAID() ?: ""

    override fun getCAIDVersion(): String = config?.getCAIDVersion() ?: ""

    override fun getOperator(): Int = config?.getOperator() ?: 0

    override fun getNetworkState() =
        config?.getNetworkState() ?: VBPBNetworkState.NETWORK_STATE_UNKNOWN

    override fun getClientIPv4(): String = config?.getClientIPv4() ?: ""

    override fun getVuid(): String = config?.getVuid() ?: ""

    override fun getQimei36(): String = config?.getQimei36() ?: ""

    override fun onInterceptRequestConfig(func: String, requestConfig: VBPBRequestConfig) {
        config?.onInterceptRequestConfig(func, requestConfig)
    }

}
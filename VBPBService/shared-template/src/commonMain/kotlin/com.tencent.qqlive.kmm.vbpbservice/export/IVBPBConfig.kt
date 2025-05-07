package com.tencent.qqlive.kmm.vbpbservice.export

import com.tencent.auth_center.auth_center.kmm.RefreshTokenRequestV2
import com.tencent.auth_center.auth_center.kmm.RefreshTokenResponseV2
import com.tencent.qqlive.protocol.vb.pb.kmm.EnvInfo
import com.tencent.qqlive.protocol.vb.pb.kmm.LoginToken
import com.tencent.qqlive.protocol.vb.pb.kmm.RefreshRequest
import com.tencent.qqlive.protocol.vb.pb.kmm.RefreshResponse
import com.tencent.qqlive.protocol.vb.pb.kmm.UserStatusInfo
import kotlin.experimental.ExperimentalObjCName
import kotlin.native.ObjCName

/**
 * Copyright (c) 2024 Tencent. All rights reserved
 *
 * PB 配置接口
 *
 * @author haibarawang
 * @date 2024/3/1 19:41
 */
@OptIn(ExperimentalObjCName::class)
@ObjCName("IVBPBConfig")
interface IVBPBConfig {

    /**
     * 设置QQAppId 用于登录信息获取
     *
     * @return QQAppId
     */
    @ObjCName("pb_getQQAppId")
    fun getQQAppId(): String

    /**
     * 获取WxAppId 用于登录信息获取
     *
     * @return WxAppId
     */
    @ObjCName("pb_getWxAppId")
    fun getWxAppId(): String

    /**
     * 获取QmfAppId
     *
     * @return QmfAppId 用于Qmf帧协议打包
     */
    @ObjCName("pb_getQmfAppId")
    fun getQmfAppId(): Int

    /**
     * 获取QmfPlatform
     *
     * @return QmfPlatform 平台标示
     */
    @ObjCName("pb_getQmfPlatform")
    fun getQmfPlatform(): Byte

    /**
     * 获取OmgId
     *
     * @return OmgId
     */
    @ObjCName("pb_getOmgId")
    fun getOmgId(): String

    /**
     * 获取Guid
     *
     * @return Guid
     */
    @ObjCName("pb_getGuid")
    fun getGuid(): String

    /**
     * 设置VNView模版离线包的版本号
     *
     * @return VNView模版离线包的版本号
     */
    @ObjCName("pb_getVNVersion")
    fun getVNVersion(): Int

    /**
     * 获取 Open Id 主端目前填写登录QQ号
     *
     * @return Open Id 主端目前填写登录QQ号
     */
    @ObjCName("pb_getOpenId")
    fun getOpenId(): String

    /**
     * 获取 环境信息，用于户客户端切换后台环境
     *
     * @return 环境信息，用于户客户端切换后台环境
     */
    @ObjCName("pb_getEnvInfo")
    fun getEnvInfo(): EnvInfo?

    /**
     * 获取 当前环境域名，用于切换测试/开发环境
     *
     * @return 设置的域名
     */
    @ObjCName("pb_getDomain")
    fun getDomain(): String?

    /**
     * 获取PB包头额外请求字段，如主端 usid，QIMEI36，vcuid
     *
     * @return PB包头额外请求字段
     */
    @ObjCName("pb_getExtraRequestHeadMap")
    fun getExtraRequestHeadMap(): Map<String, String>

    /**
     * 获取PB包头额外请求字段，如主端 usid，QIMEI36，vcuid
     *
     * @return PB包头额外请求字段
     */
    @ObjCName("pb_getExtraRequestHeadMapByRequestInfo")
    fun getExtraRequestHeadMapByRequestInfo(requestInfo: VBPBRequestInfo): Map<String, String> = mutableMapOf()

    /**
     * 获取 qimei 参数
     *
     * @return qimei 参数
     */
    @ObjCName("pb_getQIMEI")
    fun getQIMEI(): String

    /**
     * 判断是否主进程
     *
     * @return 是否主进程
     */
    @ObjCName("pb_isMainProcess")
    fun isMainProcess(): Boolean

    /**
     * 获取 用户特区信息
     *
     * @return 用户特区信息
     */
    @ObjCName("pb_getUserStatusInfo")
    fun getUserStatusInfo(): UserStatusInfo?

    /**
     * 更新用户信息
     */
    @ObjCName("pb_setUserStatusInfo")
    fun setUserStatusInfo(userStatusInfo: UserStatusInfo)

    /**
     * 获取 获取登录票据信息
     *
     * @return 获取登录票据信息
     */
    @ObjCName("pb_getLoginTokenList")
    fun getLoginTokenList(): List<LoginToken>

    /**
     * Open Anonymous Device Identifier，由工信部联合国内各安卓厂商推出的广告标识符，Android设备
     * 获取OAID
     *
     * @return OAID
     */
    @ObjCName("pb_getOAID")
    fun getOAID(): String

    /**
     * 获取登录刷新信息
     *
     * @return 登录刷新信息
     */
    @ObjCName("pb_getRefreshTokenRequest")
    fun getRefreshTokenRequest(): RefreshTokenRequestV2?

    /**
     * 更新登录信息
     */
    @ObjCName("pb_setRefreshTokenResponse")
    fun setRefreshTokenResponse(refreshTokenResponse: RefreshTokenResponseV2)

    /**
     * 获取登录刷新信息 适配新登录体系，因为要做AB 所以暂时保留老逻辑
     */
    @ObjCName("pb_getRefreshRequest")
    fun getRefreshRequest(): RefreshRequest? = null

    /**
     * 更新登录信息 适配新登录体系，因为要做AB 所以暂时保留老逻辑
     */
    @ObjCName("pb_setRefreshResponse")
    fun setRefreshResponse(refreshResponse: RefreshResponse) {
    }

    /**
     * 获取 CAID
     */
    @ObjCName("pb_getCAID")
    fun getCAID(): String

    /**
     * 获取 CAID_Version
     */
    @ObjCName("pb_getCAIDVersion")
    fun getCAIDVersion(): String

    /**
     * 获取 运营商类型
     */
    @ObjCName("pb_getOperator")
    fun getOperator(): Int

    /**
     * 获取 网络类型
     */
    @ObjCName("pb_getNetworkState")
    fun getNetworkState(): VBPBNetworkState

    /**
     * 获取出口 IP
     */
    @ObjCName("pb_getClientIPv4")
    fun getClientIPv4(): String

    /**
     * 获取视频用户 ID
     */
    @ObjCName("pb_getVuid")
    fun getVuid(): String

    /**
     * 获取 qimei36
     */
    @ObjCName("pb_getQimei36")
    fun getQimei36(): String

    /**
     * 拦截处理请求配置信息
     */
    @ObjCName("pb_onInterceptRequestConfig")
    fun onInterceptRequestConfig(func: String, requestConfig: VBPBRequestConfig)

}

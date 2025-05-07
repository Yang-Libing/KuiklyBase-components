package com.tencent.qqlive.kmm.vbpbservice.impl.internal

import com.tencent.auth_center.auth_center.kmm.RefreshTokenRequestV2
import com.tencent.qqlive.kmm.vbpbservice.export.VBNetworkState
import com.tencent.qqlive.kmm.vbpbservice.export.VBPBBucketInfo
import com.tencent.qqlive.kmm.vbpbservice.export.VBPBFlagInfo
import com.tencent.qqlive.kmm.vbpbservice.export.VBPBNetworkState
import com.tencent.qqlive.kmm.vbpbservice.export.VBPBRequestInfo
import com.tencent.qqlive.kmm.vbpbservice.impl.platform.getIVBTransportService
import com.tencent.qqlive.protocol.vb.pb.kmm.BucketInfo
import com.tencent.qqlive.protocol.vb.pb.kmm.DeviceInfo
import com.tencent.qqlive.protocol.vb.pb.kmm.EnvInfo
import com.tencent.qqlive.protocol.vb.pb.kmm.FlagInfo
import com.tencent.qqlive.protocol.vb.pb.kmm.LocationInfo
import com.tencent.qqlive.protocol.vb.pb.kmm.LoginToken
import com.tencent.qqlive.protocol.vb.pb.kmm.NetworkInfo
import com.tencent.qqlive.protocol.vb.pb.kmm.PortraitInfo
import com.tencent.qqlive.protocol.vb.pb.kmm.RefreshRequest
import com.tencent.qqlive.protocol.vb.pb.kmm.RequestHead
import com.tencent.qqlive.protocol.vb.pb.kmm.UISizeType
import com.tencent.qqlive.protocol.vb.pb.kmm.UserStatusInfo
import com.tencent.qqlive.protocol.vb.pb.kmm.VersionInfo
import okio.ByteString
import okio.ByteString.Companion.encodeUtf8

/**
 * Copyright (c) 2024 Tencent. All rights reserved
 *
 * 公参信息配置
 *
 * @author haibarawang
 * @date 2024/4/13 14:41
 */
class VBPBHeaderConfig {
    /**
     * 创建公参
     */
    fun createCommonRequestHead(
        requestId: Int,
        requestTag: String?,
        callee: String,
        func: String,
        uniqueId: String
    ): RequestHead {
        logI("$requestTag createCommonRequestHead start, reqId:$requestId")
        val loginTokenInfo: List<LoginToken> = VBPBConfig.getLoginTokenList()
        val locationInfo: LocationInfo = createLocationInfo()
        val deviceInfo: DeviceInfo = createDeviceInfo()
        val versionInfo: VersionInfo = createVersionInfo()
        logI("$requestTag createCommonRequestHead get versionInfo finish, reqId:$requestId")
        val networkInfo: NetworkInfo = createNetworkInfo()
        logI("$requestTag createCommonRequestHead get networkInfo finish, reqId:$requestId")
        val flagInfo: FlagInfo = createFlagInfo()
        val portraitInfo: List<PortraitInfo> = createPortraitInfo()
        val bucketInfo: BucketInfo? = createBucketInfo()
        val userStatusInfo: UserStatusInfo = VBPBConfig.getUserStatusInfo() ?: UserStatusInfo()
        val envInfo: EnvInfo = VBPBConfig.getEnvInfo() ?: EnvInfo()

        val requestInfo = VBPBRequestInfo()
        requestInfo.callee = callee
        requestInfo.func = func
        requestInfo.uniqueId = uniqueId
        val extraRequestHeadMap: Map<String, String> =
            VBPBConfig.getExtraRequestHeadMap() + VBPBConfig.getExtraRequestHeadMapByRequestInfo(requestInfo)

        val refreshTokenRequestV2: RefreshTokenRequestV2? = VBPBConfig.getRefreshTokenRequest()
        val refreshRequest: RefreshRequest? = VBPBConfig.getRefreshRequest()

        return RequestHead(
            login_token = loginTokenInfo,
            location_info = locationInfo,
            device_info = deviceInfo,
            version_info = versionInfo,
            network_info = networkInfo,
            flag_info = flagInfo,
            portrait_info = portraitInfo,
            bucket_info = bucketInfo,
            user_status_info = userStatusInfo,
            extra_request_head = extraRequestHeadMap,
            env_info = envInfo,
            refresh_token_request_v2 = refreshTokenRequestV2,
            refresh_request = refreshRequest,
        )
    }

    private fun createPortraitInfo(): List<PortraitInfo> {
        // TODO 用户画像信息待后续补充
        val portraitInfoList: List<PortraitInfo> = ArrayList<PortraitInfo>()
        return portraitInfoList
    }

    private fun logI(content: String) {
        VBPBLog.i(VBPBLog.HEADER_PACKAGE, "$TAG $content")
    }


    //  地理信息
    private fun createLocationInfo(): LocationInfo = LocationInfo()

    // 创建 票据信息ByteString对象
    private fun createTokenValue(tokenString: String?): ByteString {
        return if (tokenString.isNullOrEmpty()) {
            ByteString.EMPTY
        } else {
            tokenString.encodeUtf8()
        }
    }

    // 设备信息
    private fun createDeviceInfo(): DeviceInfo {
        val width = VBPBDeviceInfo.screenWidth
        val height = VBPBDeviceInfo.screenHeight
        val dpi = VBPBDeviceInfo.densityDpi
        val deviceId = VBPBDeviceInfo.deviceId
        val deviceModel = VBPBDeviceInfo.deviceModel
        val manufacturer = VBPBDeviceInfo.manufacturer
        val deviceType = VBPBDeviceInfo.deviceType
        val omgId = VBPBConfig.getOmgId()
        val guid = VBPBConfig.getGuid()
        val maxUISize = UISizeType.fromValue(VBPBDeviceInfo.getMaxUiSizeByUiSizeType())
            ?: UISizeType.UISizeType_REGULAR
        val curUISize = UISizeType.fromValue(VBPBDeviceInfo.getCurrentWindowUiSizeByUiSizeType())
            ?: UISizeType.UISizeType_REGULAR
        val qimei = VBPBConfig.getQIMEI()
        val oaid = VBPBConfig.getOAID()
        val caid = VBPBConfig.getCAID()
        val caidVersion = VBPBConfig.getCAIDVersion()
        return DeviceInfo(
            screen_width = width,
            screen_height = height,
            density_dpi = dpi,
            device_id = deviceId,
            device_model = deviceModel,
            manufacturer = manufacturer,
            device_type = deviceType,
            omg_id = omgId,
            guid = guid,
            max_uiSize = maxUISize,
            current_window_uiSize = curUISize,
            qimei = qimei,
            oaid = oaid,
            caid = caid,
            caid_version = caidVersion,
        )
    }

    // 版本信息
    private fun createVersionInfo(): VersionInfo {
        val versionName = VBPBVersionInfo.getVersionName()
        val versionCode = VBPBVersionInfo.getVersionCode().toString()
        val platform = VBPBVersionInfo.getPlatformId()
        val platformVersion = VBPBVersionInfo.getPlatformVersion()
        val appId = VBPBVersionInfo.getAppId().toString()
        val channelId = VBPBVersionInfo.getChannelId().toString()
        val appNameId = VBPBVersionInfo.getAppNameId()
        val vnviewPkgVersion = VBPBConfig.getVNVersion()
        return VersionInfo(
            version_name = versionName,
            version_code = versionCode,
            platform = platform,
            platform_version = platformVersion,
            app_id = appId,
            channel_id = channelId,
            app_name_id = appNameId,
            vnview_pkg_version = vnviewPkgVersion
        )
    }

    // 网络信息
    private fun createNetworkInfo(): NetworkInfo {
        // 获取 IVBTransportService 实例
        val transportService = getIVBTransportService()
        // 从 IVBTransportService 获取网络类型
        val rawNetworkType = transportService.getNetworkType()
        // 将鸿蒙端网络类型转换为内部网络类型
        val internalNetworkType = convertToNetworkType(rawNetworkType)
        // 获取最终的网络类型
        var networkType = getNetworkType(internalNetworkType)
        // 如果当前平台未实现网络类型获取，从外部注入获取
        if (internalNetworkType == VBPBNetworkState.NETWORK_STATE_UNKNOWN) {
            networkType = getNetworkType(VBPBConfig.getNetworkState())
        }
        logI("[createNetworkInfo] get networkType: $networkType")
        val ip  = VBPBConfig.getClientIPv4()
        logI("[createNetworkInfo] get ip: $ip")
        val mobileIsp = VBPBConfig.getOperator()
        logI("[createNetworkInfo] get mobileIsp: $mobileIsp")
        return NetworkInfo(
            network_mode = networkType,
            ip = ip,
            mobile_isp = mobileIsp
        )
    }

    // 审核/调试标示
    private fun createFlagInfo(): FlagInfo {
        val vbFlagInfo: VBPBFlagInfo = VBPBPersonalize.getFlagInfo()
        return FlagInfo(
            check_flag = if (vbFlagInfo.mIsChecking) 1 else 0,
            debug_flag = if (vbFlagInfo.mIsDebugEnabled) 1 else 0
        )
    }

    // 分桶信息
    private fun createBucketInfo(): BucketInfo? {
        val bucketInfo: VBPBBucketInfo? = VBPBPersonalize.getBucketInfo()
        return if (bucketInfo == null) BucketInfo() else BucketInfo(
            extra = bucketInfo.mExtra ?: "",
            bucket_id = bucketInfo.mBucketId
        )
    }

    // 创建请求unique id
    fun createUniqueId(requestId: Int): String {
        var uniqueIdPrefix: String? = getUniqueIdPrefix()
        if (uniqueIdPrefix.isNullOrEmpty()) {
            val guid: String? = VBPBConfig.getGuid()
            uniqueIdPrefix = createUniqueIdPrefix(if (guid.isNullOrEmpty()) "default" else guid)
        }
        return uniqueIdPrefix + requestId
    }

    private fun getUniqueIdPrefix(): String = ""

    // 拼接unique id 前缀
    // 此处需要用日期的时间戳，便于协助后台排查问题
    private fun createUniqueIdPrefix(startStr: String): String =
        startStr + "@" + getTimestamp() + "@"

    companion object {
        const val TAG = "VBPBHeaderConfig"

        /**
         * 更新网络类型
         *
         * @param networkType 网络类型
         */
        fun getNetworkType(networkType: VBPBNetworkState): Int {
            return if (networkType === VBPBNetworkState.NETWORK_STATE_WIFI) {
                3
            } else if (networkType === VBPBNetworkState.NETWORK_STATE_5G) {
                6
            } else if (networkType === VBPBNetworkState.NETWORK_STATE_4G) {
                5
            } else if (networkType === VBPBNetworkState.NETWORK_STATE_3G) {
                2
            } else if (networkType === VBPBNetworkState.NETWORK_STATE_2G) {
                1
            } else {
                4
            }
        }

        fun convertToNetworkType(networkType: Int): VBPBNetworkState {
            return when (networkType) {
                VBNetworkState.NETWORK_STATE_DISCONNECT.get() ->
                    VBPBNetworkState.NETWORK_STATE_DISCONNECT

                VBNetworkState.NETWORK_STATE_UNKNOWN.get() ->
                    VBPBNetworkState.NETWORK_STATE_UNKNOWN

                VBNetworkState.NETWORK_STATE_2G.get() ->
                    VBPBNetworkState.NETWORK_STATE_2G

                VBNetworkState.NETWORK_STATE_3G.get() ->
                    VBPBNetworkState.NETWORK_STATE_3G

                VBNetworkState.NETWORK_STATE_4G.get() ->
                    VBPBNetworkState.NETWORK_STATE_4G

                VBNetworkState.NETWORK_STATE_5G.get() ->
                    VBPBNetworkState.NETWORK_STATE_5G

                VBNetworkState.NETWORK_STATE_WIFI.get() ->
                    VBPBNetworkState.NETWORK_STATE_WIFI

                else -> {
                    logI("get illegal networkType $networkType")
                    VBPBNetworkState.NETWORK_STATE_UNKNOWN
                }
            }
        }

        private fun logI(content: String) {
            VBPBLog.i(VBPBLog.HEADER_PACKAGE, "$TAG $content")
        }

    }
}
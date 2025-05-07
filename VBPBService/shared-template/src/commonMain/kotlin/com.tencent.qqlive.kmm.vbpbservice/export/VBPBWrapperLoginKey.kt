package com.tencent.qqlive.kmm.vbpbservice.export

/**
 * Copyright (c) 2024 Tencent. All rights reserved
 *
 * 与登录组件交互的Key
 *
 * @author haibarawang
 * @date 2024/3/1 19:41
 */
object VBPBWrapperLoginKey {
    // 视频侧用户id String
    const val VB_WRAPPERLOGINSERVICE_VIDEOUSERID = "vb_wrapperloginservice_videouserid"

    // 视频票据 String
    const val VB_WRAPPERLOGINSERVICE_VIDEOSESSION = "vb_wrapperloginservice_videosession"

    // 渠道侧用户id String
    const val VB_WRAPPERLOGINSERVICE_CHANNELUSERID = "vb_wrapperloginservice_channeluserid"

    // 渠道侧登录票据 String
    const val VB_WRAPPERLOGINSERVICE_CHANNELACCESSTOKEN =
        "vb_wrapperloginservice_channelaccesstoken"

    // 已完成登录的渠道侧appid String
    const val VB_WRAPPERLOGINSERVICE_LOGGEDCHANNELAPPID =
        "vb_wrapperloginservice_loggedchannelappid"

    // 登录信息 Map<String,String>
    const val VB_WRAPPERLOGINSERVICE_ATOMICINFO = "vb_wrapperloginservice_atomicinfo"

    // !账号类型信息 String
    const val KEY_ACCOUNT_PATTERN = "vb_wrapperloginservice_account_type"

    // !副登录的userid
    const val VB_WRAPPERLOGINSERVICE_ASSIST_CHANNELUSERID =
        "vb_wrapperloginservice_assist_channeluserid"

    // !副登录的token
    const val VB_WRAPPERLOGINSERVICE_ASSIST_CHANNELACCESSTOKEN =
        "vb_wrapperloginservice_assist_channelaccesstoken"

    // !副登录的 APPID
    const val VB_WRAPPERLOGINSERVICE_ASSIST_LOGGEDCHANNELAPPID =
        "vb_wrapperloginservice_assist_loggedchannelappid"
}
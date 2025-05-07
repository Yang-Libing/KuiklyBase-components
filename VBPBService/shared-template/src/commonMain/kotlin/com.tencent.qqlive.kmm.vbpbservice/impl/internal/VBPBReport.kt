package com.tencent.qqlive.kmm.vbpbservice.impl.internal

import co.touchlab.stately.collections.ConcurrentMutableMap
import com.tencent.kmm.requestdegradation.export.RequestDegradationManager
import com.tencent.qqlive.kmm.vbpbservice.export.IVBPBReport
import com.tencent.qqlive.kmm.vbpbservice.export.VBPBReportInfo
import com.tencent.qqlive.kmm.vbpbservice.export.VBPBResultCode
import kotlinx.serialization.json.JsonObjectBuilder
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.buildJsonObject
import kotlin.random.Random

/**
 * Copyright (c) 2024 Tencent. All rights reserved
 *
 * PB 上报代理
 *
 * @author haibarawang
 * @date 2024/3/1 19:41
 */
object VBPBReport {

    // 上报实现
    var reportImpl: IVBPBReport? = null


    private const val DEFAULT_SUCCESS_SAMPLE_RATE = 1000
    private const val DEFAULT_FAILURE_SUCCESS_SAMPLE_RATE = 200

    // 上报统计信息
    fun report(reportInfo: VBPBReportInfo) {
        reportImpl?.let {
            val cmd = RequestDegradationManager.getRPCRequestName(
                reportInfo.callee,
                reportInfo.func,
                reportInfo.serviceTag
            )
            val isSuccess = reportInfo.businessErrorCode == VBPBResultCode.CODE_OK
            val pendingSampleRate = it.reportSampleRate(cmd, isSuccess)
            val defaultSampleRate = if (isSuccess) DEFAULT_SUCCESS_SAMPLE_RATE else DEFAULT_FAILURE_SUCCESS_SAMPLE_RATE
            val sampleRate = if (pendingSampleRate >= 0) pendingSampleRate else defaultSampleRate
            if (sampleRate == 0) { // 为 0 说明不上报
                return
            }
            // 生成 0 到 sampleRate - 1 的随机数
            val randomValue = Random.nextInt(0, sampleRate)
            if (randomValue == 0) {
                val reportMap = convert(reportInfo, cmd, sampleRate)
                it.report(reportMap)
            }
        }
    }

    // 将VBPBReportInfo拼装为Map
    private fun convert(reportInfo: VBPBReportInfo, cmd: String, sampleRate: Int): Map<String, String> {
        val map = ConcurrentMutableMap<String, String>()

        // -------- cmd属性信息 --------
        putValidData("vb_request_id", reportInfo.requestId, map) // 请求id
        putValidData(
            "vb_cmd", cmd, map
        )  // cmd

        // -------- cmd执行信息 --------
        putValidData("vb_ret_code", reportInfo.errorCode, map) // 结果码
        putValidData("vb_err_des", reportInfo.errorMessage, map) // 错误描述
        putValidData("vb_err_type", reportInfo.errorType, map) // 错误类型

        putValidData("vb_request_size", reportInfo.requestPackageLength, map) // 业务层请求数据长度
        putValidData("vb_response_size", reportInfo.responsePackageLength, map) // 业务层响应数据长度
        putValidData("vb_net_type", reportInfo.netType, map) // 请求时网络类型
        reportInfo.transportReportInfo?.let {
            putValidData("vb_targetIp", it.targetIp, map) // 后台目标ip
            putValidData("vb_ip_type", it.ipType, map) // IP 类型
            putValidData("vb_total_cost", it.totalCost, map) // 传输层耗时
            putValidData("vb_upstream_size", it.upstreamSize, map) // 网络层请求数据长度
            putValidData("vb_downstream_size", it.downstreamSize, map) // 网络层响应数据长度
        }
        putValidData("vb_request_total_cost", reportInfo.totalCost, map) // 请求耗时
        putValidData("vb_send_time", reportInfo.startTs, map) // 请求开始时间
        val vbExecuteExtra = buildJsonObject {
            reportInfo.transportReportInfo?.let {
                putValidStringJsonPrimitive("url", it.url, this) // 请求url
                putValidStringJsonPrimitive("domain", it.domain, this) // 请求域名
                putValidStringJsonPrimitive("dns_cost", it.dnsCost, this) // DNS 耗时
                putValidStringJsonPrimitive("conn_cost", it.connCost, this) // Socket 链接创建耗时
                putValidStringJsonPrimitive("ssl_cost", it.sslCost, this) // TLS 链接创建耗时
                putValidStringJsonPrimitive("queue_cost", it.queueCost, this) // 队列等待耗时
                putValidStringJsonPrimitive("send_cost", it.sendCost, this) // 发送耗时
                putValidStringJsonPrimitive("first_byte_cost", it.firstByteCost, this) // 首字节耗时
                putValidStringJsonPrimitive("first_byte_cost_new", it.firstByteCostNew, this) // 首字节耗时
                putValidStringJsonPrimitive("recv_cost", it.recvCost, this) // 接收耗时
                putValidStringJsonPrimitive("pre_transfer_cost", it.preTransferTime, this) // 传输准备时间耗时
                putValidStringJsonPrimitive("redirect_cost", it.redirectTime, this) // 重定向耗时
                putValidStringJsonPrimitive("trans_protocol", it.transProtocol, this) // 传输协议类型
                putValidStringJsonPrimitive("trans_protocol_strategy", it.transProtocolStrategy, this)
                putValidStringJsonPrimitive("trans_degradation_type", it.transDegradationType, this)
                putValidStringJsonPrimitive("reused_connection", it.reusedConnection, this) // 是否复用连接
                putValidStringJsonPrimitive("local_net_stack_type", it.localNetStackType, this)
                putValidStringJsonPrimitive("use_net_card_type", it.useNetCardType, this) // 使用的网络卡类型
                putValidStringJsonPrimitive("resp_content_type", it.respContentType, this)
                putValidStringJsonPrimitive("http_status_code", it.httpStatusCode, this)
                putValidStringJsonPrimitive("is_https", it.isHttps, this)
                putValidStringJsonPrimitive("http_ver", it.httpVer, this)
                putValidStringJsonPrimitive("tls_ver", it.tlsVer, this)
                putValidStringJsonPrimitive("use_quic_client", it.useQuicClient, this)
                putValidStringJsonPrimitive("disable_quic_reason", it.disableQuicReason, this)
                putValidStringJsonPrimitive("use_curl", it.useCurl, this)
            }
            putValidStringJsonPrimitive("discontent_reason", reportInfo.discontentReason, this)
        }
        map["vb_execute_extra"] = vbExecuteExtra.toString()

        // -------- 业务扩展信息 --------
        putValidData("vb_busi_proto_type", "2", map) // tRPC +PB 一定是 2
        putValidData("vb_business_code", reportInfo.businessErrorCode, map)
        putValidData("vb_business_error_des", reportInfo.businessErrorMessage, map)
        putValidData("vb_business_error_type", reportInfo.businessErrorType, map)
        putValidData("vb_pack_cost", reportInfo.packageCost, map) // 打包整体耗时
        putValidData("vb_unpack_cost", reportInfo.unpackageCost, map) // 解包整体耗时
        val vbBusinessExtra = buildJsonObject {
            putValidStringJsonPrimitive("pack_head_cost", reportInfo.packageHeaderCost, this)
            putValidStringJsonPrimitive("pack_pb_frame_cost", reportInfo.packagePBFrameCost, this)
            putValidStringJsonPrimitive("unpack_head_cost", reportInfo.unpackageHeaderCost, this)
            putValidStringJsonPrimitive("unpack_pd_frame_cost", reportInfo.unpackagePBFrameCost, this)
            putValidStringJsonPrimitive("callee", reportInfo.callee, this)
            putValidStringJsonPrimitive("func", reportInfo.func, this)
            // serviceTag 兼容多端处理（历史遗留问题）
            putValidStringJsonPrimitive("ServiceTag", reportInfo.serviceTag, this)
            putValidStringJsonPrimitive("serviceTag", reportInfo.serviceTag, this)
            putValidStringJsonPrimitive("service_tag", reportInfo.serviceTag, this)
            putValidStringJsonPrimitive("auto_retry_flag", reportInfo.autoRetryFlag, this)
            putValidStringJsonPrimitive("need_retry_flag", reportInfo.needRetryFlag, this)
            putValidStringJsonPrimitive("kmp_flag", "1", this)
            reportInfo.pageParams?.forEach {
                putValidStringJsonPrimitive("p_${it.key}", it.value, this)
            }
        }
        map["vb_business_extra"] = vbBusinessExtra.toString()
        map["vb_net_weak_level"] = "0" // 暂时默认为0，未探测
        map["vb_report_ratio"] = sampleRate.toString()
        return map
    }

    private fun putValidData(key: String, value: String?, map: ConcurrentMutableMap<String, String>) {
        if (!value.isNullOrEmpty()) {
            map[key] = value
        }
    }

    private fun putValidData(key: String, value: Int?, map: ConcurrentMutableMap<String, String>) {
        value?.let {
            map[key] = value.toString()
        }
    }

    private fun putValidData(key: String, value: Long?, map: ConcurrentMutableMap<String, String>) {
        value?.let {
            map[key] = value.toString()
        }
    }

    private fun putValidStringJsonPrimitive(key: String, value: String?, builder: JsonObjectBuilder) {
        value?.let {
            builder.put(key, JsonPrimitive(value))
        }
    }

    private fun putValidStringJsonPrimitive(key: String, value: Long, builder: JsonObjectBuilder) {
        builder.put(key, JsonPrimitive(value.toString()))
    }

    private fun putValidStringJsonPrimitive(key: String, value: Int, builder: JsonObjectBuilder) {
        builder.put(key, JsonPrimitive(value.toString()))
    }

    private fun putValidStringJsonPrimitive(key: String, value: Boolean, builder: JsonObjectBuilder) {
        builder.put(key, JsonPrimitive(value.toString()))
    }

}
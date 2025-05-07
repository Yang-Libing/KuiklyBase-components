package com.tencent.qqlive.kmm.vbpbservice.impl.internal

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.encodeToJsonElement
import kotlinx.serialization.json.int
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import kotlin.experimental.ExperimentalObjCRefinement
import kotlin.native.HiddenFromObjC

/**
 * Copyright (c) 2024 Tencent. All rights reserved
 *
 * JSON 工具类
 *
 * @author haibarawang
 * @date 2024/3/1 19:41
 */
private const val TAG = "JsonUtils"

@Throws(Exception::class)
fun String.parseToJsonObject(): JsonObject {
    return try {
        Json.parseToJsonElement(this).jsonObject
    } catch (e: Exception) {
        VBPBLog.i(TAG, "parseToJsonObject error: $e")
        throw e
    }
}

fun Map<String, JsonPrimitive>.encodeToJsonString() = Json.encodeToJsonElement(this).toString()

@OptIn(ExperimentalObjCRefinement::class)
@HiddenFromObjC
fun Number.toJsonPrimitive() = JsonPrimitive(this)

fun String.toJsonPrimitive() = JsonPrimitive(this)

fun JsonObject.optInt(key: String) = this[key]?.jsonPrimitive?.int ?: 0

fun JsonObject.optString(key: String) = this[key]?.jsonPrimitive?.content ?: ""

fun JsonObject.optJSONArray(key: String) = this[key]?.jsonArray ?: JsonArray(emptyList())

fun JsonArray.getString(index: Int) = this[index].jsonPrimitive.content

fun JsonArray.getInt(index: Int) = this[index].jsonPrimitive.int

fun JsonArray.getJSONObject(index: Int) = this[index].jsonObject

package com.tencent.qqlive.kmm.vbpbservice.impl.internal

import kotlinx.datetime.Clock

actual fun getPlatformTimeStamp(): Long = Clock.System.now().toEpochMilliseconds()
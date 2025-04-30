package com.tencent.tmm.knoi.benchmark

import com.tencent.tmm.knoi.annotation.KNExport
import com.tencent.tmm.knoi.logger.info
import com.tencent.tmm.knoi.type.toByteArray
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import platform.posix.uint8_tVar
import kotlin.time.DurationUnit

val mutMap = mutableMapOf<String, Int>().also {
    for (i in 0..100) {
        it["$i"] = i
    }
}

val mutList = mutableListOf<Int>().also {
    for (i in 0..1000) {
        it.add(i)
    }
}

val string: String = run {
    StringBuilder().also {
        for (i in 0..1000) {
            it.append("a")
        }
    }.toString()
}

@OptIn(ExperimentalForeignApi::class)
@KNExport
fun oneKeyBenchMark() {
    info("---bench  main thread call ---")
    mainThreadCall()
    info("---bench  main thread call ForWord---")
    mainThreadCallForWord()
    info("---bench  sub thread call ---")
    subThreadCall()
}

fun subThreadCall() {
    GlobalScope.launch {
        mainThreadCall()
    }
}

val BenchMarkConsumerApi =  getBenchMarkConsumerApi()
@OptIn(ExperimentalForeignApi::class)
fun mainThreadCall() {
    bench1KBlock("benchUnit") {
        BenchMarkConsumerApi.benchUnit()
    }
    bench1KBlock("benchUnit") {
        BenchMarkConsumerApi.benchUnit()
    }
    bench1KBlock("benchBoolean") {
        BenchMarkConsumerApi.benchBoolean(true)
    }
    bench1KBlock("benchInt") {
        BenchMarkConsumerApi.benchInt(1)
    }
    bench1KBlock("benchDouble") {
        BenchMarkConsumerApi.benchDouble(1.0)
    }
    bench1KBlock("benchLong") {
        BenchMarkConsumerApi.benchLong(1L)
    }

    bench1KBlock("benchString size 10") {
        BenchMarkConsumerApi.benchString("aaaaaaaaaa")
    }


    bench1KBlock("benchString size 1000") {
        BenchMarkConsumerApi.benchString(string)
    }

    bench10Block("benchList 1000 size int array") {
        BenchMarkConsumerApi.benchList(mutList)
    }
    bench1KBlock("benchJSCallback") {
        BenchMarkConsumerApi.benchJSCallback {

        }
    }

    bench10Block("benchMap 100 size string to int") {
        BenchMarkConsumerApi.benchMap(mutMap)
    }
    bench10Block("benchArrayBuffer") {
        val result = BenchMarkConsumerApi.benchArrayBuffer()
        val data = result.getData<uint8_tVar>()?.toByteArray(result.getCount().toInt())
    }
}

val BenchMarkConsumerForWordApi = getBenchMarkConsumerForWordApi()
@OptIn(ExperimentalForeignApi::class)
fun mainThreadCallForWord() {
    bench1KBlock("benchUnit") {
        BenchMarkConsumerForWordApi.benchUnit()
    }
    bench1KBlock("benchUnit") {
        BenchMarkConsumerForWordApi.benchUnit()
    }
    bench1KBlock("benchBoolean") {
        BenchMarkConsumerForWordApi.benchBoolean(true)
    }
    bench1KBlock("benchBoolean") {
        BenchMarkConsumerForWordApi.benchBoolean(true)
    }
    bench1KBlock("benchInt") {
        BenchMarkConsumerForWordApi.benchInt(1)
    }
    bench1KBlock("benchDouble") {
        BenchMarkConsumerForWordApi.benchDouble(1.0)
    }
    bench1KBlock("benchLong") {
        BenchMarkConsumerForWordApi.benchLong(1L)
    }

    bench1KBlock("benchString size 10") {
        BenchMarkConsumerForWordApi.benchString("aaaaaaaaaa")
    }

    bench1KBlock("benchString size 1000") {
        BenchMarkConsumerForWordApi.benchString(string)
    }

    bench10Block("benchList 1000 size int array") {
        BenchMarkConsumerForWordApi.benchList(mutList)
    }
    bench1KBlock("benchJSCallback") {
        BenchMarkConsumerForWordApi.benchJSCallback {

        }
    }

    bench10Block("benchMap 100 size string to int") {
        BenchMarkConsumerForWordApi.benchMap(mutMap)
    }
}

fun bench1KBlock(type: String, block: () -> Unit): Int {
    val start = Clock.System.now()
    for (i in 0 until 1000) {
        block()
    }
    val end = Clock.System.now()
    val duration = end.minus(start)
    val ave = duration / 1000
    info("---bench10KBlock $type ave cost :$ave  ---")
    return ave.toInt(DurationUnit.NANOSECONDS)
}

fun bench10Block(type: String, block: () -> Unit): Int {
    val start = Clock.System.now()
    for (i in 0 until 10) {
        block()
    }
    val end = Clock.System.now()
    val duration = end.minus(start)
    val ave = duration / 10
    info("---bench10Block $type ave cost :$ave  ---")
    return ave.toInt(DurationUnit.NANOSECONDS)
}

package com.tencent.qqlive.kmm.vbpbservice.impl.platform

import android.content.Context
import android.util.Log
import com.tencent.qqlive.kmm.vbpbservice.export.IVBPBLog
import com.tencent.qqlive.kmm.vbpbservice.service.VBPBServiceInitHelper
import com.tencent.qqlive.modules.vb.kv.export.OnValueChangeListener
import com.tencent.qqlive.modules.vb.transportservice.impl.IVBTransportExecutors
import com.tencent.qqlive.modules.vb.transportservice.impl.IVBTransportKV
import com.tencent.qqlive.modules.vb.transportservice.impl.IVBTransportLog
import com.tencent.qqlive.modules.vb.transportservice.impl.VBTransportInitConfig
import com.tencent.qqlive.modules.vb.transportservice.impl.VBTransportInitTask
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

object PBDebugInitTask {
    private val mExecutors: ExecutorService = Executors.newCachedThreadPool()

    fun init(context: Context) {
        initTransportService(context)
        initPBService()
    }

    private fun initTransportService(context: Context) {
        // 日志实现
        val logImpl: IVBTransportLog = object : IVBTransportLog {
            override fun d(tag: String, content: String?) {
                Log.d(tag, content ?: "")
            }

            override fun i(tag: String, content: String?) {
                Log.i(tag, content ?: "")
            }

            override fun e(tag: String, content: String?) {
                Log.e(tag, content ?: "")
            }

            override fun e(tag: String, content: String?, throwable: Throwable?) {
                Log.e(tag, "$content, ${throwable?.message}")
                throwable?.printStackTrace()
            }
        }

        // 线程池实现
        val executorsImpl = IVBTransportExecutors { runnable: Runnable? ->
            mExecutors.execute(
                runnable
            )
        }

        // KV实现
        val kvImpl: IVBTransportKV = object : IVBTransportKV {
            override fun registerListener(onValueChangeListener: OnValueChangeListener) = false

            override fun put(key: String, value: Boolean, crossProcessNotify: Boolean) {
            }

            override fun put(key: String, value: String, crossProcessNotify: Boolean) {
            }
        }

        val initConfig = VBTransportInitConfig.Builder()
            .setExecutosImpl(executorsImpl)
            .setLogImpl(logImpl)
            .setKVImpl(kvImpl)
            .setContext(context)
            .build()

        VBTransportInitTask.init(initConfig)
    }

    private fun initPBService() {
        val logImpl = object : IVBPBLog {
            override fun d(tag: String?, content: String?) {
                Log.d(tag, content!!)
            }

            override fun i(tag: String?, content: String?) {
                Log.i(tag, content!!)
            }

            override fun e(tag: String?, content: String?, throwable: Throwable?) {
                Log.e(tag, content!!)
            }
        }
        VBPBServiceInitHelper.debugInit(logImpl)
    }
}
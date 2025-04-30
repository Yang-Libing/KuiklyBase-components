package com.tencent.tmm.knoi.register

import com.tencent.tmm.knoi.exception.ServiceRegisterException
import com.tencent.tmm.knoi.logger.debug
import com.tencent.tmm.knoi.logger.info
import com.tencent.tmm.knoi.logger.isDebug
import com.tencent.tmm.knoi.mainTid
import platform.ohos.knoi.get_tid

class ServiceProxyLoadBalancer {

    private var serviceWrapMain : ServiceWrapper? = null
    private var serviceWrapWorker : ServiceWrapper? = null
    fun provide(serviceWrapper: ServiceWrapper) {
        if (get_tid() == mainTid){
            serviceWrapMain = serviceWrapper
        }else{
            if (serviceWrapWorker != null){
                info("${serviceWrapper.name} had been register by worker Thread ${serviceWrapWorker?.registerTid}")
                return
            }
            serviceWrapWorker = serviceWrapper
        }
    }

    fun getBalanceWrapper(): ServiceWrapper? {
        val finalWrapper = if (get_tid() == mainTid){
            serviceWrapMain
        }else{
            serviceWrapWorker ?: serviceWrapMain
        }
        if (isDebug){
            if (get_tid() == mainTid){
                debug("getBalanceWrapper main thread ${get_tid()} get wrapper ${finalWrapper?.name} use ${finalWrapper?.registerTid} thread wrapper mainTid=$mainTid")
            }else{
                debug("getBalanceWrapper sub thread ${get_tid()} get wrapper ${finalWrapper?.name} use ${finalWrapper?.registerTid} thread wrapper mainTid=$mainTid")
            }
        }
        return finalWrapper
    }
}
//
//  AppDelegate.swift
//  iosApp
//
//  Created by minqidu on 2024/7/12.
//  Copyright © 2024 orgName. All rights reserved.
//

import UIKit
import shared_template
import VBLogServiceiOS
import RAFT
import VBTransportServiceiOS

class Logger: IVBPBLog {
    func pb_d(tag: String?, content: String?) {
        print("[DEBUG][\(tag ?? "DEFAULT")] \(content ?? "")")
    }
    
    func pb_e(tag: String?, content: String?, throwable: KotlinThrowable?) {
        print("[ERROR][\(tag ?? "DEFAULT")] \(content ?? "")")
    }
    
    func pb_i(tag: String?, content: String?) {
        print("[INFO][\(tag ?? "DEFAULT")] \(content ?? "")")
    }
    
    
}

class AppDelegate: NSObject, UIApplicationDelegate {
    
    func application(_ application: UIApplication,
    willFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey : Any]? = nil) -> Bool {
        let services: [String:String] = [
            "IVBLogService": "VBLogService",
            "IVBKVService": "VBKVService",
            "IVBTransportService": "VBTransportService",
            "IVBUniCmdService": "VBUniCmdService",
            "IVBNetStateService": "VBNetStateService",
            "IVBOpenTelemetry": "VBOpenTelemetryService",
        ]
        services.forEach { (key, value) in
            let proto = NSProtocolFromString(key)
            let clz: AnyClass? = NSClassFromString(value)
            if let proto = proto, let clz = clz {
                RAFTServiceRouteManager.sharedInstance().registerService(proto, implClass: clz)
            }
        }
        VBPBServiceInitHelper.shared.debugInit(logImpl: Logger())
        VBLogService.start(withFolderPath: "/log/newlog/", isDebug: true)
        VBLog.appDidFinshLaunch()
        RAFTService.sharedInstance().getByName("IVBKVService", withUserInfo: ["name": "MainKV"])
        let transportService =  RAFTService.sharedInstance().getByName("IVBTransportService") as! VBTransportServiceiOS.IVBTransportService
        transportService.startQuicUsability()
        transportService.registerQuicUsabilityItem(withHost: "i.video.qq.com", reachabilityProbeOptions: .whenNetworkStateChanged)
        return true
    }
    
}

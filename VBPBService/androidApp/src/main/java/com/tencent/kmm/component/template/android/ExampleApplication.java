package com.tencent.kmm.component.template.android;

import android.app.Application;

import com.tencent.qqlive.kmm.vbpbservice.impl.platform.PBDebugInitTask;

public class ExampleApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        // PB 组件初始化
        PBDebugInitTask.INSTANCE.init(this);
    }

}

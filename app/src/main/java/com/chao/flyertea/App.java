package com.chao.flyertea;

import android.app.Application;

import com.chao.flyertea.util.XCrashHandlerUtils;

public class App extends Application {
    private static App singleton;

    public static App getInstance() {
        return singleton;
    }

    @Override
    public final void onCreate() {
        super.onCreate();
        singleton = this;
        XCrashHandlerUtils.getInstance().init(this);
    }
}

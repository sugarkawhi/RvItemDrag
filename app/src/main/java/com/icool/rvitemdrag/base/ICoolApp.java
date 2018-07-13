package com.icool.rvitemdrag.base;

import android.app.Application;

/**
 * @author zhzy
 * @Description Created by ZhaoZongyao on 2018/7/13.
 */
public class ICoolApp extends Application {
    private static ICoolApp mICoolApp;

    @Override
    public void onCreate() {
        super.onCreate();
        mICoolApp = this;
    }

    public static ICoolApp getInstance() {
        return mICoolApp;
    }
}

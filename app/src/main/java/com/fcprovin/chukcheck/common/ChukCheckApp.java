package com.fcprovin.chukcheck.common;

import androidx.multidex.MultiDexApplication;

public class ChukCheckApp extends MultiDexApplication {

    private static ChukCheckApp mAppContext = null;

    @Override
    public void onCreate() {
        super.onCreate();
        mAppContext = this;
    }

    public static ChukCheckApp getAppContext(){
        return mAppContext;
    }
}

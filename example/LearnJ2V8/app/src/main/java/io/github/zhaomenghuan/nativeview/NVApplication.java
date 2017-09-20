package io.github.zhaomenghuan.nativeview;

import android.app.Application;

import io.github.zhaomenghuan.nativeview.util.Helper;

public class NVApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Helper.initialize(this);
    }
}

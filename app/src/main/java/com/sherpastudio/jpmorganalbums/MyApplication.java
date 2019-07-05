package com.sherpastudio.jpmorganalbums;

import android.app.Application;
import android.content.Context;

public class MyApplication extends Application {

    public static Context sApplicationContext;

    @Override
    public void onCreate() {
        super.onCreate();
        sApplicationContext = getApplicationContext();
    }
}

package com.sherpastudio.jpmorganalbums.model.repository;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.sherpastudio.jpmorganalbums.Injection;

public class NetworkProvider implements INetworkProvider {

    private static NetworkProvider sInstance;

    public static NetworkProvider getInstance() {
        if (sInstance == null) {
            sInstance = new NetworkProvider();
        }
        return sInstance;
    }

    private NetworkProvider(){

    }

    @Override
    public boolean hasInternetConnection() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) Injection.provideApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}

package com.sherpastudio.jpmorganalbums.model.repository;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.sherpastudio.jpmorganalbums.Injection;

public class NetworkRepository implements INetworkRepository{

    private static NetworkRepository sInstance;

    public static NetworkRepository getInstance() {
        if (sInstance == null) {
            sInstance = new NetworkRepository();
        }
        return sInstance;
    }

    private NetworkRepository(){

    }

    @Override
    public boolean hasInternetConnection() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) Injection.provideApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}

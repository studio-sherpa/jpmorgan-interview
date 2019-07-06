package com.sherpastudio.jpmorganalbums.model.repository.albums.remote;

import androidx.annotation.NonNull;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyRetrofitClient implements IRetrofitClient{
    private static MyRetrofitClient sInstance;
    private final AlbumsAPIService mService;

    private MyRetrofitClient(@NonNull String url){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mService = retrofit.create(AlbumsAPIService.class);
    }

    public static MyRetrofitClient getInstance(@NonNull String url){
        if(sInstance == null) sInstance = new MyRetrofitClient(url);
        return sInstance;
    }

    @Override
    public AlbumsAPIService getService() {
        return mService;
    }
}

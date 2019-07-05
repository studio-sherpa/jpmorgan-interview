package com.sherpastudio.jpmorganalbums.model.repository.io;

import androidx.annotation.NonNull;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitAlbumsClient {
    private static RetrofitAlbumsClient sInstance;
    private final AlbumsAPIService mService;

    private RetrofitAlbumsClient(@NonNull String url){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mService = retrofit.create(AlbumsAPIService.class);
    }

    public static RetrofitAlbumsClient getInstance(@NonNull String url){
        if(sInstance == null) sInstance = new RetrofitAlbumsClient(url);
        return sInstance;
    }

    public AlbumsAPIService getService() {
        return mService;
    }
}

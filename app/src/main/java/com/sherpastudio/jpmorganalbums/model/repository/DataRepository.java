package com.sherpastudio.jpmorganalbums.model.repository;

import com.sherpastudio.jpmorganalbums.model.IDataRepository;
import com.sherpastudio.jpmorganalbums.model.data.Album;
import com.sherpastudio.jpmorganalbums.model.repository.io.RetrofitAlbumsClient;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import androidx.annotation.NonNull;
import retrofit2.Response;

public class DataRepository implements IDataRepository {

    private static DataRepository sInstance;
    private final RetrofitAlbumsClient mRetrofitAlbumsClient;

    public static DataRepository getInstance(@NonNull RetrofitAlbumsClient retrofitAlbumsClient) {
        if (sInstance == null) {
            sInstance = new DataRepository(retrofitAlbumsClient);
        }
        return sInstance;
    }

    private DataRepository(@NonNull RetrofitAlbumsClient retrofitAlbumsClient) {
        this.mRetrofitAlbumsClient = retrofitAlbumsClient;
    }

    @Override
    public List<Album> getAlbums(boolean forceReload) {
        Response<List<Album>> response;
        try {
            response = mRetrofitAlbumsClient.getService().listAllFruits().execute();

            if (response.isSuccessful()) {
                if (response.body() != null) {
                    return response.body();
                } else {
                    return Collections.emptyList();
                }
            } else {
                return null;
            }

        } catch (IOException e) {
            return null;
        }
    }
}

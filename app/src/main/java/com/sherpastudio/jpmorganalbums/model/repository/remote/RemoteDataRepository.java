package com.sherpastudio.jpmorganalbums.model.repository.remote;

import com.sherpastudio.jpmorganalbums.model.IDataRepository;
import com.sherpastudio.jpmorganalbums.model.data.Album;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import androidx.annotation.NonNull;
import retrofit2.Response;

public class RemoteDataRepository implements IDataRepository {

    private static RemoteDataRepository sInstance;
    private final RetrofitAlbumsClient mRetrofitAlbumsClient;

    public static RemoteDataRepository getInstance(@NonNull RetrofitAlbumsClient retrofitAlbumsClient) {
        if (sInstance == null) {
            sInstance = new RemoteDataRepository(retrofitAlbumsClient);
        }
        return sInstance;
    }

    private RemoteDataRepository(@NonNull RetrofitAlbumsClient retrofitAlbumsClient) {
        this.mRetrofitAlbumsClient = retrofitAlbumsClient;
    }

    @Override
    public List<Album> getAlbums() {
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

    @Override
    public void addAlbums(List<Album> albums) {}
}

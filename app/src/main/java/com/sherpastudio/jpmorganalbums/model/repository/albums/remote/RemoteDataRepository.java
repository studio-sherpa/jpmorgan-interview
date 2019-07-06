package com.sherpastudio.jpmorganalbums.model.repository.albums.remote;

import com.sherpastudio.jpmorganalbums.model.repository.albums.IDataRepository;
import com.sherpastudio.jpmorganalbums.model.data.Album;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import androidx.annotation.NonNull;
import retrofit2.Response;

public class RemoteDataRepository implements IDataRepository {

    private static RemoteDataRepository sInstance;
    private final AlbumsAPIService mService;

    public static RemoteDataRepository getInstance(@NonNull AlbumsAPIService service) {
        if (sInstance == null) {
            sInstance = new RemoteDataRepository(service);
        }
        return sInstance;
    }

    private RemoteDataRepository(@NonNull AlbumsAPIService service) {
        this.mService = service;
    }

    @Override
    public List<Album> getAlbums() {
        Response<List<Album>> response;
        try {
            response = mService.listAllFruits().execute();

            if (response.isSuccessful()) {
                if (response.body() != null) {
                    return response.body();
                } else {
                    return Collections.emptyList();
                }
            } else {
                return null;
            }

        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void addAlbums(List<Album> albums) {}

    @Override
    public Album getSingleAlbum(long albumId) {
        return null;
    }
}

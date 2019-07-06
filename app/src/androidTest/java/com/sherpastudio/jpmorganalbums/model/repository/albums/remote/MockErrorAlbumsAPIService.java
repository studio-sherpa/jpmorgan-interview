package com.sherpastudio.jpmorganalbums.model.repository.albums.remote;

import com.sherpastudio.jpmorganalbums.model.data.Album;

import java.util.List;

import okhttp3.MediaType;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.mock.BehaviorDelegate;
import retrofit2.mock.Calls;

public class MockErrorAlbumsAPIService implements AlbumsAPIService {

    private final BehaviorDelegate<AlbumsAPIService> delegate;

    public MockErrorAlbumsAPIService(BehaviorDelegate<AlbumsAPIService> service) {
        this.delegate = service;
    }

    @Override
    public Call<List<Album>> listAllFruits() {
        MediaType mediaType = MediaType.parse("application/json");
        Response<List<Album>> response = Response.error(404, okhttp3.ResponseBody.create(mediaType, ""));
        Call<List<Album>> responseCall = Calls.response(response);
        return delegate.returning(responseCall).listAllFruits();
    }
}

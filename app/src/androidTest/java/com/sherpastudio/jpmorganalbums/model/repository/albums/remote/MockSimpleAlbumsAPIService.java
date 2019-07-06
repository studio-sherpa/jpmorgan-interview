package com.sherpastudio.jpmorganalbums.model.repository.albums.remote;

import com.sherpastudio.jpmorganalbums.TestUtils;
import com.sherpastudio.jpmorganalbums.model.data.Album;

import java.util.List;

import retrofit2.Call;
import retrofit2.mock.BehaviorDelegate;

public class MockSimpleAlbumsAPIService implements AlbumsAPIService {

    private final BehaviorDelegate<AlbumsAPIService> delegate;

    public MockSimpleAlbumsAPIService(BehaviorDelegate<AlbumsAPIService> service) {
        this.delegate = service;
    }

    @Override
    public Call<List<Album>> listAllFruits() {
        return delegate.returningResponse(TestUtils.createListOfAlbums()).listAllFruits();
    }
}

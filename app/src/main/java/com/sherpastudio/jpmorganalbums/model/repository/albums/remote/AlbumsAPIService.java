package com.sherpastudio.jpmorganalbums.model.repository.albums.remote;

import com.sherpastudio.jpmorganalbums.model.data.Album;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface AlbumsAPIService {
    @GET("albums")
    Call<List<Album>> listAllFruits();
}

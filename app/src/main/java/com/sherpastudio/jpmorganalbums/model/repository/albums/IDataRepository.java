package com.sherpastudio.jpmorganalbums.model.repository.albums;

import com.sherpastudio.jpmorganalbums.model.data.Album;

import java.util.List;

public interface IDataRepository {
    List<Album> getAlbums();

    void addAlbums(List<Album> albums);

    Album getSingleAlbum(long albumId);
}

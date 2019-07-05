package com.sherpastudio.jpmorganalbums.model;

import com.sherpastudio.jpmorganalbums.model.data.Album;

import java.util.List;

public interface IDataRepository {
    List<Album> getAlbums(boolean forceReload);
}

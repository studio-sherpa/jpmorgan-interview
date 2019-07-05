package com.sherpastudio.jpmorganalbums.model.repository;

import com.sherpastudio.jpmorganalbums.model.IDataRepository;
import com.sherpastudio.jpmorganalbums.model.data.Album;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataRepository implements IDataRepository {

    private static DataRepository sInstance;

    public static DataRepository getInstance() {
        if (sInstance == null) {
            sInstance = new DataRepository();
        }
        return sInstance;
    }

    private DataRepository(){

    }

    @Override
    public List<Album> getAlbums(boolean forceReload) {
        return new ArrayList<>(Arrays.asList(
                new Album(1, "Title"),
                new Album(2, "Hello"),
                new Album(3, "Album"),
                new Album(4, "Diego"),
                new Album(5, "Bo"),
                new Album(6, "Nice")
        ));
    }
}

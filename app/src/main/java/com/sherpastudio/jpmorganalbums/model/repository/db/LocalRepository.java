package com.sherpastudio.jpmorganalbums.model.repository.db;

import com.sherpastudio.jpmorganalbums.model.IDataRepository;
import com.sherpastudio.jpmorganalbums.model.data.Album;
import com.sherpastudio.jpmorganalbums.model.repository.db.entity.AlbumEntity;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;

public class LocalRepository implements IDataRepository {

    private static LocalRepository sInstance;
    private final AppDatabase mDatabase;

    private LocalRepository(@NonNull AppDatabase database) {
        this.mDatabase = database;
    }

    public static LocalRepository getInstance(@NonNull AppDatabase database) {
        if (sInstance == null) {
            sInstance = new LocalRepository(database);
        }
        return sInstance;
    }

    @Override
    public List<Album> getAlbums() {
        List<AlbumEntity> entities = mDatabase.albumsDao().all();
        return mapEntities(entities);
    }

    @Override
    public void addAlbums(List<Album> albums) {
        mDatabase.albumsDao().insert(mapAlbums(albums));
    }


    private AlbumEntity mapAlbum(Album album){
        return new AlbumEntity(album.getId(), album.getTitle());
    }

    private Album mapEntity(AlbumEntity entity){
        return new Album(entity.getId(), entity.getTitle());
    }


    private List<AlbumEntity> mapAlbums(List<Album> albums) {
        List<AlbumEntity> entities = new ArrayList<>(albums.size());
        for(Album album : albums){
            entities.add(mapAlbum(album));
        }
        return entities;
    }


    private List<Album> mapEntities(List<AlbumEntity> entities) {
        List<Album> albums = new ArrayList<>(entities.size());
        for(AlbumEntity entity : entities){
            albums.add(mapEntity(entity));
        }
        return albums;
    }
}

package com.sherpastudio.jpmorganalbums.model.repository.local;

import android.content.Context;

import com.sherpastudio.jpmorganalbums.TestUtils;
import com.sherpastudio.jpmorganalbums.model.data.Album;
import com.sherpastudio.jpmorganalbums.model.repository.local.db.AppDatabase;
import com.sherpastudio.jpmorganalbums.model.repository.local.db.entity.AlbumEntity;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class LocalRepositoryTest {

    private AppDatabase db;

    @Before
    public void createDB() {
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase.class).build();
    }

    @After
    public void closeDb() {
        db.close();
    }


    @Test
    public void getAlbums() {
        LocalRepository localRepository = LocalRepository.getInstance(db);
        db.albumsDao().insert(TestUtils.createListOfAlbumEntities());
        List<Album> albums = localRepository.getAlbums();
        //Order of the second array's elements is important because we are fetching data in ASC order
        assertEquals(albums, Arrays.asList(TestUtils.createAlbum1(), TestUtils.createAlbum2()));
    }

    @Test
    public void addAlbums() {
        LocalRepository localRepository = LocalRepository.getInstance(db);
        localRepository.addAlbums(TestUtils.createListOfAlbums());
        List<Album> albums = localRepository.getAlbums();
        //Order of the second array's elements is important because we are fetching data in ASC order
        assertEquals(albums, Arrays.asList(TestUtils.createAlbum1(), TestUtils.createAlbum2()));
    }

    @Test
    public void getSingleAlbum() {
        LocalRepository localRepository = LocalRepository.getInstance(db);
        Album singleAlbum = localRepository.getSingleAlbum(TestUtils.ALBUM_ID_1);
        assertNull(singleAlbum);

        db.albumsDao().insert(TestUtils.createListOfAlbumEntities());
        singleAlbum = localRepository.getSingleAlbum(TestUtils.ALBUM_ID_1);
        assertThat(singleAlbum, is(TestUtils.createAlbum1()));
    }
}
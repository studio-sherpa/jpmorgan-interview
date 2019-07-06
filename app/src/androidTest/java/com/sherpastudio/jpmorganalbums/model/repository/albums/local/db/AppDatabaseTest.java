package com.sherpastudio.jpmorganalbums.model.repository.albums.local.db;

import android.content.Context;

import com.sherpastudio.jpmorganalbums.TestUtils;
import com.sherpastudio.jpmorganalbums.model.repository.albums.local.db.dao.AlbumDao;
import com.sherpastudio.jpmorganalbums.model.repository.albums.local.db.entity.AlbumEntity;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class AppDatabaseTest {

    private AlbumDao userDao;
    private AppDatabase db;

    @Before
    public void createDB() {
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase.class).build();
        userDao = db.albumsDao();
    }

    @After
    public void closeDb() {
        db.close();
    }

    @Test
    public void testInsert() {
        userDao.insert(TestUtils.createListOfAlbumEntities());

        List<AlbumEntity> albums2 = userDao.all();
        assertThat(albums2, hasItems(TestUtils.createAlbumEntity2(), TestUtils.createAlbumEntity1()));

        AlbumEntity entity2 = userDao.one(TestUtils.ALBUM_ID_1);
        assertThat(entity2, is(TestUtils.createAlbumEntity1()));
    }
}
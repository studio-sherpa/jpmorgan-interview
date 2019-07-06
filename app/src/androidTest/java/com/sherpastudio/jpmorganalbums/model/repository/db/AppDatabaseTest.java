package com.sherpastudio.jpmorganalbums.model.repository.db;

import android.content.Context;

import com.sherpastudio.jpmorganalbums.model.repository.db.dao.AlbumDao;
import com.sherpastudio.jpmorganalbums.model.repository.db.entity.AlbumEntity;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
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
        AlbumEntity entity = new AlbumEntity(1, "Title 1");

        userDao.insert(Arrays.asList(entity));

        List<AlbumEntity> albums2 = userDao.all();
        assertThat(albums2, hasItems(entity));

        AlbumEntity entity2 = userDao.one(entity.getId());
        assertThat(entity2, is(entity));
    }
}
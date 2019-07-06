package com.sherpastudio.jpmorganalbums;

import android.content.Context;

import com.sherpastudio.jpmorganalbums.model.data.Album;
import com.sherpastudio.jpmorganalbums.model.repository.albums.local.db.entity.AlbumEntity;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

import androidx.test.platform.app.InstrumentationRegistry;

public class TestUtils {

    public static long ALBUM_ID_1 = 1;
    public static long ALBUM_ID_2 = 2;

    public static String ALBUM_TITLE_1 = "aaa";
    public static String ALBUM_TITLE_2 = "bbb";

    public static Album createAlbum1() {
        return new Album(ALBUM_ID_1, ALBUM_TITLE_1);
    }

    public static Album createAlbum2() {
        return new Album(ALBUM_ID_2, ALBUM_TITLE_2);
    }

    public static AlbumEntity createOneAlbumEntity() {
        return new AlbumEntity(ALBUM_ID_1, ALBUM_TITLE_1);
    }


    public static AlbumEntity createAlbumEntity1() {
        return new AlbumEntity(ALBUM_ID_1, ALBUM_TITLE_1);
    }

    public static AlbumEntity createAlbumEntity2() {
        return new AlbumEntity(ALBUM_ID_2, ALBUM_TITLE_2);
    }

    public static List<Album> createListOfAlbums() {
        return Arrays.asList(
                createAlbum2(),
                createAlbum1());
    }

    public static List<AlbumEntity> createListOfAlbumEntities() {
        return Arrays.asList(
                createAlbumEntity2(),
                createAlbumEntity1());
    }

    public static String getStringFromFile(Context context, String filePath) throws Exception {
        final InputStream stream = context.getAssets().open(filePath);

        String ret = convertStreamToString(stream);
        //Make sure you close all streams.
        stream.close();
        return ret;
    }

    public static String convertStreamToString(InputStream is) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line).append("\n");
        }
        reader.close();
        return sb.toString();
    }
}

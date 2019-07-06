package com.sherpastudio.jpmorganalbums.model.domain;

import com.sherpastudio.jpmorganalbums.TestUtils;
import com.sherpastudio.jpmorganalbums.model.repository.albums.IDataRepository;
import com.sherpastudio.jpmorganalbums.model.data.Album;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class GetSingleAlbumUseCaseTest {

    @Test
    public void getNullAlbum() {

        IDataRepository mockLocalDataRepository = new IDataRepository() {
            @Override
            public List<Album> getAlbums() {
                return null;
            }

            @Override
            public void addAlbums(List<Album> albums) {}

            @Override
            public Album getSingleAlbum(long albumId) {
                return null;
            }
        };

        GetSingleAlbumUseCase useCase = new GetSingleAlbumUseCase(mockLocalDataRepository);
        Album album = useCase.getAlbum(1);

        assertNull(album);
    }

    @Test
    public void getSingleAlbum() {

        IDataRepository repoFound = new IDataRepository() {
            @Override
            public List<Album> getAlbums() {
                return null;
            }

            @Override
            public void addAlbums(List<Album> albums) {}

            @Override
            public Album getSingleAlbum(long albumId) {
                return TestUtils.createAlbum1();
            }
        };

        IDataRepository repoNotFound = new IDataRepository() {
            @Override
            public List<Album> getAlbums() {
                return null;
            }

            @Override
            public void addAlbums(List<Album> albums) {}

            @Override
            public Album getSingleAlbum(long albumId) {
                return null;
            }
        };

        GetSingleAlbumUseCase useCase = new GetSingleAlbumUseCase(repoFound);
        Album album = useCase.getAlbum(TestUtils.ALBUM_ID_1);

        assertNotNull(album);
        assertThat(album, is(TestUtils.createAlbum1()));

        useCase = new GetSingleAlbumUseCase(repoNotFound);
        album = useCase.getAlbum(1);
        assertNull(album);
    }
}
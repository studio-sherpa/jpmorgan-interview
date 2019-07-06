package com.sherpastudio.jpmorganalbums.model.domain;

import com.sherpastudio.jpmorganalbums.TestUtils;
import com.sherpastudio.jpmorganalbums.model.repository.albums.IDataRepository;
import com.sherpastudio.jpmorganalbums.model.data.Album;
import com.sherpastudio.jpmorganalbums.model.repository.INetworkProvider;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class GetAlbumsUseCaseTest {

    @Test
    public void getNullAlbums() {

        IDataRepository mockRemoteDataRepository = new IDataRepository() {
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

        INetworkProvider mockOnlineNetworkProvider = () -> true;
        INetworkProvider mockOfflineNetworkProvider = () -> false;

        GetAlbumsUseCase useCase = new GetAlbumsUseCase(mockRemoteDataRepository, mockLocalDataRepository, mockOnlineNetworkProvider);
        List<Album> onlineAlbums = useCase.getAlbums();

        assertNull(onlineAlbums);

        useCase = new GetAlbumsUseCase(mockRemoteDataRepository, mockLocalDataRepository, mockOfflineNetworkProvider);
        List<Album> offlineAlbums = useCase.getAlbums();

        assertNull(offlineAlbums);
    }


    @Test
    public void getAlbums() {

        IDataRepository mockRemoteDataRepository = new IDataRepository() {
            @Override
            public List<Album> getAlbums() {
                return Arrays.asList(TestUtils.createAlbum1());
            }

            @Override
            public void addAlbums(List<Album> albums) {}

            @Override
            public Album getSingleAlbum(long albumId) {
                return null;
            }
        };

        IDataRepository mockLocalDataRepository = new IDataRepository() {
            @Override
            public List<Album> getAlbums() {
                return Arrays.asList(TestUtils.createAlbum2());
            }

            @Override
            public void addAlbums(List<Album> albums) {}

            @Override
            public Album getSingleAlbum(long albumId) {
                return null;
            }
        };

        INetworkProvider mockOnlineNetworkProvider = () -> true;
        INetworkProvider mockOfflineNetworkProvider = () -> false;

        GetAlbumsUseCase useCase = new GetAlbumsUseCase(mockRemoteDataRepository, mockLocalDataRepository, mockOnlineNetworkProvider);
        List<Album> onlineAlbums = useCase.getAlbums();

        assertEquals(Arrays.asList(TestUtils.createAlbum1()), onlineAlbums);

        useCase = new GetAlbumsUseCase(mockRemoteDataRepository, mockLocalDataRepository, mockOfflineNetworkProvider);
        List<Album> offlineAlbums = useCase.getAlbums();

        assertEquals(Arrays.asList(TestUtils.createAlbum2()), offlineAlbums);
    }
}
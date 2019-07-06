package com.sherpastudio.jpmorganalbums.model.domain;

import com.sherpastudio.jpmorganalbums.model.IDataRepository;
import com.sherpastudio.jpmorganalbums.model.data.Album;
import com.sherpastudio.jpmorganalbums.model.repository.INetworkRepository;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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
        };

        IDataRepository mockLocalDataRepository = new IDataRepository() {
            @Override
            public List<Album> getAlbums() {
                return null;
            }

            @Override
            public void addAlbums(List<Album> albums) {}
        };

        INetworkRepository mockOnlineNetworkRepository = () -> true;
        INetworkRepository mockOfflineNetworkRepository = () -> false;

        GetAlbumsUseCase useCase = new GetAlbumsUseCase(mockRemoteDataRepository, mockLocalDataRepository, mockOnlineNetworkRepository);
        List<Album> onlineAlbums = useCase.getAlbums();

        assertNull(onlineAlbums);

        useCase = new GetAlbumsUseCase(mockRemoteDataRepository, mockLocalDataRepository, mockOfflineNetworkRepository);
        List<Album> offlineAlbums = useCase.getAlbums();

        assertNull(offlineAlbums);
    }


    @Test
    public void getAlbums() {
        List<Album> mockRemoteData = Arrays.asList(new Album(1, "title"));
        List<Album> mockLocalData = Arrays.asList(new Album(2, "title 2"));


        IDataRepository mockRemoteDataRepository = new IDataRepository() {
            @Override
            public List<Album> getAlbums() {
                return new ArrayList<>(mockRemoteData);
            }

            @Override
            public void addAlbums(List<Album> albums) {}
        };

        IDataRepository mockLocalDataRepository = new IDataRepository() {
            @Override
            public List<Album> getAlbums() {
                return new ArrayList<>(mockLocalData);
            }

            @Override
            public void addAlbums(List<Album> albums) {}
        };

        INetworkRepository mockOnlineNetworkRepository = () -> true;
        INetworkRepository mockOfflineNetworkRepository = () -> false;

        GetAlbumsUseCase useCase = new GetAlbumsUseCase(mockRemoteDataRepository, mockLocalDataRepository, mockOnlineNetworkRepository);
        List<Album> onlineAlbums = useCase.getAlbums();

        assertNotNull(onlineAlbums);
        assertArrayEquals(mockRemoteData.toArray(), onlineAlbums.toArray());

        useCase = new GetAlbumsUseCase(mockRemoteDataRepository, mockLocalDataRepository, mockOfflineNetworkRepository);
        List<Album> offlineAlbums = useCase.getAlbums();

        assertNotNull(offlineAlbums);
        assertArrayEquals(mockLocalData.toArray(), offlineAlbums.toArray());
    }
}
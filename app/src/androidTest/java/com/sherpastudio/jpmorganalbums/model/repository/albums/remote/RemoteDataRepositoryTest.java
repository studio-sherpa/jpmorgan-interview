package com.sherpastudio.jpmorganalbums.model.repository.albums.remote;

import android.content.Context;

import com.sherpastudio.jpmorganalbums.Injection;
import com.sherpastudio.jpmorganalbums.TestUtils;
import com.sherpastudio.jpmorganalbums.model.data.Album;
import com.sherpastudio.jpmorganalbums.model.repository.albums.IDataRepository;
import com.squareup.okhttp.mockwebserver.MockResponse;
import com.squareup.okhttp.mockwebserver.MockWebServer;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.platform.app.InstrumentationRegistry;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.mock.BehaviorDelegate;
import retrofit2.mock.MockRetrofit;
import retrofit2.mock.NetworkBehavior;

import static org.junit.Assert.*;

public class RemoteDataRepositoryTest {

    private MockRetrofit mMockRetrofit;

    @Before
    public void prepareRetrofitClient(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://test")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        NetworkBehavior behavior = NetworkBehavior.create();

        mMockRetrofit = new MockRetrofit.Builder(retrofit)
                .networkBehavior(behavior)
                .build();
    }

    @Test
    public void getRandomAlbums() {
        BehaviorDelegate<AlbumsAPIService> delegate = mMockRetrofit.create(AlbumsAPIService.class);
        AlbumsAPIService mockService = new MockSimpleAlbumsAPIService(delegate);

        RemoteDataRepository remoteDataRepository = RemoteDataRepository.getInstance(mockService);
        List<Album> albums = remoteDataRepository.getAlbums();
        assertEquals(albums, TestUtils.createListOfAlbums());
    }

    @Test
    public void getAlbumsWithError() {
        BehaviorDelegate<AlbumsAPIService> delegate = mMockRetrofit.create(AlbumsAPIService.class);
        AlbumsAPIService mockService = new MockErrorAlbumsAPIService(delegate);

        RemoteDataRepository remoteDataRepository = RemoteDataRepository.getInstance(mockService);
        List<Album> albums = remoteDataRepository.getAlbums();
        assertNull(albums);
    }

    @Test
    public void testJSONResponse() throws Exception {

        String fileName = "albums_ok_response.json";

        MockWebServer server = new MockWebServer();
        server.start();
        Injection.DEBUG_ALBUMS_BASE_URL = server.url("/").toString();

        Context context = InstrumentationRegistry.getInstrumentation().getContext();
        String jsonBody = TestUtils.getStringFromFile(context, fileName);
        server.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(jsonBody));

        IDataRepository remoteDataRepository = Injection.provideRemoteDataRepository();
        List<Album> albums = remoteDataRepository.getAlbums();
        assertEquals(2, albums.size());
    }

    @Test
    public void testWrongJSONResponse() throws Exception {

        String fileName = "albums_wrong_format_response.json";

        MockWebServer server = new MockWebServer();
        server.start();
        Injection.DEBUG_ALBUMS_BASE_URL = server.url("/").toString();

        Context context = InstrumentationRegistry.getInstrumentation().getContext();
        String jsonBody = TestUtils.getStringFromFile(context, fileName);
        server.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(jsonBody));

        IDataRepository remoteDataRepository = Injection.provideRemoteDataRepository();
        List<Album> albums = remoteDataRepository.getAlbums();
        assertNull(albums);
    }
}
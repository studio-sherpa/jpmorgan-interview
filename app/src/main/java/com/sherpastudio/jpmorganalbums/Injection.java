package com.sherpastudio.jpmorganalbums;

import android.content.Context;

import com.sherpastudio.jpmorganalbums.helper.UseCaseHandler;
import com.sherpastudio.jpmorganalbums.model.domain.GetSingleAlbumUseCase;
import com.sherpastudio.jpmorganalbums.model.repository.albums.remote.AlbumsAPIService;
import com.sherpastudio.jpmorganalbums.view.ViewModelFactory;
import com.sherpastudio.jpmorganalbums.model.repository.albums.IDataRepository;
import com.sherpastudio.jpmorganalbums.model.repository.INetworkProvider;
import com.sherpastudio.jpmorganalbums.model.repository.NetworkProvider;
import com.sherpastudio.jpmorganalbums.model.repository.albums.local.LocalRepository;
import com.sherpastudio.jpmorganalbums.model.repository.albums.remote.RemoteDataRepository;
import com.sherpastudio.jpmorganalbums.model.domain.GetAlbumsUseCase;
import com.sherpastudio.jpmorganalbums.model.repository.albums.local.db.AppDatabase;
import com.sherpastudio.jpmorganalbums.model.repository.albums.remote.MyRetrofitClient;

public class Injection {

    public static String DEBUG_ALBUMS_BASE_URL = "https://jsonplaceholder.typicode.com/";

    public static ViewModelFactory provideViewModelFactory() {
        return ViewModelFactory.getInstance();
    }

    public static UseCaseHandler provideUseCaseHandler() {
        return UseCaseHandler.getInstance();
    }

    public static IDataRepository provideRemoteDataRepository() {
        AlbumsAPIService service = MyRetrofitClient.getInstance(DEBUG_ALBUMS_BASE_URL).getService();
        return RemoteDataRepository.getInstance(service);
    }

    public static IDataRepository provideLocalRepository(){
        return LocalRepository.getInstance(AppDatabase.getInstance(provideApplicationContext()));
    }

    public static INetworkProvider provideNetworkProvider(){
        return NetworkProvider.getInstance();
    }

    public static GetAlbumsUseCase provideGetAlbumsUseCase() {
        return new GetAlbumsUseCase(
                provideRemoteDataRepository(),
                provideLocalRepository(),
                provideNetworkProvider());
    }

    public static GetSingleAlbumUseCase provideGetSingleAlbumUseCase() {
        return new GetSingleAlbumUseCase(provideLocalRepository());
    }

    public static Context provideApplicationContext(){
        return MyApplication.sApplicationContext;
    }
}

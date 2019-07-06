package com.sherpastudio.jpmorganalbums;

import android.content.Context;

import com.sherpastudio.jpmorganalbums.helper.UseCaseHandler;
import com.sherpastudio.jpmorganalbums.model.domain.GetSingleAlbumUseCase;
import com.sherpastudio.jpmorganalbums.view.ViewModelFactory;
import com.sherpastudio.jpmorganalbums.model.repository.albums.IDataRepository;
import com.sherpastudio.jpmorganalbums.model.repository.INetworkRepository;
import com.sherpastudio.jpmorganalbums.model.repository.NetworkRepository;
import com.sherpastudio.jpmorganalbums.model.repository.albums.local.LocalRepository;
import com.sherpastudio.jpmorganalbums.model.repository.albums.remote.RemoteDataRepository;
import com.sherpastudio.jpmorganalbums.model.domain.GetAlbumsUseCase;
import com.sherpastudio.jpmorganalbums.model.repository.albums.local.db.AppDatabase;
import com.sherpastudio.jpmorganalbums.model.repository.albums.remote.RetrofitAlbumsClient;

public class Injection {

    private static final String DEBUG_ALBUMS_BASE_URL = "https://jsonplaceholder.typicode.com/";

    public static ViewModelFactory provideViewModelFactory() {
        return ViewModelFactory.getInstance();
    }

    public static UseCaseHandler provideUseCaseHandler() {
        return UseCaseHandler.getInstance();
    }

    public static IDataRepository provideRemoteDataRepository() {
        return RemoteDataRepository.getInstance(RetrofitAlbumsClient.getInstance(DEBUG_ALBUMS_BASE_URL));
    }

    public static IDataRepository provideLocalRepository(){
        return LocalRepository.getInstance(AppDatabase.getInstance(provideApplicationContext()));
    }

    public static INetworkRepository provideNetworkRepository(){
        return NetworkRepository.getInstance();
    }

    public static GetAlbumsUseCase provideGetAlbumsUseCase() {
        return new GetAlbumsUseCase(
                provideRemoteDataRepository(),
                provideLocalRepository(),
                provideNetworkRepository());
    }

    public static GetSingleAlbumUseCase provideGetSingleAlbumUseCase() {
        return new GetSingleAlbumUseCase(provideLocalRepository());
    }

    public static Context provideApplicationContext(){
        return MyApplication.sApplicationContext;
    }
}

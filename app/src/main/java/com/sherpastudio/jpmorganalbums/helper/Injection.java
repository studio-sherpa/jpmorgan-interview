package com.sherpastudio.jpmorganalbums.helper;

import android.content.Context;

import com.sherpastudio.jpmorganalbums.MyApplication;
import com.sherpastudio.jpmorganalbums.model.IDataRepository;
import com.sherpastudio.jpmorganalbums.model.repository.db.LocalRepository;
import com.sherpastudio.jpmorganalbums.model.repository.remote.RemoteDataRepository;
import com.sherpastudio.jpmorganalbums.model.domain.GetAlbumsUseCase;
import com.sherpastudio.jpmorganalbums.model.repository.db.AppDatabase;
import com.sherpastudio.jpmorganalbums.model.repository.remote.RetrofitAlbumsClient;

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

    public static GetAlbumsUseCase provideGetAlbumsUseCase() {
        return new GetAlbumsUseCase(provideRemoteDataRepository(), provideLocalRepository());
    }

    public static Context provideApplicationContext(){
        return MyApplication.sApplicationContext;
    }
}

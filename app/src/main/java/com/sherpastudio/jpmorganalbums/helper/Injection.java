package com.sherpastudio.jpmorganalbums.helper;

import com.sherpastudio.jpmorganalbums.model.IDataRepository;
import com.sherpastudio.jpmorganalbums.model.repository.DataRepository;
import com.sherpastudio.jpmorganalbums.model.domain.GetAlbumsUseCase;
import com.sherpastudio.jpmorganalbums.model.repository.io.RetrofitAlbumsClient;

public class Injection {

    private static final String DEBUG_ALBUMS_BASE_URL = "https://jsonplaceholder.typicode.com/";

    public static ViewModelFactory provideViewModelFactory() {
        return ViewModelFactory.getInstance();
    }

    public static UseCaseHandler provideUseCaseHandler() {
        return UseCaseHandler.getInstance();
    }

    public static IDataRepository provideDataRepository() {
        return DataRepository.getInstance(RetrofitAlbumsClient.getInstance(DEBUG_ALBUMS_BASE_URL));
    }

    public static GetAlbumsUseCase provideGetAlbumsUseCase() {
        return new GetAlbumsUseCase(provideDataRepository());
    }
}

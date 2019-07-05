package com.sherpastudio.jpmorganalbums.helper;

import com.sherpastudio.jpmorganalbums.model.IDataRepository;
import com.sherpastudio.jpmorganalbums.model.repository.DataRepository;
import com.sherpastudio.jpmorganalbums.model.domain.GetAlbumsUseCase;

public class Injection {

    public static ViewModelFactory provideViewModelFactory() {
        return ViewModelFactory.getInstance();
    }

    public static UseCaseHandler provideUseCaseHandler(){
        return UseCaseHandler.getInstance();
    }

    public static IDataRepository provideDataRepository() {
        return DataRepository.getInstance();
    }

    public static GetAlbumsUseCase provideGetAlbumsUseCase() {
        return new GetAlbumsUseCase(provideDataRepository());
    }
}

package com.sherpastudio.jpmorganalbums.view.detail;

import com.sherpastudio.jpmorganalbums.helper.UseCaseCallback;
import com.sherpastudio.jpmorganalbums.helper.UseCaseHandler;
import com.sherpastudio.jpmorganalbums.model.data.Album;
import com.sherpastudio.jpmorganalbums.model.domain.GetSingleAlbumUseCase;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AlbumDetailViewModel extends ViewModel {
   public MutableLiveData<Album> mAlbum;
    private final UseCaseHandler mUseCaseHandler;
    private final GetSingleAlbumUseCase mGetSingleAlbumUseCase;
    private UseCaseCallback<GetSingleAlbumUseCase.ResponseValue> mUseCaseCallback = new UseCaseCallback<GetSingleAlbumUseCase.ResponseValue>() {
        @Override
        public void onSuccess(GetSingleAlbumUseCase.ResponseValue response) {
            mAlbum.postValue(response.album);
        }

        @Override
        public void onError(Throwable t) {
            //TODO
        }
    };


    public AlbumDetailViewModel(@NonNull UseCaseHandler useCaseHandler,
                                @NonNull GetSingleAlbumUseCase getSingleAlbumUseCase){
        super();
        mUseCaseHandler = useCaseHandler;
        mGetSingleAlbumUseCase = getSingleAlbumUseCase;
        mAlbum = new MutableLiveData<>();
    }

    public void setAlbumId(long albumId) {
        mUseCaseHandler.execute(mGetSingleAlbumUseCase, new GetSingleAlbumUseCase.RequestValues(albumId), mUseCaseCallback);
    }
}

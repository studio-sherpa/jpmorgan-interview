package com.sherpastudio.jpmorganalbums.view.list;

import com.sherpastudio.jpmorganalbums.helper.UseCaseCallback;
import com.sherpastudio.jpmorganalbums.helper.UseCaseHandler;
import com.sherpastudio.jpmorganalbums.model.data.Album;
import com.sherpastudio.jpmorganalbums.model.domain.GetAlbumsUseCase;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AlbumListViewModel extends ViewModel {

    private final MutableLiveData<List<Album>> mObservableAlbumsList;
    private final MutableLiveData<Boolean> mObservableIsLoading;

    public final MutableLiveData<Boolean> emptyList;
    private final UseCaseHandler mUseCaseHandler;
    private final GetAlbumsUseCase mGetAlbumsUseCase;

    private UseCaseCallback<GetAlbumsUseCase.ResponseValue> mUseCaseCallback = new UseCaseCallback<GetAlbumsUseCase.ResponseValue>() {
        @Override
        public void onSuccess(GetAlbumsUseCase.ResponseValue response) {
            mObservableIsLoading.postValue(false);
            mObservableAlbumsList.postValue(response.list);
            emptyList.postValue(response.list.isEmpty());
        }

        @Override
        public void onError(Throwable t) {
            mObservableIsLoading.postValue(false);
          //TODO
        }
    };

    public AlbumListViewModel(UseCaseHandler useCaseHandler, GetAlbumsUseCase getAlbumsUseCase) {
        this.mUseCaseHandler = useCaseHandler;
        this.mGetAlbumsUseCase = getAlbumsUseCase;

        this.mObservableIsLoading = new MutableLiveData<>();
        this.emptyList = new MutableLiveData<>();
        this.mObservableAlbumsList = new MutableLiveData<>();

        refreshList();
    }

    LiveData<List<Album>> getListItems() {
        return mObservableAlbumsList;
    }


    LiveData<Boolean> getIsLoading() {
        return mObservableIsLoading;
    }

    void refreshList(){
        mObservableIsLoading.postValue(true);
        GetAlbumsUseCase.RequestValues requestValue = new GetAlbumsUseCase.RequestValues();
        mUseCaseHandler.execute(mGetAlbumsUseCase, requestValue, mUseCaseCallback);
    }
}

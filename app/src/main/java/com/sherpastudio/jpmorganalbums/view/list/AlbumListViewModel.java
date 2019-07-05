package com.sherpastudio.jpmorganalbums.view.list;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AlbumListViewModel extends ViewModel {

    private final MutableLiveData<List<AlbumItemData>> mObservableAlbumsList;

    public final LiveData<Boolean> emptyList;

    public AlbumListViewModel() {
        this.emptyList = new MutableLiveData<>();
        mObservableAlbumsList = new MutableLiveData<>();
    }

    LiveData<List<AlbumItemData>> getListItems() {
        return mObservableAlbumsList;
    }
}

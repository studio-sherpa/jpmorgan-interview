package com.sherpastudio.jpmorganalbums.view.helper;

import com.sherpastudio.jpmorganalbums.view.list.AlbumListViewModel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class ViewModelFactory implements ViewModelProvider.Factory {
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass == AlbumListViewModel.class) {
            return (T) new AlbumListViewModel();
        }
        throw new IllegalArgumentException("unknown model class " + modelClass);
    }

    private ViewModelFactory(){

    }

    private static ViewModelFactory INSTANCE;
    public static ViewModelFactory getInstance(){
        if(INSTANCE == null){
            INSTANCE = new ViewModelFactory();
        }
        return INSTANCE;
    }
}

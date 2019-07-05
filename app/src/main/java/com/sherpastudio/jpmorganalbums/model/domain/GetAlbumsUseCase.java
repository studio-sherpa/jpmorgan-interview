package com.sherpastudio.jpmorganalbums.model.domain;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.sherpastudio.jpmorganalbums.helper.Injection;
import com.sherpastudio.jpmorganalbums.model.IDataRepository;
import com.sherpastudio.jpmorganalbums.model.data.Album;
import com.sherpastudio.jpmorganalbums.helper.UseCase;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import androidx.annotation.NonNull;

/**
 * Get the list of all the albums in order to show them to the user.
 * It will also order it by title.
 */
public class GetAlbumsUseCase extends UseCase<GetAlbumsUseCase.RequestValues, GetAlbumsUseCase.ResponseValue> {

    private final IDataRepository mDataRepository;
    private final IDataRepository mDBRepository;

    private Comparator<? super Album> mTitleComparator = (Comparator<Album>) (o1, o2) -> o1.getTitle().compareTo(o2.getTitle());

    public GetAlbumsUseCase(@NonNull IDataRepository remoteDataRepository,
                            @NonNull IDataRepository DBRepository) {
        this.mDataRepository = remoteDataRepository;
        this.mDBRepository = DBRepository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        if(isNetworkAvailable()){
            List<Album> albums = mDataRepository.getAlbums();
            if(albums != null){
                Collections.sort(albums, mTitleComparator);
                ResponseValue responseValue = new ResponseValue(albums);
                useCaseCallback.onSuccess(responseValue);

                //Add the albums to the DB for the offline mode
                mDBRepository.addAlbums(albums);
            }
            else {
                useCaseCallback.onError(new Throwable());
            }
        }
        else{
            List<Album> albums = mDBRepository.getAlbums();
            ResponseValue responseValue = new ResponseValue(albums);
            useCaseCallback.onSuccess(responseValue);
        }
    }


    public static class RequestValues implements UseCase.RequestValues{

        public RequestValues() {}
    }

    public static class ResponseValue implements UseCase.ResponseValue{
        public final List<Album> list;

        ResponseValue(List<Album> list) {
            this.list = list;
        }
    }


    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) Injection.provideApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

}
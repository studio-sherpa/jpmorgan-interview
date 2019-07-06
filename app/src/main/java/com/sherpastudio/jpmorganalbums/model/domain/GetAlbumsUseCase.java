package com.sherpastudio.jpmorganalbums.model.domain;


import com.sherpastudio.jpmorganalbums.helper.UseCase;
import com.sherpastudio.jpmorganalbums.model.repository.albums.IDataRepository;
import com.sherpastudio.jpmorganalbums.model.data.Album;
import com.sherpastudio.jpmorganalbums.model.repository.INetworkProvider;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;

/**
 * Get the list of all the albums in order to show them to the user.
 * It will also order it by title.
 */
public class GetAlbumsUseCase extends UseCase<GetAlbumsUseCase.RequestValues, GetAlbumsUseCase.ResponseValue> {

    private final IDataRepository mRemoteDataRepository;
    private final IDataRepository mLocalDataRepository;
    private final INetworkProvider mNetworkProvider;

    private Comparator<? super Album> mTitleComparator = (Comparator<Album>) (o1, o2) -> o1.getTitle().compareTo(o2.getTitle());

    public GetAlbumsUseCase(@NonNull IDataRepository remoteDataRepository,
                            @NonNull IDataRepository DBRepository,
                            @NonNull INetworkProvider networkProvider) {
        this.mRemoteDataRepository = remoteDataRepository;
        this.mLocalDataRepository = DBRepository;
        this.mNetworkProvider = networkProvider;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        List<Album> albums = getAlbums();
        if(albums != null){
            ResponseValue responseValue = new ResponseValue(albums);
            useCaseCallback.onSuccess(responseValue);
        }
        else {
            useCaseCallback.onError(new Throwable());
        }
    }

    @VisibleForTesting
    @Nullable
    public List<Album> getAlbums(){
        List<Album> albums;
        if(mNetworkProvider.hasInternetConnection()){
            albums = mRemoteDataRepository.getAlbums();
            if(albums != null){
                Collections.sort(albums, mTitleComparator);
                //Add the albums to the DB for the offline mode
                mLocalDataRepository.addAlbums(albums);
            }
        }
        else{
            albums = mLocalDataRepository.getAlbums();
        }
        return albums;
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




}
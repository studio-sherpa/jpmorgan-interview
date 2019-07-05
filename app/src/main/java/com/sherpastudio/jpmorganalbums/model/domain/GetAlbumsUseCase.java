package com.sherpastudio.jpmorganalbums.model.domain;


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
    private Comparator<? super Album> mTitleComparator = (Comparator<Album>) (o1, o2) -> o1.getTitle().compareTo(o2.getTitle());

    public GetAlbumsUseCase(@NonNull IDataRepository dataRepository) {
        this.mDataRepository = dataRepository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        List<Album> albums = mDataRepository.getAlbums(requestValues.mForceReload);
        if(albums != null){
            Collections.sort(albums, mTitleComparator);
            ResponseValue responseValue = new ResponseValue(albums);
            useCaseCallback.onSuccess(responseValue);
        }
        else {
            useCaseCallback.onError(new Throwable("Error fetching the data"));
        }
    }


    public static class RequestValues implements UseCase.RequestValues{

        public final boolean mForceReload;

        public RequestValues(boolean forceReload) {
            mForceReload = forceReload;
        }
    }

    public static class ResponseValue implements UseCase.ResponseValue{
        public final List<Album> list;

        ResponseValue(List<Album> list) {
            this.list = list;
        }
    }

}
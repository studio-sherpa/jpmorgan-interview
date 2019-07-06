package com.sherpastudio.jpmorganalbums.model.domain;

import com.sherpastudio.jpmorganalbums.helper.UseCase;
import com.sherpastudio.jpmorganalbums.model.IDataRepository;
import com.sherpastudio.jpmorganalbums.model.data.Album;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;

public class GetSingleAlbumUseCase extends UseCase<GetSingleAlbumUseCase.RequestValues, GetSingleAlbumUseCase.ResponseValue> {
    private final IDataRepository mLocalDataRepository;

    public GetSingleAlbumUseCase(@NonNull IDataRepository localDataRepository) {
        mLocalDataRepository = localDataRepository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        Album album = getAlbum(requestValues.albumId);
        if(album != null){
            ResponseValue responseValue = new ResponseValue(album);
            useCaseCallback.onSuccess(responseValue);
        }
        else {
            useCaseCallback.onError(new Throwable());
        }
    }

    @VisibleForTesting
    @Nullable
    public Album getAlbum(long albumId) {
        return mLocalDataRepository.getSingleAlbum(albumId);
    }

    public static class RequestValues implements UseCase.RequestValues{

        long albumId;

        public RequestValues(long albumId) {
            this.albumId = albumId;
        }
    }

    public static class ResponseValue implements UseCase.ResponseValue{
        public final Album album;

        ResponseValue(Album album) {
            this.album = album;
        }
    }

}

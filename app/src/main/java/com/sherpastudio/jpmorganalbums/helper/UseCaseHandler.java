package com.sherpastudio.jpmorganalbums.helper;

import androidx.annotation.NonNull;

public class UseCaseHandler {
    private final UseCaseScheduler mUseCaseScheduler;

    private static UseCaseHandler INSTANCE;
    public static UseCaseHandler getInstance(){
        if(INSTANCE == null){
            INSTANCE = new UseCaseHandler(new UseCaseThreadPoolScheduler());
        }
        return INSTANCE;
    }

    private UseCaseHandler(@NonNull UseCaseScheduler mUseCaseScheduler) {
        this.mUseCaseScheduler = mUseCaseScheduler;
    }

    public <T extends UseCase.RequestValues, R extends UseCase.ResponseValue> void execute(
            UseCase<T, R> useCase, T values, UseCaseCallback<R> callback
    ){
      useCase.requestValues = values;
      useCase.useCaseCallback = new UiCallbackWrapper<>(callback, this);

      mUseCaseScheduler.execute(() -> useCase.run());
    }

    private <V extends UseCase.ResponseValue> void notifyResponse(
            V response,
            UseCaseCallback<V> useCaseCallback){
        mUseCaseScheduler.notifyResponse(response, useCaseCallback);
    }

    private <V extends UseCase.ResponseValue> void notifyError(
            UseCaseCallback<V> useCaseCallback, Throwable t) {
        mUseCaseScheduler.onError(useCaseCallback, t);
    }

    private static class UiCallbackWrapper<V extends UseCase.ResponseValue> implements UseCaseCallback<V> {
        private final UseCaseCallback<V> mCallback;
        private final UseCaseHandler mUseCaseHandler;

        private UiCallbackWrapper(UseCaseCallback<V> mCallback, UseCaseHandler mUseCaseHandler) {
            this.mCallback = mCallback;
            this.mUseCaseHandler = mUseCaseHandler;
        }

        @Override
        public void onSuccess(V response) {
            mUseCaseHandler.notifyResponse(response, mCallback);
        }

        @Override
        public void onError(Throwable t) {
            mUseCaseHandler.notifyError(mCallback, t);
        }
    }

}
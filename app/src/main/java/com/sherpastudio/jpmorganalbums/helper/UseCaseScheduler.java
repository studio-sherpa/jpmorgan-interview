package com.sherpastudio.jpmorganalbums.helper;

public interface UseCaseScheduler {

    void execute(Runnable runnable);

    <V extends UseCase.ResponseValue> void notifyResponse(V response, UseCaseCallback<V> useCaseCallback);

    <V extends UseCase.ResponseValue> void onError(UseCaseCallback<V> useCaseCallback, Throwable t);
}
package com.sherpastudio.jpmorganalbums.helper;

import android.os.Handler;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class UseCaseThreadPoolScheduler implements UseCaseScheduler {
    private final int POOL_SIZE = 2;

    private final int  MAX_POOL_SIZE = 4;

    private final long  TIMEOUT = 30;

    private final Handler mHandler = new Handler();

    private ThreadPoolExecutor mThreadPoolExecutor;

    UseCaseThreadPoolScheduler(){
        mThreadPoolExecutor = new ThreadPoolExecutor(POOL_SIZE, MAX_POOL_SIZE, TIMEOUT,
                TimeUnit.SECONDS, new ArrayBlockingQueue<>(POOL_SIZE));
    }

    @Override
    public void execute(Runnable runnable) {
        mThreadPoolExecutor.execute(runnable);
    }

    @Override
    public <V extends UseCase.ResponseValue> void notifyResponse(V response, UseCaseCallback<V> useCaseCallback) {
        mHandler.post(() -> useCaseCallback.onSuccess(response));
    }

    @Override
    public <V extends UseCase.ResponseValue> void onError(UseCaseCallback<V> useCaseCallback, Throwable t) {
        mHandler.post(() -> useCaseCallback.onError(t));
    }
}
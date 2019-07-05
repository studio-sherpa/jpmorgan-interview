package com.sherpastudio.jpmorganalbums.helper;

public interface UseCaseCallback<R> {
    void onSuccess(R response);
    void onError(Throwable t);
}

package com.sherpastudio.jpmorganalbums.helper;

public abstract class UseCase<Q extends UseCase.RequestValues, P extends UseCase.ResponseValue> {


    Q requestValues = null;
    protected UseCaseCallback<P> useCaseCallback = null;

    void run(){
        executeUseCase(requestValues);
    }

    protected abstract void executeUseCase(Q requestValues);

    /**
     * Data passed to a request.
     */
    public interface RequestValues{}

    /**
     * Data received from a request.
     */
    public interface ResponseValue{}

}

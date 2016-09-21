/*
 * Copyright (C) 20015 MaiNaEr All rights reserved
 */
package com.harry.test.rxjava.retrofit;

import java.util.List;

import rx.Observable;

/**
 * 类/接口描述
 *
 * @author Harry
 * @date 2016/9/19.
 */
public class MovieController extends HttpClient<MovieController.LoadListener> {

    public MovieController(LoadListener l) {
        super(l);
    }

    public void load() {
        LoadTask task = new LoadTask();
        task.load();
    }

    private class LoadTask extends BaseTask<List<MovieResponse>> {

        @Override
        public Observable<BaseResponse<List<MovieResponse>>> getObservable() {
            return service.getMovie();
        }

        @Override
        public void onSuccess(List<MovieResponse> out) {
            listener.onSuccess(out);
        }

        @Override
        public void onErrors(Throwable error) {
            listener.onError(error);
        }

        @Override
        public void onComplete() {
            listener.onComplete();
        }
    }

    public interface LoadListener {
        void onSuccess(List<MovieResponse> out);

        void onError(Throwable error);

        void onComplete();
    }
}

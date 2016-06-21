package com.eddy.clean.base.mvp;

/**
 * Created by wanqi on 16-6-17.
 */
public interface MvpPresenter<V extends MvpView> {

    void attachView(V view);
    void detachView();
    V getView();
    boolean isViewAttached();
}

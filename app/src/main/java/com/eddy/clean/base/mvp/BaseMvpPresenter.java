package com.eddy.clean.base.mvp;

import android.content.Context;
import android.support.annotation.UiThread;

import com.eddy.clean.dagger.provide.PresenterProvide;
import com.trello.rxlifecycle.ActivityLifecycleProvider;
import com.trello.rxlifecycle.FragmentLifecycleProvider;

/**
 * Created by wanqi on 16-6-17.
 */
public class BaseMvpPresenter<V extends MvpView> implements MvpPresenter<V> {

    private V viewRef;
    protected ActivityLifecycleProvider mActivityLifecycleProvider;
    protected FragmentLifecycleProvider mFragmentLifecycleProvider;
    protected Context mContext;

    public BaseMvpPresenter(PresenterProvide presenterProvide){

        mActivityLifecycleProvider = presenterProvide.getActivityLifecycleProvider();
        mFragmentLifecycleProvider = presenterProvide.getFragmentLifecycleProvider();
        mContext = presenterProvide.getContext();
    }


    @UiThread
    @Override
    public void attachView(V view) {
        viewRef = view;
    }

    @UiThread
    @Override
    public void detachView() {
        if(viewRef !=null){
            viewRef = null;
        }
    }

    @UiThread
    @Override
    public V getView() {
        return viewRef;
    }

    @UiThread
    @Override
    public boolean isViewAttached() {
        return viewRef != null;
    }
}

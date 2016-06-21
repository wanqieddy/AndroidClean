package com.eddy.clean.home;

import com.eddy.clean.base.mvp.MvpView;

/**
 * Created by wanqi on 16-6-20.
 */
public interface HomeView extends MvpView {

    void getDataSuccess();
    void getDataFail();
}

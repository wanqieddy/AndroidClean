package com.eddy.clean.home;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.eddy.clean.R;
import com.eddy.clean.base.BaseActivity;
import com.eddy.clean.base.rx.RxAppCompatActivity;
import com.eddy.clean.dagger.component.ActivityComponent;

import javax.inject.Inject;

/**
 * Created by wanqi on 16-6-20.
 */
public class HomeActivity extends BaseActivity<HomeComponent, HomeView, HomePresenter> implements HomeView{


    @Inject
    protected HomePresenter mHomePresenter;



    @Override
    public void getDataSuccess() {

    }

    @Override
    public void getDataFail() {

    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        mHomePresenter.getHomeData();
    }

    @Override
    protected HomePresenter createPresenter() {

        return mHomePresenter;
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_main);
    }

    @NonNull
    @Override
    public HomeComponent initComponet(@NonNull ActivityComponent activityComponent) {
        HomeComponent homeComponent = activityComponent.provideHomeComponent();
        homeComponent.inject(this);
        return homeComponent;
    }
}

package com.eddy.clean.base;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.eddy.clean.App;
import com.eddy.clean.base.mvp.BaseMvpPresenter;
import com.eddy.clean.base.mvp.MvpView;
import com.eddy.clean.base.rx.RxAppCompatActivity;
import com.eddy.clean.dagger.base.BaseActivityDagger;
import com.eddy.clean.dagger.base.BaseComponent;
import com.eddy.clean.dagger.component.ActivityComponent;
import com.eddy.clean.dagger.module.ActivityModule;

import butterknife.ButterKnife;

/**
 * Created by wanqi on 16-6-21.
 */
public abstract class BaseActivity<C extends BaseComponent, V extends MvpView, P extends BaseMvpPresenter<V>> extends RxAppCompatActivity implements BaseActivityDagger<C> {

    protected C mComponet;
    protected P mPresent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        initInject();
        super.onCreate(savedInstanceState);
        initView();
        ButterKnife.bind(this);
        initData(savedInstanceState);
        mPresent = createPresenter();
        mPresent.attachView((V)this);
    }

    protected abstract void initData(Bundle savedInstanceState);

    protected abstract P createPresenter();

    protected abstract void initView();

    private void initInject(){


        ActivityComponent activityComponent = App.getInstance().getApplicationComponent().provideActivityComponent(new ActivityModule(this));
        mComponet = initComponet(activityComponent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresent.detachView();
        mComponet = null;
    }
}

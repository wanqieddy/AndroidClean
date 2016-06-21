package com.eddy.clean;

import com.eddy.clean.base.rx.RxApp;
import com.eddy.clean.dagger.component.ApplicationComponent;
import com.eddy.clean.dagger.component.DaggerApplicationComponent;
import com.eddy.clean.dagger.module.ApplicationModule;
import com.eddy.data.module.ApiModule;

/**
 * Created by wanqi on 16-6-21.
 */
public class App extends RxApp {

    private static App mApp;
    private ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        if (mApp == null){
            mApp = this;
        }
        initApplicationComponent();
    }

    private void initApplicationComponent(){

        mApplicationComponent = DaggerApplicationComponent.builder().applicationModule(new ApplicationModule(this)).apiModule(new ApiModule()).build();
    }


    public ApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }

    public static App getInstance() {
        return mApp;
    }
}

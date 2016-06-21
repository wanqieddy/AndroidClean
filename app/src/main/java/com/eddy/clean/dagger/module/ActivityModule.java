package com.eddy.clean.dagger.module;

import android.content.Context;
import android.support.v4.app.FragmentActivity;

import com.eddy.clean.dagger.provide.PresenterProvide;
import com.eddy.domain.dagger.qualifier.ContextLevel;
import com.eddy.domain.dagger.scope.ActivityScope;
import com.trello.rxlifecycle.ActivityLifecycleProvider;

import dagger.Module;
import dagger.Provides;

/**
 * Created by wanqi on 16-6-17.
 */
@Module
public class ActivityModule {

    private ActivityLifecycleProvider mProvider;

    public ActivityModule(ActivityLifecycleProvider provider) {
        this.mProvider = provider;
    }


    @Provides
    @ActivityScope
    @ContextLevel(ContextLevel.ACTIVITY)
    Context provideContext(){

        return (FragmentActivity) mProvider;
    }

    @Provides
    @ActivityScope
    ActivityLifecycleProvider getActivityLifecycleProvider(){
        return mProvider;
    }


    @Provides
    @ActivityScope
    PresenterProvide getPresenterProvide(){

        return new PresenterProvide(mProvider, null, (FragmentActivity)mProvider);
    }


}

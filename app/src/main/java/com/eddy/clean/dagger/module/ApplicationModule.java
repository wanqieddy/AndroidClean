package com.eddy.clean.dagger.module;

import android.content.Context;

import com.eddy.clean.App;
import com.eddy.domain.dagger.qualifier.ContextLevel;
import com.eddy.domain.dagger.scope.ApplicationScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by wanqi on 16-6-21.
 */
@Module
public class ApplicationModule {

    private App mApp;

    public ApplicationModule(App app) {
        this.mApp = app;
    }

    @Provides
    @ContextLevel(ContextLevel.APPLICATION)
    @ApplicationScope
    public Context provideContext(){
        return mApp.getApplicationContext();
    }

}

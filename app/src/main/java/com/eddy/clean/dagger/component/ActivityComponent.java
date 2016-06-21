package com.eddy.clean.dagger.component;

import com.eddy.clean.dagger.base.BaseComponent;
import com.eddy.clean.dagger.module.ActivityModule;
import com.eddy.clean.home.HomeComponent;
import com.eddy.domain.dagger.scope.ActivityScope;

import dagger.Module;
import dagger.Subcomponent;

/**
 * Created by wanqi on 16-6-17.
 */

@ActivityScope
@Subcomponent(modules = {ActivityModule.class})
public interface ActivityComponent extends BaseComponent {

    HomeComponent provideHomeComponent();
}

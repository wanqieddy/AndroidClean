package com.eddy.clean.dagger.component;

import com.eddy.clean.dagger.base.BaseComponent;
import com.eddy.clean.dagger.module.ActivityModule;
import com.eddy.clean.dagger.module.ApplicationModule;
import com.eddy.data.module.ApiModule;
import com.eddy.domain.dagger.scope.ApplicationScope;

import dagger.Component;

/**
 * Created by wanqi on 16-6-21.
 */

@ApplicationScope
@Component(modules = {ApplicationModule.class, ApiModule.class})
public interface ApplicationComponent extends BaseComponent{

    ActivityComponent provideActivityComponent(ActivityModule activityModule);

}

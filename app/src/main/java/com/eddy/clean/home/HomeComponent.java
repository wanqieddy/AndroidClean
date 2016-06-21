package com.eddy.clean.home;

import com.eddy.clean.dagger.base.BaseComponent;

import dagger.Subcomponent;

/**
 * Created by wanqi on 16-6-20.
 */
@Subcomponent
public interface HomeComponent extends BaseComponent{

    void inject(HomeActivity homeActivity);
}

package com.eddy.clean.dagger.base;

import android.support.annotation.NonNull;

import com.eddy.clean.dagger.component.ActivityComponent;

/**
 * Created by wanqi on 16-6-17.
 */
public interface BaseActivityDagger<C extends BaseComponent>{

    @NonNull
    C initComponet(@NonNull ActivityComponent activityComponent);

}

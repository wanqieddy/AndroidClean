package com.eddy.clean.dagger.provide;

import android.content.Context;

import com.trello.rxlifecycle.ActivityLifecycleProvider;
import com.trello.rxlifecycle.FragmentLifecycleProvider;

/**
 * Created by wanqi on 16-6-18.
 */
public class PresenterProvide {


    private Context mContext;
    private ActivityLifecycleProvider mActivityLifecycleProvider;
    private FragmentLifecycleProvider mFragmentLifecycleProvider;

    public PresenterProvide(ActivityLifecycleProvider activityLifecycleProvider, FragmentLifecycleProvider fragmentLifecycleProvider, Context context) {

        this.mContext = context;
        this.mActivityLifecycleProvider = activityLifecycleProvider;
        this.mFragmentLifecycleProvider = fragmentLifecycleProvider;
    }

    public FragmentLifecycleProvider getFragmentLifecycleProvider() {
        return mFragmentLifecycleProvider;
    }

    public ActivityLifecycleProvider getActivityLifecycleProvider() {
        return mActivityLifecycleProvider;
    }

    public Context getContext() {
        return mContext;
    }



}

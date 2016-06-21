package com.eddy.clean.base.rx;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;

import com.trello.rxlifecycle.LifecycleTransformer;

import rx.Observable;

/**application的provider
 * Created by liutao on 2016/6/12.
 */
public interface ApplicationLifecycleProvider {
    @NonNull
    @CheckResult
    Observable<ApplicationEvent> lifecycle();


    @NonNull
    @CheckResult
    <T> LifecycleTransformer<T> bindUntilEvent(@NonNull ApplicationEvent event);


}

package com.eddy.clean.base.rx;

import android.app.Application;
import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;

import com.trello.rxlifecycle.LifecycleTransformer;
import com.trello.rxlifecycle.RxLifecycle;

import rx.Observable;
import rx.subjects.BehaviorSubject;

/**可指定observe生命周期的application
 * Created by PLU on 2016/6/12.
 */
public class RxApp extends Application implements ApplicationLifecycleProvider{
    private final BehaviorSubject<ApplicationEvent> lifecycleSubject = BehaviorSubject.create();

    @Override
    @NonNull
    @CheckResult
    public final Observable<ApplicationEvent> lifecycle() {
        return lifecycleSubject.asObservable();
    }

    @Override
    @NonNull
    @CheckResult
    public final <T> LifecycleTransformer<T> bindUntilEvent(@NonNull ApplicationEvent event) {
        return RxLifecycle.bindUntilEvent(lifecycleSubject, event);
    }


    @Override
    public void onCreate() {
        super.onCreate();
        lifecycleSubject.onNext(ApplicationEvent.ONCREATE);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        lifecycleSubject.onNext(ApplicationEvent.ONLOWMEMORY);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        lifecycleSubject.onNext(ApplicationEvent.ONTERMINATE);
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        lifecycleSubject.onNext(ApplicationEvent.ONTRIMMENORY);
    }
}

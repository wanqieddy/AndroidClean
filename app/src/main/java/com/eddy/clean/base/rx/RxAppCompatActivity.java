package com.eddy.clean.base.rx;

import android.os.Bundle;
import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.trello.rxlifecycle.ActivityEvent;
import com.trello.rxlifecycle.ActivityLifecycleProvider;
import com.trello.rxlifecycle.LifecycleTransformer;
import com.trello.rxlifecycle.RxLifecycle;

import rx.Observable;
import rx.subjects.BehaviorSubject;

/**
 * Created by wanqi on 16-6-17.
 */
public class RxAppCompatActivity extends AppCompatActivity implements ActivityLifecycleProvider{

    private final BehaviorSubject<ActivityEvent> lifecycleSubject = BehaviorSubject.create();

    @CheckResult
    @NonNull
    @Override
    public Observable<ActivityEvent> lifecycle() {
        return lifecycleSubject.asObservable();
    }


    @CheckResult
    @NonNull
    @Override
    public <T> LifecycleTransformer<T> bindUntilEvent(@NonNull ActivityEvent event) {
        return RxLifecycle.bindUntilEvent(lifecycleSubject, event);
    }

    @CheckResult
    @NonNull
    @Override
    public <T> LifecycleTransformer<T> bindToLifecycle() {
        return RxLifecycle.bindActivity(lifecycleSubject);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lifecycleSubject.onNext(ActivityEvent.CREATE);
    }

    @Override
    protected void onStart() {
        lifecycleSubject.onNext(ActivityEvent.START);
        super.onStart();
    }

    @Override
    protected void onResume() {
        lifecycleSubject.onNext(ActivityEvent.RESUME);
        super.onResume();
    }

    @Override
    protected void onPause() {
        lifecycleSubject.onNext(ActivityEvent.PAUSE);
        super.onPause();
    }

    @Override
    protected void onStop() {
        lifecycleSubject.onNext(ActivityEvent.STOP);
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        lifecycleSubject.onNext(ActivityEvent.DESTROY);
        super.onDestroy();
    }
}

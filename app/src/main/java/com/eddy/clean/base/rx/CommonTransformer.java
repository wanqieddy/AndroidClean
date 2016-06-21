package com.eddy.clean.base.rx;

import com.trello.rxlifecycle.ActivityEvent;
import com.trello.rxlifecycle.ActivityLifecycleProvider;
import com.trello.rxlifecycle.FragmentEvent;
import com.trello.rxlifecycle.FragmentLifecycleProvider;

import rx.Observable;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**一个公共的Transformer，用来处理Observe的一些重复方法
 * Created by PLU on 2016/6/7.
 */
public class CommonTransformer<T> implements Observable.Transformer<T, T> {
    private Scheduler observeScheduler;
    private Scheduler subscribeScheduler;
    private ApplicationLifecycleProvider applicationLifecycleProvider;
    private ActivityLifecycleProvider activityLifecycleProvider;
    private FragmentLifecycleProvider fragmentLifecycleProvider;
    private ActivityEvent activityEvent;
    private FragmentEvent fragmentEvent;
    private ApplicationEvent applicationEvent;
    private CommonTransformer(){//不允许直接new
    }
    public static class Builder<T>{
        private Scheduler observeScheduler= AndroidSchedulers.mainThread();//观察者线程调度器,默认主线程
        private Scheduler subscribeScheduler=Schedulers.io();//被观察者  默认io线程

        private ActivityLifecycleProvider activityLifecycleProvider;
        private ActivityEvent activityEvent= ActivityEvent.DESTROY;

        private FragmentLifecycleProvider fragmentLifecycleProvider;
        private FragmentEvent fragmentEvent= FragmentEvent.DESTROY;

        private ApplicationLifecycleProvider applicationLifecycleProvider;
        private ApplicationEvent applicationEvent=ApplicationEvent.ONTERMINATE;
        public Builder(){
        }
        public Builder(ApplicationLifecycleProvider provider){
            this.applicationLifecycleProvider=provider;
        }
        public Builder(ActivityLifecycleProvider provider){
            this.activityLifecycleProvider=provider;
        }
        public Builder(FragmentLifecycleProvider provider){
            this.fragmentLifecycleProvider=provider;
        }
        public Builder<T> observeOn(Scheduler observeScheduler){
            this.observeScheduler=observeScheduler;
            return this;
        }
        public Builder<T> subscribeOn(Scheduler subscribeScheduler){
            this.subscribeScheduler=subscribeScheduler;
            return this;
        }
        public Builder<T> bindUntilEvent(FragmentEvent fragmentEvent){
            this.fragmentEvent=fragmentEvent;
            return this;
        }
        public Builder<T> bindUntilEvent(ActivityEvent activityEvent){
            this.activityEvent=activityEvent;
            return this;
        }
        public Builder<T> bindUntilEvent(ApplicationEvent applicationEvent){
            this.applicationEvent=applicationEvent;
            return this;
        }
        public CommonTransformer<T> build(){
            CommonTransformer<T> commonTransformer=new CommonTransformer<>();
            commonTransformer.subscribeScheduler=subscribeScheduler;
            commonTransformer.observeScheduler=observeScheduler;

            commonTransformer.activityLifecycleProvider=activityLifecycleProvider;
            commonTransformer.activityEvent=activityEvent;

            commonTransformer.fragmentLifecycleProvider=fragmentLifecycleProvider;
            commonTransformer.fragmentEvent=fragmentEvent;

            commonTransformer.applicationLifecycleProvider=applicationLifecycleProvider;
            commonTransformer.applicationEvent=applicationEvent;
            return commonTransformer;
        }
    }
    @Override
    public Observable<T> call(Observable<T> observable) {
        if(activityLifecycleProvider!=null){//activity
            return observable.compose(activityLifecycleProvider.<T>bindUntilEvent(activityEvent)).subscribeOn(subscribeScheduler).observeOn(observeScheduler);
        }
        if(fragmentLifecycleProvider!=null){//fragment
            return observable.compose(fragmentLifecycleProvider.<T>bindUntilEvent(fragmentEvent)).subscribeOn(subscribeScheduler).observeOn(observeScheduler);
        }
        if(applicationLifecycleProvider!=null){//application
            return observable.compose(applicationLifecycleProvider.<T>bindUntilEvent(applicationEvent)).subscribeOn(subscribeScheduler).observeOn(observeScheduler);
        }
        return observable.subscribeOn(subscribeScheduler).observeOn(observeScheduler);
    }
}

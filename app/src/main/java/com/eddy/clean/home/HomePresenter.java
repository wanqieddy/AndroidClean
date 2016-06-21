package com.eddy.clean.home;

import android.util.Log;

import com.eddy.clean.base.mvp.BaseMvpPresenter;
import com.eddy.clean.base.rx.CommonTransformer;
import com.eddy.clean.dagger.provide.PresenterProvide;
import com.eddy.domain.entity.HomeBean;
import com.eddy.domain.usercase.HomeDataUseCase;

import javax.inject.Inject;

import rx.Subscriber;

/**
 * Created by wanqi on 16-6-20.
 */
public class HomePresenter extends BaseMvpPresenter<HomeView> {


    private HomeDataUseCase mHomeDataUseCase;


    @Inject
    public HomePresenter(PresenterProvide presenterProvide, HomeDataUseCase homeDataUseCase) {
        super(presenterProvide);
        this.mHomeDataUseCase = homeDataUseCase;
    }

    public void getHomeData(){

        mHomeDataUseCase.getHomeData().compose(new CommonTransformer.Builder<HomeBean>(mActivityLifecycleProvider).build()).subscribe(new Subscriber<HomeBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Log.i("ss","__________________________________e:"+e);
            }

            @Override
            public void onNext(HomeBean homeBean) {
                Log.i("ss","_________________________________________homeBean:"+homeBean);
            }
        });
    }

}

package com.eddy.data.repository;

import com.eddy.data.net.api.service.HomeApiService;
import com.eddy.data.net.mapper.EntityGsonMapper;
import com.eddy.data.net.retrofit.RetrofitHelper;
import com.eddy.domain.entity.HomeBean;
import com.eddy.domain.repository.HomeDataRepository;
import com.google.gson.Gson;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by wanqi on 16-6-20.
 */
public class HomeDataRepositoryImpl extends DataRepositoryImpl implements HomeDataRepository {

    @Inject
    public HomeDataRepositoryImpl(RetrofitHelper mRetrofitHelper) {
        super(mRetrofitHelper);
    }

    @Override
    public Observable<HomeBean> getHomeData() {
        return mRetrofitHelper.createService(HomeApiService.class).getHomeData().flatMap(new EntityGsonMapper<HomeBean>(HomeBean.class, mGson));
    }
}

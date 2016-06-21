package com.eddy.data.repository;

import com.eddy.data.net.retrofit.RetrofitHelper;
import com.eddy.domain.repository.DataRepository;
import com.google.gson.Gson;

import javax.inject.Inject;

/**
 * Created by wanqi on 16-6-20.
 */
public class DataRepositoryImpl implements DataRepository{

    protected RetrofitHelper mRetrofitHelper;
    protected Gson mGson;

    @Inject
    public DataRepositoryImpl(RetrofitHelper mRetrofitHelper) {
        this.mGson = new Gson();
        this.mRetrofitHelper = mRetrofitHelper;
    }


}

package com.eddy.data.net.api.service;

import retrofit2.Response;
import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by wanqi on 16-6-20.
 */
public interface HomeApiService {

    public static final String HOST = "http://app.piaojuke.com/";

    @GET("home")
    Observable<Response<String>> getHomeData();

}

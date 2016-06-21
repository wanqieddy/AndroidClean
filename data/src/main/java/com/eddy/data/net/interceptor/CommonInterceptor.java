package com.eddy.data.net.interceptor;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by PLU on 2016/6/8.
 */
public abstract class CommonInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
            Response response = chain.proceed(request);
        if (response.isSuccessful()) {
            onSuccess(response, request);
        }else{
            onFail(response,request);
        }
        return response;
    }

    public abstract void onFail(Response response, Request request);

    public abstract void onSuccess(Response response, Request request);
}

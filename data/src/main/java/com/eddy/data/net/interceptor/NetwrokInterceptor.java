package com.eddy.data.net.interceptor;

import android.util.Log;
import javax.inject.Inject;

import okhttp3.Headers;
import okhttp3.Request;
import okhttp3.Response;

/**网络拦截，处理基本数据，比如时间之类
 * Created by liuj on 2016/5/25.
 */
public class NetwrokInterceptor extends CommonInterceptor {

    @Inject
    public NetwrokInterceptor() { }

    @Override
    public void onFail(Response response, Request request) {


    }

    @Override
    public void onSuccess(Response response, Request request) {

        Headers headers = response.headers();
        Log.i("ss","________________________OkHttp-Sent-Millis:"+Long.parseLong(headers.get("OkHttp-Sent-Millis")));
        Log.i("ss","________________________OkHttp-Received-Millis:"+Long.parseLong(headers.get("OkHttp-Received-Millis")));

        long interval = Long.parseLong(headers.get("OkHttp-Received-Millis")) - Long.parseLong(headers.get("OkHttp-Sent-Millis"));

        Log.i("ss","_______________________________________________path:"+request.url().url().getPath()+"__interval:"+interval+"\n");
    }
}

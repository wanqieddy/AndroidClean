package com.eddy.data.net.mapper;

import android.util.Log;

import com.eddy.data.exception.TgaException;
import com.google.gson.Gson;
import retrofit2.Response;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

/**
 * Created by liujun on 16-5-26.
 */
public class EntityGsonMapper<R> implements Func1<Response<String>, Observable<? extends R>> {

    private Class<R> type;
    private Gson gson;

    public EntityGsonMapper(Class<R> responseType, Gson gson) {
        type = responseType;
        this.gson = gson;
    }

    @Override
    public Observable<? extends R> call(final Response<String> response) {

        return Observable.create(new Observable.OnSubscribe<R>() {
            @Override
            public void call(Subscriber<? super R> subscriber) {
                try {
                    Log.e("thread",Thread.currentThread().getName());
                    String responseString = response.body().replace("<app_data>","").replace("</app_data>","");
                    Log.i("ss","____________________________responseString:"+responseString);
                    R result = gson.fromJson(responseString, type);
                    subscriber.onNext(result);
                    subscriber.onCompleted();
                } catch (Exception exception) {
                    Log.i("ss","_________________________________exception:"+exception);
                    subscriber.onError(new TgaException.Builder(TgaException.ERROR_PARSE, response.raw().request().url().toString()).msg(exception.getMessage()).build());
                }
            }
        });

    }
}

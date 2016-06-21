package com.eddy.data.net.retrofit;

import android.util.Log;

import java.security.cert.CertificateException;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;

/**
 * Created by liujun on 16-5-19.
 */
public class RetrofitHelper {

    private Map<Class<?>, String> apiMap;
    private Set<Interceptor> defInterceptors;

    @Inject
    public RetrofitHelper(Map<Class<?>, String> apiMap, Set<Interceptor> defInterceptors) {
        this.apiMap = apiMap;
        this.defInterceptors = defInterceptors;
    }


    public <T> T createService(Class<T> apiClazz, Interceptor... interceptors) {

        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        if (defInterceptors != null) {
            for (Interceptor interceptor : defInterceptors) {
                builder.addInterceptor(interceptor);
            }
        }

        if (interceptors != null) {
            for (Interceptor interceptor : interceptors) {
                builder.addInterceptor(interceptor);
            }
        }

        OkHttpClient okHttpClient =builder.sslSocketFactory(getSslSocketFactory()).build();

        Log.i("ss","_____________________________________________apiClazz:"+apiMap.get(apiClazz)+"____apiClazz:"+apiClazz);
        Retrofit retrofit = new Retrofit.Builder().baseUrl(apiMap.get(apiClazz)).addCallAdapterFactory(RxJavaCallAdapterFactory.create()).addConverterFactory(new CustomConverterFactory())
                .client(okHttpClient).build();

        return retrofit.create(apiClazz);
    }


    private static SSLSocketFactory getSslSocketFactory() {
        SSLSocketFactory sslSocketFactory = null;
        try {
            final TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
                @Override
                public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {

                }

                @Override
                public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                }

                @Override
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
            }};

            // Install the all-trusting trust manager
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            // Create an ssl socket factory with our all-trusting manager
            sslSocketFactory = sslContext.getSocketFactory();

        } catch (Exception e) {
            Log.i("ss", "____________________________________e:" + e);
        }
        return sslSocketFactory;
    }


}

package com.eddy.data.module;

import com.eddy.data.net.api.service.HomeApiService;
import com.eddy.data.net.interceptor.NetwrokInterceptor;
import com.eddy.data.repository.HomeDataRepositoryImpl;
import com.eddy.domain.dagger.scope.ApplicationScope;
import com.eddy.domain.repository.HomeDataRepository;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;

/**
 * Created by wanqi on 16-6-20.
 */
@Module
public class ApiModule {

    @Provides
    @ApplicationScope
    Map<Class<?>, String> provideApiMap(){

        Map<Class<?>, String> map = new HashMap<>();
        map.put(HomeApiService.class, HomeApiService.HOST);

        return map;
    }

    @Provides
    @ApplicationScope
    Set<Interceptor> provideDefInterceptor(NetwrokInterceptor netwrokInterceptor){

        Set<Interceptor> interceptorSet = new HashSet<>();
        interceptorSet.add(netwrokInterceptor);

        return interceptorSet;
    }

    @Provides
    @ApplicationScope
    HomeDataRepository provideHomeDataRepository(HomeDataRepositoryImpl homeDataRepositoryImpl){
        return homeDataRepositoryImpl;
    }



}

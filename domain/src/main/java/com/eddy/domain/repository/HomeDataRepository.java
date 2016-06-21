package com.eddy.domain.repository;

import com.eddy.domain.entity.HomeBean;

import rx.Observable;

/**
 * Created by wanqi on 16-6-20.
 */
public interface HomeDataRepository extends DataRepository {

    Observable<HomeBean> getHomeData();

}

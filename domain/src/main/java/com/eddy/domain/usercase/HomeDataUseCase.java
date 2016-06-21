package com.eddy.domain.usercase;

import com.eddy.domain.entity.HomeBean;
import com.eddy.domain.repository.HomeDataRepository;
import com.eddy.domain.usercase.base.BaseUseCase;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by wanqi on 16-6-20.
 */
public class HomeDataUseCase extends BaseUseCase<HomeDataRepository> {

    @Inject
    public HomeDataUseCase(HomeDataRepository homeDataRepository) {
        super(homeDataRepository);
    }

    public Observable<HomeBean> getHomeData(){

        return dataRepository.getHomeData();
    }
}

package com.eddy.domain.usercase.base;

import com.eddy.domain.repository.DataRepository;

/**
 * Created by wanqi on 16-6-20.
 */
public class BaseUseCase <T extends DataRepository> implements UseCase {

    protected T dataRepository;

    public BaseUseCase(T t){ this.dataRepository = t; }

}

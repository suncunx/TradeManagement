package com.architecture.mvp;

import com.tamic.novate.Novate;

import java.lang.reflect.ParameterizedType;

import javax.inject.Inject;

/**
 * Created by Stephen Sun on 2017/6/11 0011.
 * Email:suncunx@qq.com
 */

public abstract class BaseNovatePresenterImpl<V extends BaseView, T> extends BasePresenterImpl<V> {

    protected T service;
    protected Novate novate;

    @Override
    public void attachView(V view) {
        super.attachView(view);
        createService();
    }

    private void createService() {
        Class<T> tClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
        service = novate.create(tClass);
    }

    public Novate getNovate() {
        return novate;
    }

    @Inject
    public void setNovate(Novate novate) {
        this.novate = novate;
    }
}

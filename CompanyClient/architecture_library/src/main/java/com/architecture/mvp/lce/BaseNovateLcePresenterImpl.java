package com.architecture.mvp.lce;

import android.content.Context;

import com.architecture.di.components.ApplicationComponent;
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;
import com.tamic.novate.Novate;

import java.lang.reflect.ParameterizedType;

import javax.inject.Inject;

/**
 * Created by Stephen Sun on 2017/6/11 0011.
 * Email:suncunx@qq.com
 */

public abstract class BaseNovateLcePresenterImpl<V extends BaseLceView, T> extends MvpBasePresenter<V> {

    protected Context context;
    protected T service;
    protected Novate novate;

    @Override
    public void attachView(V view) {
        super.attachView(view);
        context = view.getApplicationComponent().context();
        initializeInjector(view.getApplicationComponent());
        createService();
        init();
    }
    protected void init(){}
    private void createService() {
        Class<T> tClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
        service = novate.create(tClass);
    }

    protected abstract void initializeInjector(ApplicationComponent applicationComponent);

    public Novate getNovate() {
        return novate;
    }

    @Inject
    public void setNovate(Novate novate) {
        this.novate = novate;
    }
}

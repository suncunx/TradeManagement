package com.architecture.mvp;

import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.architecture.application.AndroidApplication;
import com.architecture.di.components.ApplicationComponent;
import com.hannesdorfmann.mosby3.mvp.MvpFragment;
import com.hannesdorfmann.mosby3.mvp.MvpPresenter;

import javax.inject.Inject;

/**
 * Created by Stephen Sun on 2017/5/25 0025.
 * Email:suncunx@qq.com
 * 最基本的fragment不需要下拉刷新，有的确实不需要
 * 注意没有在ViewCreated方法中调用loadData方法
 */

public abstract class BaseFragment<V extends BaseView, P extends MvpPresenter<V>> extends MvpFragment<V, P> implements BaseView {

    protected ViewDataBinding viewDataBinding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        initializeInjector();
        viewDataBinding = createViewDataBinding(inflater, container);
        if (viewDataBinding == null) {
            throw new NullPointerException("ViewDataBinding is null , please check createViewDataBinding() method");
        }
        return viewDataBinding.getRoot();
    }

    // 此方法会在onCreateView方法返回View之后立即调用，Fragment为我们提供了此方法，用于VIew的初始化工作
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(); // 此方法一定是在loadData方法之前调用，所以可以直接在子类重写，在加载数据之前做初始化工作
    }

    @Inject
    @Override
    public void setPresenter(@NonNull P presenter) {
        super.setPresenter(presenter);
    }

    @NonNull
    @Override
    public P createPresenter() {
        return getPresenter();
    }

    public abstract ViewDataBinding createViewDataBinding(LayoutInflater inflater, ViewGroup container);

    public void init(){}

    public abstract void initializeInjector();
    public ApplicationComponent getApplicationComponent() {
        return ((AndroidApplication) getActivity().getApplication()).getApplicationComponent();
    }

}

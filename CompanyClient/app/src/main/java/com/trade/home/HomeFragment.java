package com.trade.home;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.architecture.model.AdapterBean;
import com.architecture.mvp.rv.BaseRvFragment;
import com.trade.BR;
import com.trade.R;
import com.trade.home.di.DaggerHomeComponent;
import com.trade.home.di.HomeModule;
import com.trade.home.mvp.HomePresenter;
import com.trade.home.mvp.HomeView;
import com.trade.util.ApiManager;


public class HomeFragment extends BaseRvFragment<SwipeRefreshLayout, AdapterBean, HomeView, HomePresenter> implements HomeView {

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    protected String getErrorMessage(Throwable e, boolean pullToRefresh) {
        if (e instanceof IndexOutOfBoundsException) {
            return "您还没有订单哦~点击刷新";
        }
        return ApiManager.CONNECT_TIME_OUT;
    }

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void init() {
//        loadData(false);
    }

    @Override
    public ViewDataBinding createViewDataBinding(LayoutInflater inflater, ViewGroup container) {
        return DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
    }

    @Override
    public void initializeInjector() {
        DaggerHomeComponent.builder()
                .homeModule(new HomeModule())
                .build()
                .inject(this);
    }

    @Override
    public void setData(AdapterBean data) {
        viewDataBinding.setVariable(BR.adapterBean, data);
    }

    // 程序运行即调用此方法
    @Override
    public void loadData(boolean pullToRefresh) {
        presenter.loadTitles(pullToRefresh, getFragmentManager());
    }

    public void onTitleChange(boolean isOutBill) {
        presenter.onTitleChange(isOutBill);
    }


    @Override
    public Activity getHomeActivity() {
        return getActivity();
    }

    public void refresh() {
        loadData(true);
    }
}

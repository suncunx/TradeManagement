package com.trade.goods.ui;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.architecture.model.AdapterBean;
import com.architecture.mvp.rv.BaseRvFragment;
import com.trade.BR;
import com.trade.R;
import com.trade.goods.di.DaggerGoodsComponent;
import com.trade.goods.di.GoodsModule;
import com.trade.goods.presenter.GoodsPresenter;
import com.trade.goods.view.GoodsView;
import com.trade.util.ApiManager;

public class GoodsFragment extends BaseRvFragment<SwipeRefreshLayout, AdapterBean, GoodsView, GoodsPresenter> implements GoodsView {

    public GoodsFragment() {
        // Required empty public constructor
    }

    @Override
    protected String getErrorMessage(Throwable e, boolean pullToRefresh) {
        if (e instanceof IndexOutOfBoundsException) {
            return "您还没有商品哦~点击刷新";
        }
        return ApiManager.CONNECT_TIME_OUT;
    }

    @Override
    public void init() {
//        presenter.loadGoods();
    }

    public static GoodsFragment newInstance() {
        GoodsFragment fragment = new GoodsFragment();
        return fragment;
    }

    @Override
    public ViewDataBinding createViewDataBinding(LayoutInflater inflater, ViewGroup container) {
        return DataBindingUtil.inflate(inflater, R.layout.fragment_goods, container, false);
    }

    @Override
    public void initializeInjector() {
        DaggerGoodsComponent.builder()
                .applicationComponent(getApplicationComponent())
                .goodsModule(new GoodsModule())
                .build()
                .inject(this);
    }

    @Override
    public void onRefresh() {
        presenter.refreshGoods();
    }

    @Override
    public void setData(AdapterBean data) {
        viewDataBinding.setVariable(BR.adapterBean, data);
    }

    @Override
    public void loadData(boolean pullToRefresh) {
        presenter.loadGoods();
    }

    @Override
    public void refreshGoods() {
        contentView.setRefreshing(false);
    }

    @Override
    public Activity getGoodsActivity() {
        return getActivity();
    }
}

package com.architecture.mvp.rv;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.architecture.R;
import com.architecture.di.components.ApplicationComponent;
import com.architecture.mvp.refresh.BaseRefreshToolbarActivity;

/**
 * Created by Stephen Sun on 2017/7/19 0019.
 * Email:suncunx@qq.com
 */

public abstract class BaseRvToolbarActivity<CV extends SwipeRefreshLayout, M, V extends BaseRvView<M>, P extends BaseRvPresenter<V>> extends BaseRefreshToolbarActivity<CV, M, V, P> implements BaseRvView<M>, SwipeRefreshLayout.OnRefreshListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadData(false);
    }

    @Override
    protected void init() {
        presenter.setOnLoadMoreListener((V) this);
    }

    @Override
    public ApplicationComponent getApplicationComponent() {
        return super.getApplicationComponent();
    }

    @Override
    public void onRefresh() {
        loadData(true);
    }

    @Override
    public void stopRefresh() {
        contentView.setRefreshing(false);
    }

    // 应该在onCreate方法之后调用此方法
    @Override
    public RecyclerView getRecyclerView() {
        RecyclerView recyclerView = viewDataBinding.getRoot().findViewById(R.id.recyclerView);
        if (recyclerView == null) {
            throw new NullPointerException("recyclerView is null,have you declare the id as 'recyclerView'?");
        }
        return recyclerView;
    }
}

package com.architecture.mvp.rv;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.architecture.R;
import com.architecture.mvp.refresh.BaseRefreshFragment;

/**
 * Created by Stephen Sun on 2017/5/25 0025.
 * Email:suncunx@qq.com
 * 支持recyclerView的Fragment
 */
//TODO 将adapter去掉
public abstract class BaseRvFragment<CV extends SwipeRefreshLayout, M, V extends BaseRvView<M>, P extends BaseRvPresenter<V>> extends BaseRefreshFragment<CV, M, V, P> implements BaseRvView<M>, SwipeRefreshLayout.OnRefreshListener{

//    protected BaseQuickAdapter adapter;

    // 此方法会在onCreateView方法返回View之后立即调用，Fragment为我们提供了此方法，用于View的初始化工作
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        presenter.setOnLoadMoreListener((V) this);
        // 此时injector已经初始化完成
//        initAdapter();
        // 加载数据
        loadData(false);
    }
//
//    private void initAdapter() {
//        adapter.setOnItemClickListener(this);
//        adapter.setOnLoadMoreListener(this, (RecyclerView) viewDataBinding.getRoot().findViewById(R.id.recyclerView));
//    }
//    @Inject
//    public void setAdapter(BaseQuickAdapter adapter) {
//        this.adapter = adapter;
//        if (adapter == null) {
//            throw new NullPointerException("adapter is null , please check createAdapter() method");
//        }
//    }

    @Override
    protected String getErrorMessage(Throwable e, boolean pullToRefresh) {
        return e.getMessage();
    }

    @Override
    public void onRefresh() {
        loadData(true);
    }

    @Override
    public void stopRefresh() {
        contentView.setRefreshing(false);
    }

    @Override
    public RecyclerView getRecyclerView() {
        RecyclerView recyclerView = viewDataBinding.getRoot().findViewById(R.id.recyclerView);
        if (recyclerView == null) {
            throw new NullPointerException("recyclerView is null,have you declare the id as 'recyclerView'?");
        }
        return recyclerView;
    }
}

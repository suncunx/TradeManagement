package com.architecture.mvp.rv;

import com.architecture.mvp.lce.BaseLcePresenterImpl;
import com.chad.library.adapter.base.BaseQuickAdapter;


/**
 * Created by Stephen Sun on 2017/5/22 0022.
 * Email:suncunx@qq.com
 */

public abstract class BaseRvPresenterImpl<V extends BaseRvView> extends BaseLcePresenterImpl<V> implements BaseRvPresenter<V>, BaseQuickAdapter.OnItemClickListener, BaseQuickAdapter.RequestLoadMoreListener {
    protected BaseQuickAdapter adapter;

    @Override
    public void attachView(V view) {
        super.attachView(view);
        adapter = createAdapter();
        adapter.setOnItemClickListener(this);
        init();
    }

    protected abstract BaseQuickAdapter createAdapter();

    protected abstract void loadMore();

    protected void init(){}
    @Override
    public void onLoadMoreRequested() {
        getView().stopRefresh();
        loadMore();
    }

    // 此方法需要在onCreate的viewDataBinding初始化完成之后调用
    public void setOnLoadMoreListener(V view) {
        adapter.setOnLoadMoreListener(this, view.getRecyclerView());
    }

    protected void loadComplete() {
        adapter.loadMoreComplete();
    }

    protected void loadMoreEnd(boolean gone) {
        adapter.loadMoreEnd(gone);
    }
}

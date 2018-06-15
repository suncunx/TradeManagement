package com.architecture.mvp.rv;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.architecture.R;
import com.architecture.di.components.ApplicationComponent;
import com.architecture.mvp.refresh.BaseRefreshActivity;

/**
 * Created by Stephen Sun on 2017/5/23 0023.
 * Email:suncunx@qq.com
 * 此类只适合设计网络请求，下拉刷新，上拉加载的界面
 * 对于普通不需要网络的界面，只需要继承普通的MvpActivity即可
 */

public abstract class BaseRvActivity<CV extends SwipeRefreshLayout, M, V extends BaseRvView<M>, P extends BaseRvPresenter<V>> extends BaseRefreshActivity<CV, M, V, P> implements BaseRvView<M>, SwipeRefreshLayout.OnRefreshListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        loadData(false);
    }

    /**
     * 获取ApplicationComponent {@link ApplicationComponent}从而直接在依赖此Component的Component中的Module使用Component暴露
     * 的对象
     *
     * @return
     */
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

    @Override
    public RecyclerView getRecyclerView() {
        RecyclerView recyclerView = viewDataBinding.getRoot().findViewById(R.id.recyclerView);
        if (recyclerView == null) {
            throw new NullPointerException("recyclerView is null,have you declare the id as 'recyclerView'?");
        }
        return recyclerView;
    }
}

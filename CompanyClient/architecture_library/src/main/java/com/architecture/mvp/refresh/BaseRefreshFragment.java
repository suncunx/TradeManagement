package com.architecture.mvp.refresh;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;

import com.architecture.R;
import com.architecture.mvp.lce.BaseLceFragment;
import com.architecture.mvp.lce.BaseLceView;
import com.hannesdorfmann.mosby3.mvp.MvpPresenter;

/**
 * Created by Stephen Sun on 2017/5/25 0025.
 * Email:suncunx@qq.com
 * 带下拉刷新的fragment  注意也没有调用loadData方法
 * 此类必须继承自BaseLceFragment，便于调用contentView的刷新
 */

public abstract class BaseRefreshFragment<CV extends SwipeRefreshLayout, M, V extends BaseLceView<M>, P extends MvpPresenter<V>> extends BaseLceFragment<CV, M, V, P> implements SwipeRefreshLayout.OnRefreshListener {

    // 此方法会在onCreateView方法返回View之后立即调用，Fragment为我们提供了此方法，用于VIew的初始化工作
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Setup contentView == SwipeRefreshView
        contentView.setColorSchemeResources(R.color.colorAccent);
        contentView.setOnRefreshListener(this);
    }

    @Override
    public void showContent() {
        super.showContent();
        contentView.setRefreshing(false);
    }

    @Override
    public void showError(Throwable e, boolean pullToRefresh) {
        super.showError(e, pullToRefresh);
        contentView.setRefreshing(false);
    }
}

package com.architecture.mvp.refresh;

import android.support.v4.widget.SwipeRefreshLayout;

import com.architecture.R;
import com.architecture.mvp.lce.BaseLceToolbarActivity;
import com.hannesdorfmann.mosby3.mvp.MvpPresenter;
import com.hannesdorfmann.mosby3.mvp.lce.MvpLceView;

/**
 * Created by Stephen Sun on 2017/7/19 0019.
 * Email:suncunx@qq.com
 */

public abstract class BaseRefreshToolbarActivity<CV extends SwipeRefreshLayout, M, V extends MvpLceView<M>, P extends MvpPresenter<V>> extends BaseLceToolbarActivity<CV, M, V, P> implements SwipeRefreshLayout.OnRefreshListener {
    // 先调用onCreate，后调用此方法
    @Override
    public void onContentChanged() {
        super.onContentChanged();
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

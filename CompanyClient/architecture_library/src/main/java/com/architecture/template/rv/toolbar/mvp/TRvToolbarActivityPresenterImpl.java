package com.architecture.template.rv.toolbar.mvp;

import android.view.View;

import com.architecture.di.components.ApplicationComponent;
import com.architecture.mvp.rv.BaseRvPresenterImpl;
import com.chad.library.adapter.base.BaseQuickAdapter;

/**
 * Created by Stephen Sun on 2017/7/11 0011.
 * Email:suncunx@qq.com
 */

public class TRvToolbarActivityPresenterImpl extends BaseRvPresenterImpl<TRvToolbarActivityView> implements TRvToolbarActivityPresenter {

    @Override
    protected void initializeInjector(ApplicationComponent applicationComponent) {
        //TODO 需要网络操作的时候一般需要  不能用未初始化的变量
    }

    @Override
    public void loadData(boolean pullToRefresh) {
        getView().showLoading(pullToRefresh);

        //TODO 加载数据
        getView().showContent();
    }

    @Override
    protected BaseQuickAdapter createAdapter() {
        return null;
    }

    @Override
    public void loadMore() {

    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

    }
}

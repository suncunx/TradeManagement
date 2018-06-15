package com.architecture.template.rv.fragment.mvp;

import android.view.View;

import com.architecture.di.components.ApplicationComponent;
import com.architecture.mvp.rv.BaseRvPresenterImpl;
import com.chad.library.adapter.base.BaseQuickAdapter;

/**
 * Created by Stephen Sun on 2017/7/11 0011.
 * Email:suncunx@qq.com
 */

public class TRvFragmentPresenterImpl extends BaseRvPresenterImpl<TRvFragmentView> implements TRvFragmentPresenter {

    @Override
    protected void initializeInjector(ApplicationComponent applicationComponent) {
        // 可有可无，需要网络操作的时候一般需要
    }

    @Override
    public void loadData(boolean pullToRefresh) {

    }

    @Override
    public void loadMore() {
        //TODO 加载更多
    }

    @Override
    protected BaseQuickAdapter createAdapter() {
        return null;
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        //TODO 点击事件
    }
}

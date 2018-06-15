package com.architecture.template.lce.toolbar.mvp;

import com.architecture.di.components.ApplicationComponent;
import com.architecture.mvp.lce.BaseLcePresenterImpl;

/**
 * Created by Stephen Sun on 2017/7/11 0011.
 * Email:suncunx@qq.com
 */

public class TLceToolbarActivityPresenterImpl extends BaseLcePresenterImpl<TLceToolbarActivityView> implements TLceToolbarActivityPresenter {

    @Override
    protected void initializeInjector(ApplicationComponent applicationComponent) {
        //TODO 需要网络操作的时候一般需要
    }

    @Override
    public void loadData(boolean pullToRefresh) {
        getView().showLoading(pullToRefresh);

        //TODO 加载数据
        getView().showContent();
    }
}

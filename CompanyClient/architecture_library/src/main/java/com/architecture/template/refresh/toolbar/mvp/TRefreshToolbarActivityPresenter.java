package com.architecture.template.refresh.toolbar.mvp;

import com.architecture.mvp.lce.BaseLcePresenter;

/**
 * Created by Stephen Sun on 2017/7/11 0011.
 * Email:suncunx@qq.com
 */

public interface TRefreshToolbarActivityPresenter extends BaseLcePresenter<TRefreshToolbarActivityView> {
    //TODO 添加Presenter需要做的操作，与TLceActivityView有关
    void loadData(final boolean pullToRefresh);
}

package com.architecture.template.lce.activity.mvp;

import com.architecture.mvp.lce.BaseLcePresenter;

/**
 * Created by Stephen Sun on 2017/7/11 0011.
 * Email:suncunx@qq.com
 */

public interface TLceActivityPresenter extends BaseLcePresenter<TLceActivityView> {
    //TODO 添加Presenter需要做的操作，与TLceActivityView有关
    void loadData(final boolean pullToRefresh);
}

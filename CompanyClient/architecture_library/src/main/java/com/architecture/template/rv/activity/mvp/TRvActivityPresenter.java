package com.architecture.template.rv.activity.mvp;

import com.architecture.mvp.rv.BaseRvPresenter;

/**
 * Created by Stephen Sun on 2017/7/11 0011.
 * Email:suncunx@qq.com
 */

public interface TRvActivityPresenter extends BaseRvPresenter<TRvActivityView> {
    //TODO 添加Presenter需要做的操作，与TLceActivityView有关
    void loadData(final boolean pullToRefresh);
}

package com.architecture.template.refresh.fragment.mvp;

import com.architecture.mvp.lce.BaseLcePresenter;

/**
 * Created by Stephen Sun on 2017/7/11 0011.
 * Email:suncunx@qq.com
 */

public interface TRefreshFragmentPresenter extends BaseLcePresenter<TRefreshFragmentView> {
    //TODO 添加Presenter需要做的操作，与TFragmentView有关
    void loadData(final boolean pullToRefresh);
}

package com.trade.other.finance.mvp;

import com.architecture.mvp.lce.BaseLcePresenter;

/**
 * Created by Stephen Sun on 2017/7/11 0011.
 * Email:suncunx@qq.com
 */

public interface FinancePresenter extends BaseLcePresenter<FinanceView> {
    void loadData(final boolean pullToRefresh, String year);
}

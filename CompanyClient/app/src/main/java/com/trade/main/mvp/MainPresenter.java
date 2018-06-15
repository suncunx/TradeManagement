package com.trade.main.mvp;

import com.architecture.mvp.BasePresenter;

/**
 * Created by Stephen Sun on 2017/7/4 0004.
 * Email:suncunx@qq.com
 */

public interface MainPresenter extends BasePresenter<MainView> {
    void onTitleChange(boolean isOutBill);
}

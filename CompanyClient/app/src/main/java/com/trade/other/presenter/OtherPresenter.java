package com.trade.other.presenter;

import com.architecture.mvp.BasePresenter;
import com.trade.other.view.OtherView;

/**
 * Created by Stephen Sun on 2017/7/5 0005.
 * Email:suncunx@qq.com
 */

public interface OtherPresenter extends BasePresenter<OtherView> {

    void loadUser();
}

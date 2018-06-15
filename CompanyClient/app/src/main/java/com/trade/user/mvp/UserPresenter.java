package com.trade.user.mvp;

import com.architecture.mvp.BasePresenter;

/**
 * Created by Stephen Sun on 2017/7/5 0005.
 * Email:suncunx@qq.com
 */

public interface UserPresenter extends BasePresenter<UserView> {

    void loadUser();
}

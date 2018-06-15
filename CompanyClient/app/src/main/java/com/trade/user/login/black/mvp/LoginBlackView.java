package com.trade.user.login.black.mvp;

import com.architecture.mvp.BaseView;

/**
 * Created by Stephen Sun on 2017/7/11 0011.
 * Email:suncunx@qq.com
 */

public interface LoginBlackView extends BaseView {
    void requestLogin();

    void finishActivity();
}

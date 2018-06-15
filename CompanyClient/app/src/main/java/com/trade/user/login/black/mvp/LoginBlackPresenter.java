package com.trade.user.login.black.mvp;

import com.architecture.mvp.BasePresenter;
import com.trade.user.login.model.LoginBean;

/**
 * Created by Stephen Sun on 2017/7/11 0011.
 * Email:suncunx@qq.com
 */

public interface LoginBlackPresenter extends BasePresenter<LoginBlackView> {
    void requestLogin(LoginBean loginBean);

    void onLogin(LoginBean loginBean);
}

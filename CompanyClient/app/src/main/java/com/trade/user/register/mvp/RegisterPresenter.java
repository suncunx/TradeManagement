package com.trade.user.register.mvp;

import com.architecture.mvp.BasePresenter;
import com.trade.user.login.model.LoginBean;

/**
 * Created by Stephen Sun on 2017/7/11 0011.
 * Email:suncunx@qq.com
 */

public interface RegisterPresenter extends BasePresenter<RegisterView> {
//    void loginSuccess();

    void onLogin(LoginBean loginBean);

    void requestRegister(LoginBean loginBean);
}

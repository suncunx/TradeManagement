package com.trade.user.login.mvp;

import com.architecture.mvp.BasePresenter;
import com.trade.user.login.model.LoginBean;

/**
 * Created by Stephen Sun on 2017/7/11 0011.
 * Email:suncunx@qq.com
 */

public interface LoginPresenter extends BasePresenter<LoginView> {

    void onLogin(LoginBean loginBean);

    void requestLogin(LoginBean loginBean);
}

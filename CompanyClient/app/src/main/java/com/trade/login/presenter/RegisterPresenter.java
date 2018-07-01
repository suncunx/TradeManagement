package com.trade.login.presenter;

import com.architecture.mvp.BasePresenter;
import com.trade.login.model.LoginBean;
import com.trade.login.view.RegisterView;

/**
 * Created by Stephen Sun on 2017/7/11 0011.
 * Email:suncunx@qq.com
 */

public interface RegisterPresenter extends BasePresenter<RegisterView> {
//    void loginSuccess();

    void onLogin(LoginBean loginBean);

    void requestRegister(LoginBean loginBean);
}

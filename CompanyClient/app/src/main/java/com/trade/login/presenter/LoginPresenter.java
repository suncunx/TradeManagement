package com.trade.login.presenter;

import com.architecture.mvp.BasePresenter;
import com.trade.login.model.LoginBean;
import com.trade.login.view.LoginView;

/**
 * Created by Stephen Sun on 2017/7/11 0011.
 * Email:suncunx@qq.com
 */

public interface LoginPresenter extends BasePresenter<LoginView> {

    void onLogin(LoginBean loginBean);

    void requestLogin(LoginBean loginBean);
}

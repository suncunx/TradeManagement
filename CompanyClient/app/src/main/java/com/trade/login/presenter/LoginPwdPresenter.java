package com.trade.login.presenter;

import com.architecture.mvp.BasePresenter;
import com.trade.login.view.LoginPwdView;
import com.trade.login.model.LoginBean;

/**
 * Created by Stephen Sun on 2017/7/11 0011.
 * Email:suncunx@qq.com
 */

public interface LoginPwdPresenter extends BasePresenter<LoginPwdView> {
    void requestLogin(LoginBean loginBean);

    void onLogin(LoginBean loginBean);
}

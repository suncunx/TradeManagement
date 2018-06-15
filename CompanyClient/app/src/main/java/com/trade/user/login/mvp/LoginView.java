package com.trade.user.login.mvp;

import com.architecture.mvp.BaseView;

/**
 * Created by Stephen Sun on 2017/7/11 0011.
 * Email:suncunx@qq.com
 */

public interface LoginView extends BaseView {
    void submitCode();

    void finishActivity();

    void showProgressDialog(String prompt);

    void dismissProgressDialog();
}

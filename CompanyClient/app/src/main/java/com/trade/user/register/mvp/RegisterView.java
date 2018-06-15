package com.trade.user.register.mvp;

import com.architecture.mvp.BaseView;

/**
 * Created by Stephen Sun on 2017/7/11 0011.
 * Email:suncunx@qq.com
 */

public interface RegisterView extends BaseView {
    void submitCode();

    void finishActivity();

    void showProgressDialog(String prompt);

    void dismissProgressDialog();
}

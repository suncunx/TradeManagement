package com.trade.login.ui;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;

import com.architecture.mvp.BaseToolbarActivity;
import com.blankj.utilcode.util.ToastUtils;
import com.trade.BR;
import com.trade.R;
import com.trade.login.di.DaggerLoginComponent;
import com.trade.login.di.LoginModule;
import com.trade.login.model.LoginBean;
import com.trade.login.presenter.LoginPwdPresenter;
import com.trade.login.view.LoginPwdView;

/**
 * Created by Stephen Sun on 2017/7/11 0011.
 * Email:suncunx@qq.com
 */

public class LoginPwdActivity extends BaseToolbarActivity<LoginPwdView, LoginPwdPresenter> implements LoginPwdView {
    private LoginBean loginBean;
    private boolean showPwd;  // 是否显示密码 true 是
    private static final String LOGIN_BY_PWD = "密码登录";
    private static Activity activity;

    public static Intent getCallingIntent(Activity context) {
        activity = context;
        return new Intent(context, LoginPwdActivity.class);
    }

    @Override
    protected String createTitle() {
        return LOGIN_BY_PWD;
    }

    @Override
    public ViewDataBinding createViewDataBinding() {
        return DataBindingUtil.setContentView(this, R.layout.activity_login_pwd);
    }

    @Override
    public void initializeInjector() {
        DaggerLoginComponent.builder()
                .applicationComponent(getApplicationComponent())
                .loginModule(new LoginModule())
                .build()
                .inject(this);
    }

    @Override
    public void init() {

        loginBean = new LoginBean("", "", "");
        viewDataBinding.setVariable(BR.transMethod, PasswordTransformationMethod.getInstance()); // 默认不显示密码
        viewDataBinding.setVariable(BR.loginBean, loginBean);
        viewDataBinding.setVariable(BR.loginBlackListener, new LoginBlackClickListener());
    }

    public void requestLogin() {
        presenter.requestLogin(loginBean);
    }

    @Override
    public void finishActivity() {
        if (activity != null) {
            activity.finish();
        }
        finish();
    }

    public class LoginBlackClickListener {

        public void onShowPwd() { // 显示密码
            if (!showPwd) {
                viewDataBinding.setVariable(BR.transMethod, HideReturnsTransformationMethod.getInstance());
                showPwd = true;
            } else {
                viewDataBinding.setVariable(BR.transMethod, PasswordTransformationMethod.getInstance());
                showPwd = false;
            }
        }

        public void onLogin() {// 点击登录按钮  先提交验证码，验证成功再登录，如果已经验证过则可以直接登录
            presenter.onLogin(loginBean);
        }

        public void onVerify() {
            // 跳转到刚刚界面
            finish();
        }

        public void onForgetPwd() { // 新用户注册
            ToastUtils.showShort("忘记密码");
        }
    }
}

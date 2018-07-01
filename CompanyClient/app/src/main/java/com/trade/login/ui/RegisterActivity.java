package com.trade.login.ui;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Handler;
import android.os.Message;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.architecture.mvp.BaseToolbarActivity;
import com.blankj.utilcode.util.ToastUtils;
import com.trade.BR;
import com.trade.R;
import com.trade.login.di.DaggerLoginComponent;
import com.trade.login.di.LoginModule;
import com.trade.login.model.LoginBean;
import com.trade.login.presenter.RegisterPresenter;
import com.trade.login.util.LoginUtil;
import com.trade.login.view.RegisterView;
import com.trade.util.TimeCount;

import org.json.JSONException;
import org.json.JSONObject;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

/**
 * Created by Stephen Sun on 2017/7/11 0011.
 * Email:suncunx@qq.com
 */

public class RegisterActivity extends BaseToolbarActivity<RegisterView, RegisterPresenter> implements RegisterView {

    private boolean showPwd;  // 是否显示密码 true 是
    private boolean isVerified;
    private LoginBean loginBean;
    private static final String LOGIN_BY_PWD = "用户注册";
    private static Activity activity;

    public static Intent getCallingIntent(Activity context) {
        activity = context;
        return new Intent(context, RegisterActivity.class);
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
    protected String createTitle() {
        return LOGIN_BY_PWD;
    }

    @Override
    public ViewDataBinding createViewDataBinding() {
        return DataBindingUtil.setContentView(this, R.layout.activity_register);
    }

    @Override
    public void init() {
        loginBean = new LoginBean("", "", "");
        //        viewDataBinding.setVariable(BR.title, LOGIN_BY_PWD);
        viewDataBinding.setVariable(BR.transMethod, PasswordTransformationMethod.getInstance()); // 默认不显示密码
        viewDataBinding.setVariable(BR.loginBean, loginBean);
        viewDataBinding.setVariable(BR.registerListener, new RegisterClickListener());
    }

    /**
     * 短信验证功能向SmsHandler发送信息
     */
    private void SMSEventSendMessage() {
        EventHandler eh = new EventHandler() {

            @Override
            public void afterEvent(int event, int result, Object data) {

                Message msg = new Message();
                msg.arg1 = event;
                msg.arg2 = result;
                msg.obj = data;
                msg.what = 2;
                SmsHandler.sendMessage(msg);
            }
        };
        SMSSDK.registerEventHandler(eh);
    }

    /**
     * 处理mob发送过来的消息
     */
    private Handler SmsHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            int event = msg.arg1;
            int result = msg.arg2;
            Object data = msg.obj;
            if (result == SMSSDK.RESULT_COMPLETE) {
                // 短信注册成功后，返回MainActivity,然后提示新好友
                if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {// 提交验证码成功
                    submitVerifySuccess();

                } else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) { //发送验证码成功
                    dismissProgressDialog();
                    waitSeconds();
                    ToastUtils.showShort("验证码已经发送");
                } else if (event == SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES) {// 返回支持发送验证码的国家列表
                    Toast.makeText(getApplicationContext(), "获取国家列表成功", Toast.LENGTH_SHORT).show();
                }
            } else if (result == SMSSDK.RESULT_ERROR) {
                dismissProgressDialog();
                ((Throwable) data).printStackTrace();
                try {
                    String jsonStr = ((Throwable) data).getMessage();
                    if (!jsonStr.contains("{"))
                        return;
                    JSONObject json = new JSONObject(jsonStr);
                    ToastUtils.showShort(json.getString("detail"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }
    };

    private void waitSeconds() {
        new TimeCount(60000, 1, (TextView) viewDataBinding.getRoot().findViewById(R.id.tv_verify)).start();
    }

    // 提交验证码成功，请求注册
    private void submitVerifySuccess() {
        isVerified = true;
        presenter.requestRegister(loginBean);
    }

    @Override
    public void submitCode() {
        SMSSDK.submitVerificationCode("86", loginBean.getPhone(), loginBean.getVerify());
    }

    @Override
    public void finishActivity() {
        if (activity != null) {
            activity.finish();
        }
        finish();
    }
    private MaterialDialog progress;
    public void showProgressDialog(String prompt) {
        if (progress != null && progress.isShowing()) {
            return;
        }
        MaterialDialog.Builder builder = new MaterialDialog.Builder(this)
                .content(prompt)
                .progress(true, 0);
        progress = builder.cancelable(false)
                .canceledOnTouchOutside(false)
                .show();
    }

    public void dismissProgressDialog() {
        if (progress != null && progress.isShowing()) {
            progress.dismiss();
            progress = null;
        }
    }

    public class RegisterClickListener {

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
            if (isVerified) {
                presenter.requestRegister(loginBean);
                //                PreferUtil.setUserInfo(RegisterActivity.this, "1", loginBean.getPhone(), "已登录", "http://pic.jj20.com/up/allimg/911/0P316142450/160P3142450-4.jpg");
            } else {
                presenter.onLogin(loginBean); // 进行登录前输入检测，若检测无误则提交验证码
            }
        }

        public void onVerify() {
            // 发送验证码
            if (!LoginUtil.checkPhone(loginBean.getPhone())) {
                ToastUtils.showShort("手机号码不正确，请重新输入");
            } else {
                showProgressDialog("发送验证码...");
                isVerified = false;
                SMSSDK.getVerificationCode("86", loginBean.getPhone());
                SMSEventSendMessage();
            }
        }

    }
}

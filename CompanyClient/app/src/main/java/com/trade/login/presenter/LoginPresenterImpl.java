package com.trade.login.presenter;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.architecture.di.components.ApplicationComponent;
import com.architecture.mvp.BasePresenterImpl;
import com.architecture.util.BasePreferUtil;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.tamic.novate.BaseSubscriber;
import com.tamic.novate.Novate;
import com.tamic.novate.Throwable;
import com.trade.app.WelcomeActivity;
import com.trade.login.di.DaggerLoginPresenterComponent;
import com.trade.login.di.LoginPresenterModule;
import com.trade.login.model.LoginBean;
import com.trade.login.model.LoginPwdService;
import com.trade.login.model.LoginResultBean;
import com.trade.login.util.LoginUtil;
import com.trade.login.view.LoginView;
import com.trade.main.ui.MainActivity;
import com.trade.util.PreferUtil;

import javax.inject.Inject;

/**
 * Created by Stephen Sun on 2017/7/11 0011.
 * Email:suncunx@qq.com
 */

public class LoginPresenterImpl extends BasePresenterImpl<LoginView> implements LoginPresenter {

    @Inject
    Novate novate;
    @Override
    protected void initializeInjector(ApplicationComponent applicationComponent) {
        DaggerLoginPresenterComponent.builder()
                .applicationComponent(applicationComponent)
                .loginPresenterModule(new LoginPresenterModule())
                .build()
                .inject(this);
    }

    private void loginSuccess() { // 请求服务器登录
        ToastUtils.showShort("登录成功");
        PreferUtil.getInstance().setHasLogin();
        //        restartApplication();
        if (getView() != null)
            getView().finishActivity();
        Intent intent = MainActivity.getCallingIntent(context);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    private void restartApplication() {
        if (getView() != null) {
            getView().finishActivity();
        }
        Intent mStartActivity = new Intent(context, WelcomeActivity.class);
        int mPendingIntentId = 123456;
        PendingIntent mPendingIntent = PendingIntent.getActivity(context, mPendingIntentId, mStartActivity, PendingIntent.FLAG_CANCEL_CURRENT);
        AlarmManager mgr = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        mgr.set(AlarmManager.RTC, System.currentTimeMillis(), mPendingIntent);
        System.exit(0);
    }

    @Override
    public void onLogin(LoginBean loginBean) {
        String phone = loginBean.getPhone();
        String verify = loginBean.getVerify();

        if (!LoginUtil.checkPhone(phone)) {
            ToastUtils.showShort("手机号码不正确，请重新输入");
            return;
        }
        if (StringUtils.isEmpty(verify)) {
            ToastUtils.showShort("请输入验证码");
        } else {
            getView().submitCode();
        }

    }

    @Override
    public void requestLogin(final LoginBean loginBean) {

        LoginPwdService service = novate.create(LoginPwdService.class);
        novate.call(service.login(loginBean.getPhone()), new BaseSubscriber<LoginResultBean>(context) {
            @Override
            public void onNext(LoginResultBean loginResultBean) {
                Log.e("LoginBlack", "onNext: result = " + loginResultBean.getResult());
                if (loginResultBean.getCode() == 200) {
                    BasePreferUtil.getInstance().setUserId(loginResultBean.getResult());
                    PreferUtil.getInstance().setUserInfo(loginResultBean.getResult(), loginBean.getPhone(), "已登录");
                    loginSuccess();
                } else {
                    ToastUtils.showShort(loginResultBean.getMsg());
                }
            }

            @Override
            public void onError(Throwable e) {
                ToastUtils.showShort(e.getMessage());
            }
        });
    }

}

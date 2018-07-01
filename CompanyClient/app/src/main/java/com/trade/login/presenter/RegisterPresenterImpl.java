package com.trade.login.presenter;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
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
import com.trade.login.model.RegisterService;
import com.trade.login.util.LoginUtil;
import com.trade.login.view.RegisterView;
import com.trade.main.ui.MainActivity;
import com.trade.model.SimpleResultBean;
import com.trade.util.HttpCode;
import com.trade.util.PreferUtil;

import javax.inject.Inject;

import static com.tamic.novate.Novate.TAG;

/**
 * Created by Stephen Sun on 2017/7/11 0011.
 * Email:suncunx@qq.com
 */

public class RegisterPresenterImpl extends BasePresenterImpl<RegisterView> implements RegisterPresenter {

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

    public void loginSuccess() { // 请求服务器登录
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
        String password = loginBean.getPassword();
        String verify = loginBean.getVerify();

        if (!LoginUtil.checkPhone(phone)) {
            ToastUtils.showShort("手机号码不正确，请重新输入");
            return;
        }
        if (StringUtils.isEmpty(password)) {
            ToastUtils.showShort("请输入密码");
            return;
        }
        if (StringUtils.isEmpty(verify)) {
            ToastUtils.showShort("请输入验证码");
        } else {
            getView().showProgressDialog("注册中...");
            getView().submitCode();
        }
    }

    @Override
    public void requestRegister(final LoginBean loginBean) {
        //        Map<String, String> map = new HashMap<>();
        //        map.put(LoginConstant.KEY_ACCOUNT, loginBean.getPhone());
        //        map.put(LoginConstant.KEY_PASSWORD, loginBean.getPassword());

        RegisterService service = novate.create(RegisterService.class);
        novate.call(service.register(loginBean.getPhone(), loginBean.getPassword()), new BaseSubscriber<SimpleResultBean>() {
            @Override
            public void onNext(final SimpleResultBean simpleResultBean) {
                getView().dismissProgressDialog();
                Log.d(TAG, "onNext: code = " + simpleResultBean.getCode());
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        ToastUtils.showShort(simpleResultBean.getMsg());
                    }
                });

                if (simpleResultBean.getCode().equals(HttpCode.OK)) {
                    BasePreferUtil.getInstance().setUserId(simpleResultBean.getResult());
                    PreferUtil.getInstance().setUserInfo(simpleResultBean.getResult(), loginBean.getPhone(), "已登录");
                    loginSuccess();
                }
            }

            @Override
            public void onError(Throwable e) {
                getView().dismissProgressDialog();
                ToastUtils.showShort(e.getMessage());
            }
        });
    }
}

package com.trade.user.login.black.mvp;

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
import com.trade.main.MainActivity;
import com.trade.user.login.black.di.DaggerLoginPwdPresenterComponent;
import com.trade.user.login.black.di.LoginPwdPresenterModule;
import com.trade.user.login.black.model.LoginPwdService;
import com.trade.user.login.model.LoginBean;
import com.trade.user.login.model.LoginResultBean;
import com.trade.user.login.util.LoginUtil;
import com.trade.util.PreferUtil;

import javax.inject.Inject;

/**
 * Created by Stephen Sun on 2017/7/11 0011.
 * Email:suncunx@qq.com
 */

public class LoginBlackPresenterImpl extends BasePresenterImpl<LoginBlackView> implements LoginBlackPresenter {

    @Inject
    Novate novate;

    @Override
    protected void initializeInjector(ApplicationComponent applicationComponent) {
        // 可有可无，需要网络操作的时候一般需要
        DaggerLoginPwdPresenterComponent.builder()
                .applicationComponent(applicationComponent)
                .loginPwdPresenterModule(new LoginPwdPresenterModule())
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

//    private void restartApplication() {
//        if (getView() != null) {
//            getView().finishActivity();
//        }
//        Intent mStartActivity = new Intent(context, WelcomeActivity.class);
//        int mPendingIntentId = 123456;
//        PendingIntent mPendingIntent = PendingIntent.getActivity(context, mPendingIntentId, mStartActivity, PendingIntent.FLAG_CANCEL_CURRENT);
//        AlarmManager mgr = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
//        mgr.set(AlarmManager.RTC, System.currentTimeMillis(), mPendingIntent);
//        System.exit(0);
//    }

    @Override
    public void requestLogin(final LoginBean loginBean) {
        //        Map<String, String> map = new HashMap<>();
        //        map.put(LoginConstant.KEY_ACCOUNT, loginBean.getPhone());
        //        map.put(LoginConstant.KEY_PASSWORD, loginBean.getPassword());
        //        map.put(LoginConstant.KEY_TYPE, LoginConstant.TYPE_PASSWORD);

        LoginPwdService service = novate.create(LoginPwdService.class);
        novate.call(service.login(loginBean.getPhone(), loginBean.getPassword()), new BaseSubscriber<LoginResultBean>(context) {
            @Override
            public void onNext(LoginResultBean loginResultBean) {
                Log.d("LoginBlack", "onNext: result = " + loginResultBean.getResult());
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

    @Override
    public void onLogin(LoginBean loginBean) {
        String phone = loginBean.getPhone();
        String password = loginBean.getPassword();

        if (!LoginUtil.checkPhone(phone)) {
            ToastUtils.showShort("手机号码不正确，请重新输入");
            return;
        }
        if (StringUtils.isEmpty(password)) {
            ToastUtils.showShort("请输入密码");
        } else {
            getView().requestLogin();
        }
    }

}

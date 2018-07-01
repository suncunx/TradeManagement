package com.trade.login.di;

import com.architecture.di.PerActivity;
import com.trade.login.presenter.LoginPwdPresenter;
import com.trade.login.presenter.LoginPwdPresenterImpl;
import com.trade.login.presenter.LoginPresenter;
import com.trade.login.presenter.LoginPresenterImpl;
import com.trade.login.presenter.RegisterPresenter;
import com.trade.login.presenter.RegisterPresenterImpl;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Stephen Sun on 2017/7/11 0011.
 * Email:suncunx@qq.com
 */

@Module
public class LoginModule {
    @PerActivity
    @Provides
    LoginPresenter provideLoginPresenter() {
        return new LoginPresenterImpl();
    }

    @PerActivity
    @Provides
    LoginPwdPresenter provideBlackActivityPresenter() {
        return new LoginPwdPresenterImpl();
    }

    @PerActivity
    @Provides
    RegisterPresenter provideRegisterPresenter() {
        return new RegisterPresenterImpl();
    }
}

package com.trade.user.login.di;

import com.architecture.di.PerActivity;
import com.trade.user.login.mvp.LoginPresenter;
import com.trade.user.login.mvp.LoginPresenterImpl;

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
}

package com.trade.user.login.black.di;

import com.architecture.di.PerActivity;
import com.trade.user.login.black.mvp.LoginBlackPresenter;
import com.trade.user.login.black.mvp.LoginBlackPresenterImpl;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Stephen Sun on 2017/7/11 0011.
 * Email:suncunx@qq.com
 */

@Module
public class LoginBlackModule {
    @PerActivity
    @Provides
    LoginBlackPresenter provideBlackActivityPresenter() {
        return new LoginBlackPresenterImpl();
    }
}

package com.trade.user.register.di;

import com.architecture.di.PerActivity;
import com.trade.user.register.mvp.RegisterPresenter;
import com.trade.user.register.mvp.RegisterPresenterImpl;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Stephen Sun on 2017/7/11 0011.
 * Email:suncunx@qq.com
 */

@Module
public class RegisterModule {
    @PerActivity
    @Provides
    RegisterPresenter provideRegisterPresenter() {
        return new RegisterPresenterImpl();
    }
}

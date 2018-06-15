package com.trade.user.login.black.di;

import com.architecture.di.PerActivity;
import com.tamic.novate.Novate;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Stephen Sun on 2017/7/11 0011.
 * Email:suncunx@qq.com
 */

@Module
public class LoginPwdPresenterModule {
    @PerActivity
    @Provides
    Novate provideNovate(Novate.Builder builder) {
        return builder.build();
    }
}

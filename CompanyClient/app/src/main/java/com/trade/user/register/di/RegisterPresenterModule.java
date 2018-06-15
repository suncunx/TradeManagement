package com.trade.user.register.di;

import com.architecture.di.PerActivity;
import com.tamic.novate.Novate;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Stephen Sun on 2017/8/15 0015.
 * Email:suncunx@qq.com
 */
@Module
public class RegisterPresenterModule {
    @PerActivity
    @Provides
    Novate provideNovate(Novate.Builder builder) {
        return builder.build();
    }
}

package com.trade.user.di;

import com.architecture.di.PerFragment;
import com.trade.user.mvp.UserPresenter;
import com.trade.user.mvp.UserPresenterImpl;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Stephen Sun on 2017/7/5 0005.
 * Email:suncunx@qq.com
 */

@PerFragment
@Module
public class UserModule {

    @PerFragment
    @Provides
    UserPresenter provideUserPresenter() {
        return new UserPresenterImpl();
    }
}

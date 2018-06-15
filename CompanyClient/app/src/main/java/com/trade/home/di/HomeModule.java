package com.trade.home.di;

import com.architecture.di.PerFragment;
import com.trade.home.mvp.HomePresenter;
import com.trade.home.mvp.HomePresenterImpl;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Stephen Sun on 2017/7/5 0005.
 * Email:suncunx@qq.com
 */

@PerFragment
@Module
public class HomeModule {

    @PerFragment
    @Provides
    HomePresenter provideHomePresenter() {
        return new HomePresenterImpl();
    }
}

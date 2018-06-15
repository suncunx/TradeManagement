package com.trade.other.di;

import com.architecture.di.PerFragment;
import com.trade.other.mvp.OtherPresenter;
import com.trade.other.mvp.OtherPresenterImpl;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Stephen Sun on 2017/7/5 0005.
 * Email:suncunx@qq.com
 */

@PerFragment
@Module
public class OtherModule {

    @PerFragment
    @Provides
    OtherPresenter provideOtherPresenter() {
        return new OtherPresenterImpl();
    }
}

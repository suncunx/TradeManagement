package com.trade.other.focus.di;

import com.architecture.di.PerActivity;
import com.trade.other.focus.mvp.FocusPresenter;
import com.trade.other.focus.mvp.FocusPresenterImpl;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Stephen Sun on 2017/7/5 0005.
 * Email:suncunx@qq.com
 */

@PerActivity
@Module
public class FocusModule {

    @PerActivity
    @Provides
    FocusPresenter provideFocusPresenter() {
        return new FocusPresenterImpl();
    }
}

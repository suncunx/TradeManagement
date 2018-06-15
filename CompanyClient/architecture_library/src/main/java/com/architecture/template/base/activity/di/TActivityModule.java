package com.architecture.template.base.activity.di;

import com.architecture.di.PerActivity;
import com.architecture.template.base.activity.mvp.TActivityPresenter;
import com.architecture.template.base.activity.mvp.TActivityPresenterImpl;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Stephen Sun on 2017/7/11 0011.
 * Email:suncunx@qq.com
 */

@Module
public class TActivityModule {
    @PerActivity
    @Provides
    TActivityPresenter provideTActivityPresenter() {
        return new TActivityPresenterImpl();
    }
}

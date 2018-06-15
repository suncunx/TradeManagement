package com.architecture.template.rv.activity.di;

import com.architecture.di.PerActivity;
import com.architecture.template.rv.activity.mvp.TRvActivityPresenter;
import com.architecture.template.rv.activity.mvp.TRvActivityPresenterImpl;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Stephen Sun on 2017/7/11 0011.
 * Email:suncunx@qq.com
 */

@Module
public class TRvActivityModule {
    @PerActivity
    @Provides
    TRvActivityPresenter provideTRvActivityPresenter() {
        return new TRvActivityPresenterImpl();
    }
}

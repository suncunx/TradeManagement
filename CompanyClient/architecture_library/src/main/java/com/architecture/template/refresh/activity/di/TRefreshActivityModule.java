package com.architecture.template.refresh.activity.di;

import com.architecture.di.PerActivity;
import com.architecture.template.refresh.activity.mvp.TRefreshActivityPresenter;
import com.architecture.template.refresh.activity.mvp.TRefreshActivityPresenterImpl;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Stephen Sun on 2017/7/11 0011.
 * Email:suncunx@qq.com
 */

@Module
public class TRefreshActivityModule {
    @PerActivity
    @Provides
    TRefreshActivityPresenter provideTLceActivityPresenter() {
        return new TRefreshActivityPresenterImpl();
    }
}

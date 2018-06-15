package com.architecture.template.refresh.toolbar.di;

import com.architecture.di.PerActivity;
import com.architecture.template.refresh.toolbar.mvp.TRefreshToolbarActivityPresenter;
import com.architecture.template.refresh.toolbar.mvp.TRefreshToolbarActivityPresenterImpl;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Stephen Sun on 2017/7/11 0011.
 * Email:suncunx@qq.com
 */

@Module
public class TRefreshToolbarActivityModule {
    @PerActivity
    @Provides
    TRefreshToolbarActivityPresenter provideTRefreshToobarActivityPresenter() {
        return new TRefreshToolbarActivityPresenterImpl();
    }
}

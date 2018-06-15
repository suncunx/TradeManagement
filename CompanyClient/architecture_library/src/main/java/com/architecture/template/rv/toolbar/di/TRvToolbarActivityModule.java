package com.architecture.template.rv.toolbar.di;

import com.architecture.di.PerActivity;
import com.architecture.template.rv.toolbar.mvp.TRvToolbarActivityPresenter;
import com.architecture.template.rv.toolbar.mvp.TRvToolbarActivityPresenterImpl;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Stephen Sun on 2017/7/11 0011.
 * Email:suncunx@qq.com
 */

@Module
public class TRvToolbarActivityModule {
    @PerActivity
    @Provides
    TRvToolbarActivityPresenter provideTRefreshToobarActivityPresenter() {
        return new TRvToolbarActivityPresenterImpl();
    }
}

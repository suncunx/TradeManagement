package com.architecture.template.lce.toolbar.di;

import com.architecture.di.PerActivity;
import com.architecture.template.lce.toolbar.mvp.TLceToolbarActivityPresenter;
import com.architecture.template.lce.toolbar.mvp.TLceToolbarActivityPresenterImpl;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Stephen Sun on 2017/7/11 0011.
 * Email:suncunx@qq.com
 */

@Module
public class TLceToolbarActivityModule {
    @PerActivity
    @Provides
    TLceToolbarActivityPresenter provideTLceToobarActivityPresenter() {
        return new TLceToolbarActivityPresenterImpl();
    }
}

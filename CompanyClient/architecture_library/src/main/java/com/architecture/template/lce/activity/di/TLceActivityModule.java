package com.architecture.template.lce.activity.di;

import com.architecture.di.PerActivity;
import com.architecture.template.lce.activity.mvp.TLceActivityPresenter;
import com.architecture.template.lce.activity.mvp.TLceActivityPresenterImpl;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Stephen Sun on 2017/7/11 0011.
 * Email:suncunx@qq.com
 */

@Module
public class TLceActivityModule {
    @PerActivity
    @Provides
    TLceActivityPresenter provideTLceActivityPresenter() {
        return new TLceActivityPresenterImpl();
    }
}

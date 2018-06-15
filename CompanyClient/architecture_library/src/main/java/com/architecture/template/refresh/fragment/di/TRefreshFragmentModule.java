package com.architecture.template.refresh.fragment.di;

import com.architecture.di.PerFragment;
import com.architecture.template.refresh.fragment.mvp.TRefreshFragmentPresenter;
import com.architecture.template.refresh.fragment.mvp.TRefreshFragmentPresenterImpl;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Stephen Sun on 2017/7/11 0011.
 * Email:suncunx@qq.com
 */

@Module
public class TRefreshFragmentModule {
    @PerFragment
    @Provides
    TRefreshFragmentPresenter provideTRefreshFragmentPresenter() {
        return new TRefreshFragmentPresenterImpl();
    }
}

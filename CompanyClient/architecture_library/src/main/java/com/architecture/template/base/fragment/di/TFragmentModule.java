package com.architecture.template.base.fragment.di;

import com.architecture.di.PerFragment;
import com.architecture.template.base.fragment.mvp.TFragmentPresenter;
import com.architecture.template.base.fragment.mvp.TFragmentPresenterImpl;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Stephen Sun on 2017/7/11 0011.
 * Email:suncunx@qq.com
 */

@Module
public class TFragmentModule {
    @PerFragment
    @Provides
    TFragmentPresenter provideTFragmentPresenter() {
        return new TFragmentPresenterImpl();
    }
}

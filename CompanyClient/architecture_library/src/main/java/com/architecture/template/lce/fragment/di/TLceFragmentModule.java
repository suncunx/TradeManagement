package com.architecture.template.lce.fragment.di;

import com.architecture.di.PerFragment;
import com.architecture.template.lce.fragment.mvp.TLceFragmentPresenter;
import com.architecture.template.lce.fragment.mvp.TLceFragmentPresenterImpl;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Stephen Sun on 2017/7/11 0011.
 * Email:suncunx@qq.com
 */

@Module
public class TLceFragmentModule {
    @PerFragment
    @Provides
    TLceFragmentPresenter provideTLceFragmentPresenter() {
        return new TLceFragmentPresenterImpl();
    }
}

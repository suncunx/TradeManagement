package com.architecture.template.rv.fragment.di;

import com.architecture.di.PerFragment;
import com.architecture.template.rv.fragment.mvp.TRvFragmentPresenter;
import com.architecture.template.rv.fragment.mvp.TRvFragmentPresenterImpl;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Stephen Sun on 2017/7/11 0011.
 * Email:suncunx@qq.com
 */

@Module
public class TRvFragmentModule {
    @PerFragment
    @Provides
    TRvFragmentPresenter provideTRvFragmentPresenter() {
        return new TRvFragmentPresenterImpl();
    }

}

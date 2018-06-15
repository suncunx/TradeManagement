package com.trade.other.focus.news.di;

import com.architecture.di.PerFragment;
import com.trade.other.focus.news.mvp.NewsFragmentPresenter;
import com.trade.other.focus.news.mvp.NewsFragmentPresenterImpl;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Stephen Sun on 2017/7/5 0005.
 * Email:suncunx@qq.com
 */

@PerFragment
@Module
public class NewsFragmentModule {

    @PerFragment
    @Provides
    NewsFragmentPresenter provideNewsFragmentPresenter() {
        return new NewsFragmentPresenterImpl();
    }

}

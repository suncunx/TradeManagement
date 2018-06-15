package com.trade.other.focus.news.di;

import com.architecture.di.PerFragment;
import com.tamic.novate.Novate;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Stephen Sun on 2017/7/11 0011.
 * Email:suncunx@qq.com
 */

@Module
public class NewsFragmentPresenterModule {
    @PerFragment
    @Provides
    Novate provideNovate(Novate.Builder builder) {
        return builder.build();
    }
}

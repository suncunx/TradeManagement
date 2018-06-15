package com.trade.other.focus.news.di;

import com.architecture.di.PerFragment;
import com.architecture.di.modules.ActivityModule;
import com.trade.other.focus.news.NewsFragment;

import dagger.Component;

/**
 * Created by Stephen Sun on 2017/7/5 0005.
 * Email:suncunx@qq.com
 */
@PerFragment
@Component(modules = {ActivityModule.class, NewsFragmentModule.class})
public interface NewsFragmentComponent {

    void inject(NewsFragment homeFragment);
}

package com.trade.other.focus.news.di;

import com.architecture.di.PerFragment;
import com.architecture.di.components.ApplicationComponent;
import com.trade.other.focus.news.mvp.NewsFragmentPresenterImpl;

import dagger.Component;

/**
 * Created by Stephen Sun on 2017/7/11 0011.
 * Email:suncunx@qq.com
 */

@PerFragment
@Component(dependencies = ApplicationComponent.class, modules = {NewsFragmentPresenterModule.class})
public interface NewsFragmentPresenterComponent {
    void inject(NewsFragmentPresenterImpl presenter);
}

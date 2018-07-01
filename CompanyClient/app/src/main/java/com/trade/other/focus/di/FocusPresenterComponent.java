package com.trade.other.focus.di;

import com.architecture.di.PerFragment;
import com.architecture.di.components.ApplicationComponent;
import com.trade.other.focus.presenter.NewsFragmentPresenterImpl;

import dagger.Component;

/**
 * Created by Stephen Sun on 2017/7/11 0011.
 * Email:suncunx@qq.com
 */

@PerFragment
@Component(dependencies = ApplicationComponent.class, modules = {FocusPresenterModule.class})
public interface FocusPresenterComponent {
    void inject(NewsFragmentPresenterImpl presenter);
}

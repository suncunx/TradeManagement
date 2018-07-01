package com.trade.other.focus.di;

import com.architecture.di.PerActivity;
import com.trade.other.focus.ui.FocusActivity;
import com.trade.other.focus.ui.NewsFragment;

import dagger.Component;

/**
 * Created by Stephen Sun on 2017/7/5 0005.
 * Email:suncunx@qq.com
 */
@PerActivity
@Component(modules = FocusModule.class)
public interface FocusComponent {

    void inject(FocusActivity activity);

    void inject(NewsFragment newsFragment);
}

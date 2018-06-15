package com.trade.home.di;

import com.architecture.di.PerFragment;
import com.trade.home.HomeFragment;

import dagger.Component;

/**
 * Created by Stephen Sun on 2017/7/5 0005.
 * Email:suncunx@qq.com
 */
@PerFragment
@Component(modules = HomeModule.class)
public interface HomeComponent {

    void inject(HomeFragment homeFragment);
}

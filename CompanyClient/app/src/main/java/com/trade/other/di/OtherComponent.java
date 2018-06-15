package com.trade.other.di;

import com.architecture.di.PerFragment;
import com.trade.other.OtherFragment;

import dagger.Component;
/**
 * Created by Stephen Sun on 2017/7/5 0005.
 * Email:suncunx@qq.com
 */
@PerFragment
@Component(modules = OtherModule.class)
public interface OtherComponent {

    void inject(OtherFragment otherFragment);
}

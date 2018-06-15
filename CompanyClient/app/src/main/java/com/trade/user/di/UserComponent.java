package com.trade.user.di;

import com.architecture.di.PerFragment;
import com.trade.user.UserFragment;

import dagger.Component;

/**
 * Created by Stephen Sun on 2017/7/5 0005.
 * Email:suncunx@qq.com
 */
@PerFragment
@Component(modules = UserModule.class)
public interface UserComponent {

    void inject(UserFragment userFragment);
}

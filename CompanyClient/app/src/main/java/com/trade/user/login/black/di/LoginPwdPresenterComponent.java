package com.trade.user.login.black.di;

import com.architecture.di.PerActivity;
import com.architecture.di.components.ApplicationComponent;
import com.trade.user.login.black.mvp.LoginBlackPresenterImpl;

import dagger.Component;

/**
 * Created by Stephen Sun on 2017/7/11 0011.
 * Email:suncunx@qq.com
 */

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {LoginPwdPresenterModule.class})
public interface LoginPwdPresenterComponent {
    void inject(LoginBlackPresenterImpl presenter);
}

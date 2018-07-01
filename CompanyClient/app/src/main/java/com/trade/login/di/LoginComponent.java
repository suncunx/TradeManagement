package com.trade.login.di;

import com.architecture.di.PerActivity;
import com.architecture.di.components.ApplicationComponent;
import com.trade.login.ui.LoginActivity;
import com.trade.login.ui.LoginPwdActivity;
import com.trade.login.ui.RegisterActivity;

import dagger.Component;

/**
 * Created by Stephen Sun on 2017/7/11 0011.
 * Email:suncunx@qq.com
 */

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {LoginModule.class})
public interface LoginComponent {
    void inject(LoginActivity activity);

    void inject(LoginPwdActivity activity);

    void inject(RegisterActivity activity);
}

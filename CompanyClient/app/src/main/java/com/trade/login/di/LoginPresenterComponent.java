package com.trade.login.di;

import com.architecture.di.PerActivity;
import com.architecture.di.components.ApplicationComponent;
import com.trade.login.presenter.LoginPwdPresenterImpl;
import com.trade.login.presenter.LoginPresenterImpl;
import com.trade.login.presenter.RegisterPresenterImpl;

import dagger.Component;

/**
 * Created by Stephen Sun on 2017/7/11 0011.
 * Email:suncunx@qq.com
 */

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {LoginPresenterModule.class})
public interface LoginPresenterComponent {
    void inject(LoginPresenterImpl presenter);

    void inject(LoginPwdPresenterImpl presenter);

    void inject(RegisterPresenterImpl presenter);
}

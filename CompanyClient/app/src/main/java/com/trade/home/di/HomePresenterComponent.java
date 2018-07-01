package com.trade.home.di;

import com.architecture.di.PerFragment;
import com.architecture.di.components.ApplicationComponent;
import com.trade.home.presenter.InBillSavePresenterImpl;
import com.trade.home.presenter.OutBillPresenterImpl;
import com.trade.home.presenter.OutBillSavePresenterImpl;
import com.trade.home.presenter.HomePresenterImpl;

import dagger.Component;

/**
 * Created by Stephen Sun on 2017/8/15 0015.
 * Email:suncunx@qq.com
 */
@PerFragment
@Component(dependencies = {ApplicationComponent.class}, modules = {HomePresenterModule.class})
public interface HomePresenterComponent {
    void inject(HomePresenterImpl presenter);

    void inject(InBillSavePresenterImpl presenter);

    void inject(OutBillPresenterImpl presenter);

    void inject(OutBillSavePresenterImpl presenter);
}

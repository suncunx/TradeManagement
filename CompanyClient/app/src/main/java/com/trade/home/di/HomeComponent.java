package com.trade.home.di;

import com.architecture.di.PerFragment;
import com.architecture.di.components.ApplicationComponent;
import com.trade.home.ui.HomeFragment;
import com.trade.home.ui.InBillActivity;
import com.trade.home.ui.InBillSaveActivity;
import com.trade.home.ui.OutBillActivity;
import com.trade.home.ui.OutBillSaveActivity;

import dagger.Component;

/**
 * Created by Stephen Sun on 2017/7/5 0005.
 * Email:suncunx@qq.com
 */
@PerFragment
@Component(dependencies = ApplicationComponent.class, modules = {HomeModule.class})
public interface HomeComponent {

    void inject(HomeFragment homeFragment);

    void inject(InBillActivity activity);

    void inject(InBillSaveActivity activity);

    void inject(OutBillActivity activity);

    void inject(OutBillSaveActivity activity);
}

package com.trade.other.di;

import com.architecture.di.PerFragment;
import com.architecture.di.components.ApplicationComponent;
import com.trade.other.ui.CustomerActivity;
import com.trade.other.ui.DeliverActivity;
import com.trade.other.ui.FinanceActivity;
import com.trade.other.ui.OtherFragment;
import com.trade.other.ui.SupplierActivity;

import dagger.Component;
/**
 * Created by Stephen Sun on 2017/7/5 0005.
 * Email:suncunx@qq.com
 */
@PerFragment
@Component(dependencies = ApplicationComponent.class, modules = {OtherModule.class})
public interface OtherComponent {

    void inject(OtherFragment otherFragment);

    void inject(CustomerActivity activity);

    void inject(SupplierActivity activity);

    void inject(DeliverActivity activity);

    void inject(FinanceActivity activity);
}

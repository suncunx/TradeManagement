package com.architecture.template.base.toolbar.mvp;

import com.architecture.di.components.ApplicationComponent;
import com.architecture.mvp.BasePresenterImpl;
import com.architecture.template.base.activity.mvp.TActivityPresenter;

/**
 * Created by Stephen Sun on 2017/7/11 0011.
 * Email:suncunx@qq.com
 */

public class TToolbarPresenterImpl extends BasePresenterImpl<com.architecture.template.base.activity.mvp.TActivityView> implements TActivityPresenter {

    @Override
    protected void initializeInjector(ApplicationComponent applicationComponent) {
        // 可有可无，需要网络操作的时候一般需要
    }
}

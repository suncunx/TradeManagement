package com.architecture.template.base.fragment.mvp;

import com.architecture.di.components.ApplicationComponent;
import com.architecture.mvp.BasePresenterImpl;

/**
 * Created by Stephen Sun on 2017/7/11 0011.
 * Email:suncunx@qq.com
 */

public class TFragmentPresenterImpl extends BasePresenterImpl<TFragmentView> implements TFragmentPresenter {

    @Override
    protected void initializeInjector(ApplicationComponent applicationComponent) {
        // 可有可无，需要网络操作的时候一般需要
    }
}

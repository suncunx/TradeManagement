package com.architecture.template.lce.fragment.mvp;

import com.architecture.di.components.ApplicationComponent;
import com.architecture.mvp.lce.BaseLcePresenterImpl;

/**
 * Created by Stephen Sun on 2017/7/11 0011.
 * Email:suncunx@qq.com
 */

public class TLceFragmentPresenterImpl extends BaseLcePresenterImpl<TLceFragmentView> implements TLceFragmentPresenter {

    @Override
    protected void initializeInjector(ApplicationComponent applicationComponent) {
        // 可有可无，需要网络操作的时候一般需要
    }

    @Override
    public void loadData(boolean pullToRefresh) {

    }
}

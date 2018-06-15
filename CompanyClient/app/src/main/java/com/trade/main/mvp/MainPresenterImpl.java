package com.trade.main.mvp;

import com.architecture.di.components.ApplicationComponent;
import com.architecture.mvp.BasePresenterImpl;

/**
 * Created by Stephen Sun on 2017/7/4 0004.
 * Email:suncunx@qq.com
 */

public class MainPresenterImpl extends BasePresenterImpl<MainView> implements MainPresenter {
    @Override
    protected void initializeInjector(ApplicationComponent applicationComponent) {
        
    }

    @Override
    public void onTitleChange(boolean isOutBill) {
        getView().onTitleChange(isOutBill);
    }
}
package com.trade.main.di;

import android.app.Activity;

import com.architecture.di.PerActivity;
import com.trade.main.listener.MainListeners;
import com.trade.main.mvp.MainPresenter;
import com.trade.main.mvp.MainPresenterImpl;
import com.trade.model.ToolbarBean;
import com.trade.util.Constants;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Stephen Sun on 2017/7/4 0004.
 * Email:suncunx@qq.com
 */
@Module
public class MainModule {

    @PerActivity
    @Provides
    MainPresenter provideMainPresenter() {
        return new MainPresenterImpl();
    }

    @PerActivity
    @Provides
    ToolbarBean provideToolbarBean(Activity context, MainPresenter presenter) {
        ToolbarBean toolbarBean = new ToolbarBean(Constants.APP_NAME, Constants.FILTER, true,
                MainListeners.getLeftClickListener(context), MainListeners.getTitleClickListener(context, presenter), MainListeners.getRightClickListener(context));
//        LogUtils.d(PreferUtil.getInstance().getCity());
        return toolbarBean;
    }
}

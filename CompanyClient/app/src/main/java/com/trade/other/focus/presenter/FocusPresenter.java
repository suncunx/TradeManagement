package com.trade.other.focus.presenter;

import android.support.v4.app.FragmentManager;

import com.architecture.mvp.BasePresenter;
import com.trade.other.focus.view.FocusView;

/**
 * Created by Stephen Sun on 2017/7/5 0005.
 * Email:suncunx@qq.com
 */

public interface FocusPresenter extends BasePresenter<FocusView> {

    void loadTitles(FragmentManager fragmentManager);
}

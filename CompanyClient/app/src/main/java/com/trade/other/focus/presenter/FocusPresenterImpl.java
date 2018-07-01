package com.trade.other.focus.presenter;

import android.support.v4.app.FragmentManager;

import com.architecture.di.components.ApplicationComponent;
import com.architecture.mvp.BasePresenterImpl;
import com.trade.other.focus.model.FocusBean;
import com.trade.other.focus.view.FocusView;
import com.trade.other.focus.ui.NewsFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stephen Sun on 2017/7/5 0005.
 * Email:suncunx@qq.com
 */

public class FocusPresenterImpl extends BasePresenterImpl<FocusView> implements FocusPresenter {

    private String[] titles = {"商业", "娱乐", "大众", "健康", "科学", "体育", "技术"};
    private String[] titlesEn = {"business", "entertainment", "general", "health", "science", "sports", "technology"};
    @Override
    public void loadTitles(FragmentManager fragmentManager) {

        final List<FocusBean> beanList = new ArrayList<>();
        for (int i = 0; i < titles.length; i++) {
            FocusBean fBean = new FocusBean(titlesEn[i], titles[i], NewsFragment.newInstance(titlesEn[i]));
            beanList.add(fBean);
        }
        getView().setData(beanList);
    }

    @Override
    protected void initializeInjector(ApplicationComponent applicationComponent) {
    }
}

package com.trade.other.focus.ui;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;

import com.architecture.mvp.BaseToolbarActivity;
import com.trade.BR;
import com.trade.R;
import com.trade.other.focus.adapter.FocusPagerAdapter;
import com.trade.other.focus.di.DaggerFocusComponent;
import com.trade.other.focus.di.FocusModule;
import com.trade.other.focus.model.FocusBean;
import com.trade.other.focus.presenter.FocusPresenter;
import com.trade.other.focus.view.FocusView;

import java.util.List;


public class FocusActivity extends BaseToolbarActivity<FocusView, FocusPresenter> implements FocusView {

    private FocusPagerAdapter pagerAdapter;

    @Override
    public void init() {
        presenter.loadTitles(getSupportFragmentManager());
    }

    @Override
    protected String createTitle() {
        return "看点";
    }

    @Override
    public ViewDataBinding createViewDataBinding() {
        return DataBindingUtil.setContentView(this, R.layout.activity_focus);
    }

    @Override
    public void initializeInjector() {
        DaggerFocusComponent.builder()
                .focusModule(new FocusModule())
                .build()
                .inject(this);
    }

    @Override
    public void setData(List<FocusBean> data) {
        pagerAdapter = new FocusPagerAdapter(getSupportFragmentManager(), data);
//        pagerAdapter.setData(data);
        viewDataBinding.setVariable(BR.fragmentPagerAdapter, pagerAdapter);
        viewDataBinding.setVariable(BR.viewPager, viewDataBinding.getRoot().findViewById(R.id.homeViewPager));
    }

}

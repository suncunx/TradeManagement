package com.architecture.template.refresh.fragment;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.architecture.R;
import com.architecture.mvp.refresh.BaseRefreshFragment;
import com.architecture.template.refresh.fragment.model.TRefreshFragmentBean;
import com.architecture.template.refresh.fragment.mvp.TRefreshFragmentPresenter;
import com.architecture.template.refresh.fragment.mvp.TRefreshFragmentView;

/**
 * Created by Stephen Sun on 2017/7/11 0011.
 * Email:suncunx@qq.com
 */

public class TRefreshFragment extends BaseRefreshFragment<SwipeRefreshLayout, TRefreshFragmentBean, TRefreshFragmentView, TRefreshFragmentPresenter> implements TRefreshFragmentView {
    @Override
    public ViewDataBinding createViewDataBinding(LayoutInflater inflater, ViewGroup container) {
        //TODO 修改布局文件
        return DataBindingUtil.inflate(inflater, R.layout.template, container, false);
    }

    @Override
    public void initializeInjector() {
        //TODO 初始化Component
    }

    @Override
    protected String getErrorMessage(Throwable e, boolean pullToRefresh) {
        return e.getMessage();
    }

    @Override
    public void setData(TRefreshFragmentBean data) {
        //TODO 将数据通过DataBinding绑定到View中
    }

    @Override
    public void loadData(boolean pullToRefresh) {
        presenter.loadData(pullToRefresh);
    }

    @Override
    public void onRefresh() {

    }
}

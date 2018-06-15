package com.architecture.template.lce.fragment;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.architecture.R;
import com.architecture.mvp.lce.BaseLceFragment;
import com.architecture.template.lce.fragment.model.TLceFragmentBean;
import com.architecture.template.lce.fragment.mvp.TLceFragmentPresenter;
import com.architecture.template.lce.fragment.mvp.TLceFragmentView;

/**
 * Created by Stephen Sun on 2017/7/11 0011.
 * Email:suncunx@qq.com
 */

public class TLceFragment extends BaseLceFragment<View, TLceFragmentBean, TLceFragmentView, TLceFragmentPresenter> implements TLceFragmentView {
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
    public void setData(TLceFragmentBean data) {
        //TODO 将数据通过DataBinding绑定到View中
    }

    @Override
    public void loadData(boolean pullToRefresh) {
        presenter.loadData(pullToRefresh);
    }
}

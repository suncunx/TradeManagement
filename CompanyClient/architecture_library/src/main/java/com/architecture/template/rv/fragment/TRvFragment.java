package com.architecture.template.rv.fragment;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.architecture.R;
import com.architecture.mvp.rv.BaseRvFragment;
import com.architecture.template.rv.fragment.model.TRvFragmentBean;
import com.architecture.template.rv.fragment.mvp.TRvFragmentPresenter;
import com.architecture.template.rv.fragment.mvp.TRvFragmentView;

/**
 * Created by Stephen Sun on 2017/7/11 0011.
 * Email:suncunx@qq.com
 */

public class TRvFragment extends BaseRvFragment<SwipeRefreshLayout, TRvFragmentBean, TRvFragmentView, TRvFragmentPresenter> implements TRvFragmentView {
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
    public void setData(TRvFragmentBean data) {
        //TODO 将数据通过DataBinding绑定到View中
    }

    @Override
    public void loadData(boolean pullToRefresh) {
        presenter.loadData(pullToRefresh);
    }

}

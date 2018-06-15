package com.architecture.template.base.fragment;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.architecture.R;
import com.architecture.mvp.BaseFragment;
import com.architecture.template.base.fragment.mvp.TFragmentPresenter;
import com.architecture.template.base.fragment.mvp.TFragmentView;

/**
 * Created by Stephen Sun on 2017/7/11 0011.
 * Email:suncunx@qq.com
 */

public class TFragment extends BaseFragment<TFragmentView, TFragmentPresenter> implements TFragmentView {
    @Override
    public ViewDataBinding createViewDataBinding(LayoutInflater inflater, ViewGroup container) {
        //TODO 修改布局文件
        return DataBindingUtil.inflate(inflater, R.layout.template, container, false);
    }

    @Override
    public void initializeInjector() {
        //TODO 初始化Component
    }
}

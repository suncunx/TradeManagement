package com.trade.other;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.apkfuns.logutils.LogUtils;
import com.architecture.model.AdapterBean;
import com.architecture.mvp.BaseFragment;
import com.trade.BR;
import com.trade.R;
import com.trade.other.di.DaggerOtherComponent;
import com.trade.other.mvp.OtherPresenter;
import com.trade.other.mvp.OtherView;

public class OtherFragment extends BaseFragment<OtherView, OtherPresenter> implements OtherView {

    public OtherFragment() {
        // Required empty public constructor
    }

    @Override
    public void onStart() {
        super.onStart();
        LogUtils.d("onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        LogUtils.d("onResume");
    }

    @Override
    public void init() {
        presenter.loadUser();
    }

    public static OtherFragment newInstance() {
        OtherFragment fragment = new OtherFragment();
        return fragment;
    }

    @Override
    public ViewDataBinding createViewDataBinding(LayoutInflater inflater, ViewGroup container) {
        return DataBindingUtil.inflate(inflater, R.layout.fragment_other, container, false);
    }

    @Override
    public void initializeInjector() {
        DaggerOtherComponent.create()
                .inject(this);
    }

    @Override
    public void setData(AdapterBean data) {
        LogUtils.d(data);
        viewDataBinding.setVariable(BR.adapterBean, data);
    }

    public Activity getUserActivity() {
        return getActivity();
    }

}

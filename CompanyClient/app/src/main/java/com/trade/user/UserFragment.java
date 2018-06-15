package com.trade.user;

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
import com.trade.user.di.DaggerUserComponent;
import com.trade.user.listener.UserListeners;
import com.trade.user.model.UserBean;
import com.trade.user.mvp.UserPresenter;
import com.trade.user.mvp.UserView;
import com.trade.util.PreferUtil;

public class UserFragment extends BaseFragment<UserView, UserPresenter> implements UserView {

    private UserBean userBean;

    public UserFragment() {
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

    public void refresh() {
        userBean = PreferUtil.getInstance().getUserInfo();
        viewDataBinding.setVariable(BR.userBean, userBean);
    }

    @Override
    public void init() {
        userBean = PreferUtil.getInstance().getUserInfo();
        presenter.loadUser();
    }

    public static UserFragment newInstance() {
        UserFragment fragment = new UserFragment();
        return fragment;
    }

    @Override
    public ViewDataBinding createViewDataBinding(LayoutInflater inflater, ViewGroup container) {
        return DataBindingUtil.inflate(inflater, R.layout.fragment_user, container, false);
    }

    @Override
    public void initializeInjector() {
        DaggerUserComponent.create()
                .inject(this);
    }

    @Override
    public void setData(AdapterBean data) {
        LogUtils.d(data);
        viewDataBinding.setVariable(BR.userBean, userBean);
        viewDataBinding.setVariable(BR.adapterBean, data);
        viewDataBinding.setVariable(BR.userListener, new UserListeners(getActivity()));
    }

    public Activity getUserActivity() {
        return getActivity();
    }

}

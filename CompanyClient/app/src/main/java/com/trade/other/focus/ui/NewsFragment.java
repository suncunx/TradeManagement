package com.trade.other.focus.ui;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.apkfuns.logutils.LogUtils;
import com.architecture.model.AdapterBean;
import com.architecture.mvp.rv.BaseRvFragment;
import com.trade.BR;
import com.trade.R;
import com.trade.other.focus.di.DaggerFocusComponent;
import com.trade.other.focus.di.FocusModule;
import com.trade.other.focus.presenter.NewsFragmentPresenter;
import com.trade.other.focus.view.NewsFragmentView;

import static android.content.ContentValues.TAG;
import static com.trade.util.ApiManager.CONNECT_TIME_OUT;

// 切换fragment的时候不会调用任何的生命周期方法，因为没有destroyItem
public class NewsFragment extends BaseRvFragment<SwipeRefreshLayout, AdapterBean, NewsFragmentView, NewsFragmentPresenter> implements NewsFragmentView {
    private static final String ARG_PARAM1 = "param1";

    private String category;

    public NewsFragment() {
        // Required empty public constructor
    }

    @Override
    protected String getErrorMessage(Throwable e, boolean pullToRefresh) {
        return CONNECT_TIME_OUT;
    }

    public static NewsFragment newInstance(String param1) {
        NewsFragment fragment = new NewsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            category = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public ViewDataBinding createViewDataBinding(LayoutInflater inflater, ViewGroup container) {
        return DataBindingUtil.inflate(inflater, R.layout.fragment_news, container, false);
    }

    @Override
    public void initializeInjector() {
        DaggerFocusComponent.builder()
                .focusModule(new FocusModule())
                .build()
                .inject(this);
    }

    @Override
    public void setData(AdapterBean data) {
        LogUtils.d("setData");
        viewDataBinding.setVariable(BR.adapterBean, data);
    }

    @Override
    public void loadData(boolean pullToRefresh) {
        LogUtils.d("loadData");
        presenter.loadData(pullToRefresh, category, getContext());
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(TAG, "onActivityCreated: ");
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d(TAG, "onAttach: ");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: ");
        //        bannerBean.setStartAutoPlay(true); 无效，因为在切换fragment的过程中不会调用此方法
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: ");
    }

    @Override
    public void onStop() {
        super.onStop();
        //        bannerBean.setStartAutoPlay(false);
        Log.d(TAG, "onStop: ");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG, "onDestroyView: ");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: cateId=" + getArguments().getInt("id"));
    }

    @Override
    public void onRefresh() {
        loadData(true);
    }

    @Override
    public SwipeRefreshLayout getContentView() {
        return contentView;
    }

    public void startAutoPlay() {
        if (presenter != null)
            presenter.startAutoPlay();
    }

    public void stopAutoPlay() {
        if (presenter != null)
            presenter.stopAutoPlay();
    }
}

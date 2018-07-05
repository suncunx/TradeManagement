package com.trade.other.focus.presenter;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;

import com.apkfuns.logutils.LogUtils;
import com.architecture.di.components.ApplicationComponent;
import com.architecture.model.AdapterBean;
import com.architecture.mvp.rv.BaseNovateRvPresenterImpl;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.trade.R;
import com.trade.app.TradeApplication;
import com.trade.other.focus.adapter.NewsAdapter;
import com.trade.other.focus.di.DaggerFocusPresenterComponent;
import com.trade.other.focus.di.FocusPresenterModule;
import com.trade.other.focus.model.BannerBean;
import com.trade.other.focus.model.BannerResultBean;
import com.trade.other.focus.model.NewsResultBean;
import com.trade.other.focus.model.NewsService;
import com.trade.other.focus.ui.NewsDetailActivity;
import com.trade.other.focus.view.NewsFragmentView;
import com.trade.widget.HorizontalDecoration;
import com.youth.banner.listener.OnBannerListener;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Stephen Sun on 2017/7/5 0005.
 * Email:suncunx@qq.com
 */

public class NewsFragmentPresenterImpl extends BaseNovateRvPresenterImpl<NewsFragmentView, NewsService> implements NewsFragmentPresenter, OnBannerListener {

    private static final String API_URL = "https://newsapi.org/v2/";
    private static final String API_KEY = "1a2ed8b9114942028dbada1d37080fe0";
    private BannerBean bannerBean;
    private AdapterBean adapterBean;
    private List<BannerResultBean.ResultBean> bannerResultList;
    private int pageNo = 1;   // 当前页面的编号
    private int pages;
    private static final int PAGE_SIZE = 5;
    private String category;

    @Override
    protected void init() {
        bannerBean = BannerBean.getDefaultInstance();
    }

    public void stopAutoPlay() {
        if (bannerBean != null) {
            bannerBean.setStartAutoPlay(false);
        }
    }

    public void startAutoPlay() {
        if (bannerBean != null) {
            bannerBean.setStartAutoPlay(true);
        }
    }

    @Override
    public void loadData(boolean pullToRefresh, String category, Context context) {
        this.category = category;
        getView().showLoading(pullToRefresh);
        pageNo = 1;
        request(pullToRefresh);

    }

    private void request(final boolean pullToRefresh) {
        Request.Builder builder = new Request.Builder();
        String url = "https://newsapi.org/v2/top-headlines?country=cn&pageSize=8&category=" + category + "&apiKey=1a2ed8b9114942028dbada1d37080fe0&page=" + pageNo;
        builder.url(url);
        TradeApplication.client.newCall(builder.build()).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        LogUtils.e(e.getMessage());
                        if (!pullToRefresh) {
                            if (getView() != null) {
                                getView().showError(new Exception(), pullToRefresh);
                            }
                        } else {
                            ToastUtils.showShort("网络连接超时");
                        }
                    }
                });

            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        String rsp = null;
                        try {
                            rsp = response.body().string();
                            Log.d("MainActivity", "onResponse: " + rsp);
                            NewsResultBean newsResultBean = new Gson().fromJson(rsp, NewsResultBean.class);
                            pages = newsResultBean.getTotalResults() / PAGE_SIZE;
                            List<NewsResultBean.ArticleBean> beanList = newsResultBean.getArticles();
                            adapter.setNewData(beanList);
                            if (!pullToRefresh) {
                                LinearLayoutManager layoutManager = new LinearLayoutManager(NewsFragmentPresenterImpl.this.context);
                                adapterBean = new AdapterBean(layoutManager, adapter, new HorizontalDecoration(NewsFragmentPresenterImpl.this.context, R.drawable.divider));
                                if (getView() == null)
                                    return;
                                getView().setData(adapterBean);
                                getView().showContent();
                                LogUtils.d("getView().setData(adapterBean);");
                            } else {
                                getView().stopRefresh();
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                });
            }
        });
    }

    @Override
    protected void initializeInjector(ApplicationComponent applicationComponent) {
        DaggerFocusPresenterComponent.builder()
                .applicationComponent(applicationComponent)
                .focusPresenterModule(new FocusPresenterModule())
                .build()
                .inject(this);
    }

    @Override
    protected BaseQuickAdapter createAdapter() {
        return new NewsAdapter(R.layout.item_list_news_one_image);
    }

    @Override
    public void loadMore() {
        LogUtils.d("loadMore");
        if (pageNo >= pages) {
            loadMoreEnd(false);
            return;
        }
        Request.Builder builder = new Request.Builder();
        String url = "https://newsapi.org/v2/top-headlines?country=cn&pageSize=8&category=" + category + "&apiKey=1a2ed8b9114942028dbada1d37080fe0&page=" + (++pageNo);
        builder.url(url);
        TradeApplication.client.newCall(builder.build()).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        LogUtils.e(e.getMessage());
                        adapter.loadMoreFail();
                    }
                });

            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        String rsp = null;
                        try {
                            rsp = response.body().string();
                            Log.d("MainActivity", "onResponse: " + rsp);
                            NewsResultBean newsResultBean = new Gson().fromJson(rsp, NewsResultBean.class);
                            pages = newsResultBean.getTotalResults() / PAGE_SIZE;
                            List<NewsResultBean.ArticleBean> beanList = newsResultBean.getArticles();
                            adapter.addData(beanList);
                            loadComplete();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        String url = ((NewsResultBean.ArticleBean) adapter.getData().get(position)).getUrl();
        Intent intent = NewsDetailActivity.getCallingIntent(context, url);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @Override
    public void OnBannerClick(int position) {
        Intent intent = NewsDetailActivity.getCallingIntent(context, bannerResultList.get(position).getUrl());
        context.startActivity(intent);
    }
}

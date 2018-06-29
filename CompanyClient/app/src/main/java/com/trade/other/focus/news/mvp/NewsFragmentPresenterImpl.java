package com.trade.other.focus.news.mvp;

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
import com.trade.other.focus.model.BannerResultBean;
import com.trade.other.focus.news.adapter.NewsAdapter;
import com.trade.other.focus.news.detail.NewsDetailActivity;
import com.trade.other.focus.news.di.DaggerNewsFragmentPresenterComponent;
import com.trade.other.focus.news.di.NewsFragmentPresenterModule;
import com.trade.other.focus.news.model.BannerBean;
import com.trade.other.focus.news.model.NewsResultBean;
import com.trade.other.focus.news.model.NewsService;
import com.trade.other.focus.news.model.WeatherBean;
import com.trade.util.BannerUtil;
import com.trade.view.HorizontalDecoration;
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

    private void initializeBanner(List<String> images, List<String> titles, OnBannerListener listener) {
        adapter.removeAllHeaderView();
        bannerBean.setImages(images);
        bannerBean.setTitles(titles);
        bannerBean.setStart(true);
        bannerBean.setOnBannerListener(listener);

    }

    private void initializeHeader(WeatherBean weatherBean) {

        View view = BannerUtil.getHeaderDataBinding(context, R.layout.header_news, getView().getContentView(), bannerBean, weatherBean).getRoot();
        adapter.addHeaderView(view);
    }

    @Override
    public void loadData(boolean pullToRefresh, String category, Context context) {
        this.category = category;
        getView().showLoading(pullToRefresh);
        pageNo = 1;
        request(pullToRefresh);
        //        requestNewsList(pullToRefresh, category);

    }

    //    private void requestBanner(final boolean pullToRefresh, final String cateId) {
    //        Map<String, Object> map = new HashMap<>();
    //        map.put(NewsConstant.KEY_CATE_ID, cateId);
    //        novate.post(NewsConstant.NEWS_BANNER, map, new BaseSubscriber<ResponseBody>() {
    //            @Override
    //            public void onError(Throwable e) {
    //
    //            }
    //
    //            @Override
    //            public void onNext(ResponseBody responseBody) {
    //                try {
    //                    String resultStr = responseBody.string();
    //                    JSONObject jsonObject = new JSONObject(resultStr);
    //
    //                    LogUtils.d(resultStr);
    //                    if (jsonObject.getString(ApiManager.CODE).equals(HttpCode.OK)) { // 200
    //                        BannerResultBean resultBean = GsonUtils.json2Bean(jsonObject, BannerResultBean.class);
    //                        bannerResultList = resultBean.getResult();
    //                        List<String> images = new ArrayList<>();
    //                        List<String> titles = new ArrayList<>();
    //                        for (BannerResultBean.ResultBean bean : bannerResultList) {
    //                            images.add(bean.getPicture());
    //                            titles.add(bean.getTitle());
    //                        }
    //                        initializeBanner(images, titles, NewsFragmentPresenterImpl.this);
    //                        requestWeather();
    //                    } else {
    //                        getView().showContent();
    //                    }
    //
    //                } catch (IOException e) {
    //                    e.printStackTrace();
    //                } catch (JSONException e) {
    //                    e.printStackTrace();
    //                }
    //            }
    //        });
    //    }

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
                                //                    requestBanner(false, category);
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
    //    private void requestWeather() {
    //        String city = "";
    //        novate.call(service.getWeather(city), new BaseSubscriber<WeatherBean>() {
    //            @Override
    //            public void onError(Throwable e) {
    //                getView().showError(e, false);
    //            }
    //
    //            @Override
    //            public void onNext(WeatherBean weatherBean) {
    //                initializeHeader(weatherBean);
    //                getView().showContent();
    //            }
    //        });
    //    }

    @Override
    protected void initializeInjector(ApplicationComponent applicationComponent) {
        DaggerNewsFragmentPresenterComponent.builder()
                .applicationComponent(applicationComponent)
                .newsFragmentPresenterModule(new NewsFragmentPresenterModule())
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

package com.trade.other.focus.news.model;


import com.trade.other.focus.model.BannerResultBean;

import java.util.Map;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by Stephen Sun on 2017/8/16 0016.
 * Email:suncunx@qq.com
 */

public interface NewsService {

    @FormUrlEncoded
    @POST("news/rotationList")
    Observable<BannerResultBean> getBanner(@Field("cate_id") String cateId);

    @FormUrlEncoded
    @POST("news/weather")
    Observable<WeatherBean> getWeather(@Field("city") String city);

//    @FormUrlEncoded
    @GET("top-headlines")
    Observable<NewsResultBean> requestNews(@QueryMap Map<String, String> map);
}

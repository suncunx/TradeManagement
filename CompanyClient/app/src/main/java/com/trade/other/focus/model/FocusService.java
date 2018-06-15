package com.trade.other.focus.model;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by Stephen Sun on 2017/8/16 0016.
 * Email:suncunx@qq.com
 */

public interface FocusService {
    @FormUrlEncoded
    @POST("news/newsCate")
    Observable<HomeResultBean> getTitles(@Field("type") String type);
}

package com.trade.util;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.trade.other.focus.news.model.BannerBean;
import com.trade.other.focus.news.model.WeatherBean;


/**
 * Created by Stephen Sun on 2017/7/7 0007.
 * Email:suncunx@qq.com
 */

public class BannerUtil {

    public static ViewDataBinding getHeaderDataBinding(Context context, int layoutId, ViewGroup parent, BannerBean bannerBean, WeatherBean weatherBean) {
        ViewDataBinding viewDataBinding = DataBindingUtil.inflate(LayoutInflater.from(context), layoutId, parent, false);
//        viewDataBinding.setVariable(BR.bannerBean, bannerBean);
//        viewDataBinding.setVariable(BR.weatherResultBean, weatherBean);
        return viewDataBinding;
    }

    //    Banner banner = new Banner(activity);
    //        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE); // 圆形加标题指示器
    //        banner.setImageLoader(new GlideImageLoader());
    //        banner.setBannerAnimation(Transformer.DepthPage);
    //        banner.isAutoPlay(true);
    //        banner.setDelayTime(1500);
    //        banner.setIndicatorGravity(BannerConfig.RIGHT);
    //        return banner;

}

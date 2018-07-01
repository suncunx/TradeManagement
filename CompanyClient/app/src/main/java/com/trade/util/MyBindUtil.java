package com.trade.util;

import android.databinding.BindingAdapter;

import com.blankj.utilcode.util.LogUtils;
import com.trade.other.focus.model.BannerBean;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stephen Sun on 2017/7/6 0006.
 * Email:suncunx@qq.com
 */

public class MyBindUtil {
    @BindingAdapter({"bannerStyle", "indicatorGravity", "imageLoader", "images", "titles", "start"})
    public static void setBanner(Banner banner, int bannerStyle, int indicatorGravity, ImageLoader imageLoader,
                                 List<String> images, List<String> titles, Boolean start) {
        LogUtils.i(start);
        banner.setBannerStyle(bannerStyle);
        banner.setIndicatorGravity(indicatorGravity);
        banner.setImageLoader(imageLoader);
        banner.setImages(images == null ? new ArrayList<String>() : images);
        banner.setBannerTitles(titles == null ? new ArrayList<String>() : titles);

        if (start) {
            banner.start();
        } else {
            banner.releaseBanner();
        }
    }

    @BindingAdapter({"bannerBean"})
    public static void setBanner(Banner banner, BannerBean bannerBean) {
        banner.setBannerStyle(bannerBean.getBannerStyle());
        banner.setIndicatorGravity(bannerBean.getIndicatorGravity());
        banner.setImageLoader(bannerBean.getImageLoader());
        banner.setImages(bannerBean.getImages() == null ? new ArrayList<String>() : bannerBean.getImages());
        banner.setBannerTitles(bannerBean.getTitles() == null ? new ArrayList<String>() : bannerBean.getTitles());
        banner.setOnBannerListener(bannerBean.getOnBannerListener());

        if (bannerBean.getStart()) {
            banner.start();
        } else {
            banner.releaseBanner();
        }
    }

    @BindingAdapter({"bannerStyle", "indicatorGravity", "imageLoader", "images", "start"})
    public static void setBanner(Banner banner, int bannerStyle, int indicatorGravity, ImageLoader imageLoader,
                                 List<String> images, Boolean start) {
        LogUtils.i(start);
        banner.setBannerStyle(bannerStyle);
        banner.setIndicatorGravity(indicatorGravity);
        banner.setImageLoader(imageLoader);
        banner.setImages(images == null ? new ArrayList<String>() : images);

        if (start) {
            banner.start();
        } else {
            banner.releaseBanner();
        }
    }

    @BindingAdapter({"autoPlay"})
    public static void setBannerAutoPlay(Banner banner, boolean autoPlay) {
        if (autoPlay) {
            banner.startAutoPlay();
        } else {
            banner.stopAutoPlay();
        }
    }


    //    @BindingAdapter({"imageLoader"})
    //    public static void setBanner(Banner banner, ImageLoader imageLoader) {
    //        LogUtils.i("imageLoader");
    //        banner.setImageLoader(imageLoader);
    //    }
    //
    //    @BindingAdapter({"images"})
    //    public static void setBanner(Banner banner, List<String> images) {
    //        banner.setImages(images);
    //    }
    //
    //    @BindingAdapter({"titles"})
    //    public static void setBannerTitles(Banner banner, List<String> titles) {
    //        banner.setBannerTitles(titles);
    //    }
    //
    //    @BindingAdapter({"bannerStyle", "indicatorGravity"})
    //    public static void setBannerTitles(Banner banner, int bannerStyle, int indicatorGravity) {
    //        banner.setBannerStyle(bannerStyle);
    //        banner.setIndicatorGravity(indicatorGravity);
    //    }

}

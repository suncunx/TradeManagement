package com.trade.other.focus.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.trade.BR;
import com.trade.other.focus.util.GlideImageLoader;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.List;

/**
 * Created by Stephen Sun on 2017/7/6 0006.
 * Email:suncunx@qq.com
 */

public class BannerBean extends BaseObservable {

    private int bannerStyle;
    private int indicatorGravity;

    private ImageLoader imageLoader;

    // 下面四个参数需要自定义
    private List<String> images;
    private List<String> titles;

    private boolean visible;
    private boolean start;
    private boolean startAutoPlay;
    private OnBannerListener onBannerListener;

    public static BannerBean getDefaultInstance() {
        return new BannerBean(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE, BannerConfig.RIGHT, new GlideImageLoader());
    }

    public static BannerBean getNoTitleInstance() {
        return new BannerBean(BannerConfig.CIRCLE_INDICATOR, BannerConfig.RIGHT, new GlideImageLoader());
    }

    private BannerBean(int bannerStyle, int indicatorGravity, ImageLoader imageLoader) {
        this.bannerStyle = bannerStyle;
        this.indicatorGravity = indicatorGravity;
        this.imageLoader = imageLoader;
    }

    public ImageLoader getImageLoader() {
        return imageLoader;
    }

    public void setImageLoader(ImageLoader imageLoader) {
        this.imageLoader = imageLoader;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public List<String> getTitles() {
        return titles;
    }

    public void setTitles(List<String> titles) {
        this.titles = titles;
    }

    @Bindable
    public boolean getVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
        notifyPropertyChanged(BR.visible);
    }

    public int getIndicatorGravity() {
        return indicatorGravity;
    }

    public void setIndicatorGravity(int indicatorGravity) {
        this.indicatorGravity = indicatorGravity;
    }

    @Bindable
    public boolean getStartAutoPlay() {
        return startAutoPlay;
    }

    public void setStartAutoPlay(boolean startAutoPlay) {
        this.startAutoPlay = startAutoPlay;
        notifyPropertyChanged(BR.startAutoPlay);
    }

    public int getBannerStyle() {
        return bannerStyle;
    }

    public void setBannerStyle(int bannerStyle) {
        this.bannerStyle = bannerStyle;
    }

    public boolean getStart() {
        return start;
    }

    public void setStart(boolean start) {
        this.start = start;
    }

    public OnBannerListener getOnBannerListener() {
        return onBannerListener;
    }

    public void setOnBannerListener(OnBannerListener onBannerListener) {
        this.onBannerListener = onBannerListener;
    }
}

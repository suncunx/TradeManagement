package com.trade.other.focus.model;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

/**
 * Created by Stephen Sun on 2017/7/5 0005.
 * Email:suncunx@qq.com
 */

public class NewsBean implements MultiItemEntity {
    public static final int ONE_IMAGE = 1;
    public static final int THREE_IMAGES = 2;

    private String title;
    private List<String> images;
    // 来源
    private String source;
    private int commentCount;

    private int itemType;

    public NewsBean(String title, List<String> images, String source, int commentCount, int itemType) {
        this.title = title;
        this.images = images;
        this.source = source;
        this.commentCount = commentCount;
        this.itemType = itemType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    @Override
    public int getItemType() {
        return itemType;
    }
}

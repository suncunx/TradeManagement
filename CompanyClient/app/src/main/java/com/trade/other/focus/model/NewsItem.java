package com.trade.other.focus.model;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * Created by Stephen Sun on 2017/7/6 0006.
 * Email:suncunx@qq.com
 */

public class NewsItem implements MultiItemEntity{
    public static final int ONE_IMAGE = 1;
    public static final int THREE_IMAGES = 2;

    private int itemType;

    @Override
    public int getItemType() {
        return 0;
    }
}

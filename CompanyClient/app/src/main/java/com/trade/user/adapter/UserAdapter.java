package com.trade.user.adapter;

import android.support.annotation.LayoutRes;

import com.architecture.adapter.BaseAdapter;
import com.architecture.adapter.BaseViewHolder;
import com.trade.BR;
import com.trade.user.model.UserItem;

/**
 * Created by Stephen Sun on 2017/7/10 0010.
 * Email:suncunx@qq.com
 */

public class UserAdapter extends BaseAdapter<UserItem> {

    public UserAdapter(@LayoutRes int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, UserItem item) {
        helper.setVariable(BR.userItem, item);
    }
}

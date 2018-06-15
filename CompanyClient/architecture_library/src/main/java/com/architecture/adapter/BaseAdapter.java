package com.architecture.adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.LayoutRes;
import android.view.View;
import android.view.ViewGroup;

import com.architecture.R;
import com.chad.library.adapter.base.BaseQuickAdapter;

/**
 * Created by Stephen Sun on 2017/6/10 0010.
 * Email:suncunx@qq.com
 * 支持DataBinding的QuickAdapter基类
 *  这里没有覆盖convert方法直接提供ViewDataBinding，1 不可以在抽象类中直接覆盖  2 覆盖了无法使用BaseViewHolder里面的方法，比如给子View设置监听器。牛逼
 *  普通的QuickAdapter直接使用BaseViewHolder即可
 *
 *  ViewHolder为什么不能使用内部类？？？？手动微笑
 */

public abstract class BaseAdapter<T> extends BaseQuickAdapter<T, BaseViewHolder> {

    public BaseAdapter(@LayoutRes int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected View getItemView(@LayoutRes int layoutResId, ViewGroup parent) {
        ViewDataBinding binding = DataBindingUtil.inflate(mLayoutInflater, layoutResId, parent, false);
        if (binding == null) {
            return super.getItemView(layoutResId, parent);
        }
        View view = binding.getRoot();
        view.setTag(R.id.BaseQuickAdapter_databinding_support, binding);
        return view;
    }

}

package com.architecture.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.v7.widget.RecyclerView;

import com.architecture.BR;

/**
 * Created by Stephen Sun on 2017/7/6 0006.
 * Email:suncunx@qq.com
 */

public class AdapterBean extends BaseObservable {

    private RecyclerView.LayoutManager layoutManager;

    private RecyclerView.Adapter adapter;

    private RecyclerView.ItemDecoration itemDecoration;

    public AdapterBean(RecyclerView.LayoutManager layoutManager, RecyclerView.Adapter adapter, RecyclerView.ItemDecoration itemDecoration) {
        this.layoutManager = layoutManager;
        this.adapter = adapter;
        this.itemDecoration = itemDecoration;
    }

    public RecyclerView.LayoutManager getLayoutManager() {
        return layoutManager;
    }

    public void setLayoutManager(RecyclerView.LayoutManager layoutManager) {
        this.layoutManager = layoutManager;
    }

    @Bindable
    public RecyclerView.Adapter getAdapter() {
        return adapter;
    }

    public void setAdapter(RecyclerView.Adapter adapter) {
        this.adapter = adapter;
        notifyPropertyChanged(BR.adapter);
    }

    public RecyclerView.ItemDecoration getItemDecoration() {
        return itemDecoration;
    }

    public void setItemDecoration(RecyclerView.ItemDecoration itemDecoration) {
        this.itemDecoration = itemDecoration;
    }
}

package com.trade.other.presenter;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.afollestad.materialdialogs.MaterialDialog;
import com.apkfuns.logutils.LogUtils;
import com.architecture.di.components.ApplicationComponent;
import com.architecture.model.AdapterBean;
import com.architecture.mvp.BasePresenterImpl;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.trade.R;
import com.trade.other.adapter.OtherAdapter;
import com.trade.other.ui.CustomerActivity;
import com.trade.other.ui.DeliverActivity;
import com.trade.other.ui.FinanceActivity;
import com.trade.other.focus.ui.FocusActivity;
import com.trade.other.model.OtherItem;
import com.trade.other.view.OtherView;
import com.trade.other.ui.SupplierActivity;
import com.trade.widget.HorizontalDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stephen Sun on 2017/7/5 0005.
 * Email:suncunx@qq.com
 */

public class OtherPresenterImpl extends BasePresenterImpl<OtherView> implements OtherPresenter, BaseQuickAdapter.OnItemClickListener {

    private int[] resIds = {R.mipmap.user_orders, R.mipmap.user_collection, R.mipmap.user_footprint, R.mipmap.user_data, R.mipmap.user_about_us};
    private String[] titles = {"供应商管理", "客户管理", "送货员管理", "财务报表", "新闻看点"};

    @Override
    public void loadUser() {
        getView().setData(getData());
    }

    private AdapterBean getData() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        RecyclerView.ItemDecoration decoration = new HorizontalDecoration(context, R.drawable.divider_gray_light);
        LogUtils.d(decoration);
        AdapterBean adapterBean = new AdapterBean(layoutManager, getAdapter(), decoration);
        return adapterBean;
    }

    private RecyclerView.Adapter getAdapter() {
        List<OtherItem> list = new ArrayList<>();

        for (int i = 0; i < titles.length; i++) {
            list.add(new OtherItem(resIds[i], titles[i]));
        }

        OtherAdapter adapter = new OtherAdapter(R.layout.item_list_other);
        adapter.setNewData(list);
        adapter.setOnItemClickListener(this);

        return adapter;
    }

    @Override
    protected void initializeInjector(ApplicationComponent applicationComponent) {
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        switch (position) {
            case 0:
                context.startActivity(SupplierActivity.getCallingIntent(context));
                break;
            case 1:
                context.startActivity(CustomerActivity.getCallingIntent(context));
                break;
            case 2:
                context.startActivity(DeliverActivity.getCallingIntent(context));
                break;
            case 3:
                showChooseYearDialog();
                break;
            case 4:
                context.startActivity(FocusActivity.getCallingIntent(context));
                break;
        }
    }

    private void showChooseYearDialog() {
        final List<String> years = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            int y = 2015 + i;
            years.add(y + "");
        }
        new MaterialDialog.Builder(getView().getUserActivity())
                .items(years)
                .itemsCallback(new MaterialDialog.ListCallback() {
                    @Override
                    public void onSelection(MaterialDialog dialog, View itemView, int position, CharSequence text) {
                        context.startActivity(FinanceActivity.getCallingIntent(context, years.get(position)));
                    }
                })
                .show();
    }
}

package com.trade.popup;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.apkfuns.logutils.LogUtils;
import com.architecture.model.DropMenuBean;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.trade.R;
import com.trade.view.HorizontalDecoration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Stephen Sun on 2017/7/31 0031.
 * Email:suncunx@qq.com
 */

public class PopupHelper {

    public static DropMenuBean getSimpleDropMenuBean(Context context, DropItemClickListener listener,
                                                 String header, List<String> data, View contentView) {
        List<String> headers = new ArrayList<>();
        headers.add(header);

        List<List<String>> dataList = new ArrayList<>();
        dataList.add(data);
        return getSimpleDropMenuBean(context, listener, headers, dataList, contentView);
    }
    public static DropMenuBean getSimpleDropMenuBean(Context context, DropItemClickListener listener, List<String> headers, List<List<String>> data, View contentView) {
        DropMenuBean bean = new DropMenuBean();

        List<View> popupViews = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            List<String> array = data.get(i);
            initPopupViews(context, listener, popupViews, headers.get(i), array, bean, i);
        }
        bean.setTabTexts(headers);
        bean.setPopupViews(popupViews);
        bean.setContentView(contentView);
        LogUtils.d(headers);
        LogUtils.d(popupViews);
        LogUtils.d(contentView);
        return bean;
    }
    /**
     * 获取结果列表的数据
     *
     * @param headers 标题数组
     * @param data    每个标题对应的列表数据的列表，长度与标题数量一致
     * @return 整个结果列表页面的数据结构
     */
    public static void setSimpleBean(Context context, DropItemClickListener listener, String[] headers, List<List<String>> data, DropMenuBean bean) {
        List<View> popupViews = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            List<String> array = data.get(i);
            initPopupViews(context, listener, popupViews, headers[i], array, bean, i);
        }

        bean.setTabTexts(Arrays.asList(headers));
        bean.setPopupViews(popupViews);
//        bean = new DropMenuBean(Arrays.asList(headers), popupViews, R.layout.content_in_bill_save, null, true, BR.inBillSaveBean, null);
//        return bean;
    }

    private static List<PopupItem> filter2Items(List<String> values) {
        List<PopupItem> list = new ArrayList<>();
        for (String value : values) {
            list.add(new PopupItem(value, false));
        }
        return list;
    }

    // simple
    private static void initPopupViews(Context context, final DropItemClickListener listener, List<View> popupViews, final String header, final List<String> array, final DropMenuBean bean, final int tabPosition) {

        RecyclerView recyclerView = new RecyclerView(context);
        final PopupAdapter popupAdapter = new PopupAdapter(R.layout.item_list_forum_filter_simple);
        popupAdapter.setNewData(filter2Items(array));

        recyclerView.addItemDecoration(new HorizontalDecoration(context, R.drawable.divider_thin));
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(popupAdapter);
        popupAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                popupAdapter.setCheckItem(position);
//                bean.getMenuBean().setTabText(position == 0 ? header : array[position]);
                bean.setTabText(array.get(position));
                bean.setClose(true);
                listener.onDropItemClick(adapter, position, tabPosition);
            }
        });

        popupViews.add(recyclerView);

    }

}

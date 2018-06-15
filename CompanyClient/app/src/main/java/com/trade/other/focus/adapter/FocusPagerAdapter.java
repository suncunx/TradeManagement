package com.trade.other.focus.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.apkfuns.logutils.LogUtils;
import com.trade.other.focus.model.FocusBean;

import java.util.List;

public class FocusPagerAdapter extends FragmentPagerAdapter {

    private List<FocusBean> list;

    public FocusPagerAdapter(FragmentManager fm, List<FocusBean> list) {
        super(fm);
        this.list = list;
        LogUtils.i(list);
    }

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        for (int i = 0; i < getCount(); i++) {
            if (i == position) {
                list.get(i).getFragment().startAutoPlay();
            } else {
                list.get(i).getFragment().stopAutoPlay();
            }
        }
        //        LogUtils.i(fragment);
        super.setPrimaryItem(container, position, object);
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position).getFragment();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return list.get(position).getTitle();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        //        super.destroyItem(container, position, object);
    }

    public List<FocusBean> getData() {
        return list;
    }

    public void setData(List<FocusBean> list) {
        this.list = list;
    }
}
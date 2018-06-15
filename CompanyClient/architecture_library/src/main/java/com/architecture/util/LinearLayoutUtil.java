package com.architecture.util;

import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * Created by Stephen Sun on 2016/8/12 0012.
 * Email:1243357168@qq.com
 */
public class LinearLayoutUtil {
    public static final LinearLayout.LayoutParams M_M = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
    public static final LinearLayout.LayoutParams M_W = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    public static final LinearLayout.LayoutParams W_M = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
    public static final LinearLayout.LayoutParams W_W = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    public static final LinearLayout.LayoutParams WEIGHT_M = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT,1);
    public static final LinearLayout.LayoutParams WEIGHT_W = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT,1);
    public static final LinearLayout.LayoutParams M_WEIGHT = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,0,1);
    public static final LinearLayout.LayoutParams W_WEIGHT = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,0,1);

}

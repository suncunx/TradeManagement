package com.trade.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.trade.R;


public class HorizontalDecoration extends RecyclerView.ItemDecoration {

    private Drawable mDivider;
    private int mOrientation;

    //我们通过获取系统属性中的listDivider来添加，在系统中的AppTheme中设置
    public static final int[] ATTRS = new int[]{
            android.R.attr.listDivider
    };

    public HorizontalDecoration(Context context, int drawableId) {
        //        final TypedArray ta = context.obtainStyledAttributes(ATTRS);

        this.mDivider = context.getResources().getDrawable(drawableId);
        //        ta.recycle();
        setOrientation(LinearLayoutManager.VERTICAL);
    }

    public HorizontalDecoration(Context context) {
        //        final TypedArray ta = context.obtainStyledAttributes(ATTRS);

        this.mDivider = context.getResources().getDrawable(R.drawable.divider);
        //        ta.recycle();
        setOrientation(LinearLayoutManager.VERTICAL);
    }

    //设置屏幕的方向
    public void setOrientation(int orientation) {
        mOrientation = orientation;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        drawHorizontalLine(c, parent, state);
    }

    //画横线, 这里的parent其实是显示在屏幕显示的这部分
    public void drawHorizontalLine(Canvas c, RecyclerView parent, RecyclerView.State state) {
        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();
        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);

            //获得child的布局信息
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            final int top = child.getBottom() + params.bottomMargin;
            final int bottom = top + mDivider.getIntrinsicHeight();
            mDivider.setBounds(left, top, right, bottom);

            mDivider.draw(c);
        }
    }

    //由于Divider也有长宽高，每一个Item需要向下或者向右偏移
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        //画横线，就是往下偏移一个分割线的高度
        outRect.set(0, 0, 0, mDivider.getIntrinsicHeight());
        //            LogUtils.d(mDivider.getIntrinsicHeight());
    }
}
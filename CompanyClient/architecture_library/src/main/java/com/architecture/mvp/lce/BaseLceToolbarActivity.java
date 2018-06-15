package com.architecture.mvp.lce;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;
import com.architecture.R;
import com.blankj.utilcode.util.StringUtils;
import com.hannesdorfmann.mosby3.mvp.MvpPresenter;
import com.hannesdorfmann.mosby3.mvp.lce.MvpLceView;

/**
 * Created by Stephen Sun on 2017/7/19 0019.
 * Email:suncunx@qq.com
 */

public abstract class BaseLceToolbarActivity<CV extends View, M, V extends MvpLceView<M>, P extends MvpPresenter<V>> extends BaseLceActivity<CV, M, V, P> implements BaseLceView<M> {
    private TextView title;
    private ImageView back;

    private TextView action;

    protected void setTitle(String msg) {
        if (title != null && !StringUtils.isEmpty(msg)) {
            title.setText(msg);
        }
    }

    protected void setAction(String text) {
        if (action != null && !StringUtils.isEmpty(text)) {
            LogUtils.d(text);
            action.setText(text);
        }
    }

    protected abstract String createTitle();

    /**
     * sometime you want to define back event
     */
    protected void setBackBtn() {
        if (back != null) {
            back.setVisibility(View.VISIBLE);
            back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        } else {
            LogUtils.e("back is null ,please check out");
        }

    }

    private void setBackClickListener(View.OnClickListener l) {
        if (l == null) {
            setBackBtn();
            return;
        }
        if (back != null) {
            back.setVisibility(View.VISIBLE);
            back.setOnClickListener(l);
        } else {
            LogUtils.e("back is null ,please check out");
        }

    }

    // 重写此方法为左边的View设置监听接口
    protected View.OnClickListener createBackClickListener() {
        return null;
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initToolbar();
    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) viewDataBinding.getRoot().findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }
        if (getSupportActionBar() != null) {
            // Enable the Up button
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            getSupportActionBar().setDisplayShowTitleEnabled(false);

        }
        back = (ImageView) viewDataBinding.getRoot().findViewById(R.id.iv_back);
        title = (TextView) viewDataBinding.getRoot().findViewById(R.id.tv_title);
        action = viewDataBinding.getRoot().findViewById(R.id.action);
        setTitle(createTitle());

        setBackClickListener(createBackClickListener());

        setRightClickListener();

    }

    private void setRightClickListener() {
        if (action != null) {
            action.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onClickRight(view);
                }
            });
        }
    }

    protected void onClickRight(View view) {}

}

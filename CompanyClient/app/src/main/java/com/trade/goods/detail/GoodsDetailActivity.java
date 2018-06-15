package com.trade.goods.detail;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;

import com.architecture.mvp.BaseToolbarActivity;
import com.trade.BR;
import com.trade.R;
import com.trade.goods.detail.di.DaggerGoodsDetailComponent;
import com.trade.goods.detail.di.GoodsDetailModule;
import com.trade.goods.detail.mvp.GoodsDetailPresenter;
import com.trade.goods.detail.mvp.GoodsDetailView;
import com.trade.model.GoodsResultBean;

/**
 * Created by Stephen Sun on 2017/7/11 0011.
 * Email:suncunx@qq.com
 */

public class GoodsDetailActivity extends BaseToolbarActivity<GoodsDetailView, GoodsDetailPresenter> implements GoodsDetailView {

    private static final String EXTRA = "goodsBean";
    private static final String TITLE = "商品详情";

    public static Intent getCallingIntent(Context context, GoodsResultBean.ResultBean.GoodsBean goodsBean) {
        Intent intent = new Intent(context, GoodsDetailActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra(EXTRA, goodsBean);
        return intent;
    }

    @Override
    public void init() {
        GoodsResultBean.ResultBean.GoodsBean goodsBean = (GoodsResultBean.ResultBean.GoodsBean) getIntent().getSerializableExtra(EXTRA);
        viewDataBinding.setVariable(BR.goodsBean, goodsBean);
        viewDataBinding.setVariable(BR.listener, presenter.getClickListener());
    }
    @Override
    public void initializeInjector() {
        DaggerGoodsDetailComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .goodsDetailModule(new GoodsDetailModule())
                .build()
                .inject(this);
    }

    @Override
    protected String createTitle() {
        return TITLE;
    }

    @Override
    public ViewDataBinding createViewDataBinding() {
        return DataBindingUtil.setContentView(this, R.layout.activity_goods);
    }
}

package com.trade.goods.presenter;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.architecture.di.components.ApplicationComponent;
import com.architecture.model.AdapterBean;
import com.architecture.mvp.rv.BaseNovateRvPresenterImpl;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.tamic.novate.Throwable;
import com.trade.R;
import com.trade.goods.adapter.GoodsAdapter;
import com.trade.goods.di.DaggerGoodsPresenterComponent;
import com.trade.goods.di.GoodsPresenterModule;
import com.trade.goods.model.GoodsModel;
import com.trade.goods.model.GoodsService;
import com.trade.goods.ui.GoodsDetailActivity;
import com.trade.goods.ui.GoodsSaveActivity;
import com.trade.goods.view.GoodsView;
import com.trade.listener.HttpCallback;
import com.trade.goods.model.GoodsResultBean;
import com.trade.model.SimpleResultBean;
import com.trade.widget.HorizontalDecoration;

import javax.inject.Inject;

/**
 * Created by Stephen Sun on 2017/7/5 0005.
 * Email:suncunx@qq.com
 */

public class GoodsPresenterImpl extends BaseNovateRvPresenterImpl<GoodsView, GoodsService> implements GoodsPresenter {

    private AdapterBean adapterBean;

    @Inject
    GoodsModel goodsModel;

    @Override
    protected void initializeInjector(ApplicationComponent applicationComponent) {
        DaggerGoodsPresenterComponent.builder()
                .applicationComponent(applicationComponent)
                .goodsPresenterModule(new GoodsPresenterModule())
                .build()
                .inject(this);
    }

    // 此方法理论上只会调用一次
    @Override
    public void loadGoods() {
        getView().showLoading(false);
        goodsModel.listGoods(new HttpCallback<GoodsResultBean>() {
            @Override
            public void onSuccess(GoodsResultBean resultBean) {
                if (resultBean.getResult().getGoodss().size() == 0) {
                    getView().showError(new IndexOutOfBoundsException(), false);
                    return;
                }
                adapter.setNewData(resultBean.getResult().getGoodss());
                adapterBean = new AdapterBean(new LinearLayoutManager(GoodsPresenterImpl.this.context),
                        adapter, new HorizontalDecoration(GoodsPresenterImpl.this.context, R.drawable.divider_message));
                getView().setData(adapterBean);
                getView().showContent();
            }

            @Override
            public void onFailure(Throwable e) {
                getView().showError(e, false);
            }
        });
    }

    @Override
    public void refreshGoods() {
        goodsModel.listGoods(new HttpCallback<GoodsResultBean>() {
            @Override
            public void onSuccess(GoodsResultBean resultBean) {
                if (resultBean.getResult().getGoodss().size() == 0) {
                    getView().showError(new IndexOutOfBoundsException(), true);
                    return;
                }
                adapter.setNewData(resultBean.getResult().getGoodss());
                adapter.notifyDataSetChanged();
                getView().showContent();
            }

            @Override
            public void onFailure(Throwable e) {
                getView().showError(e, false);
            }
        });
    }

    @Override
    protected BaseQuickAdapter createAdapter() {
        return new GoodsAdapter(R.layout.item_list_goods);
    }

    @Override
    protected void loadMore() {
        loadMoreEnd(false);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        GoodsResultBean.ResultBean.GoodsBean goodsBean = (GoodsResultBean.ResultBean.GoodsBean) adapter.getData().get(position);
        showActionSelectDialog(goodsBean);
    }

    private void showActionSelectDialog(final GoodsResultBean.ResultBean.GoodsBean goodsBean) {
        new MaterialDialog.Builder(getView().getGoodsActivity())
                .items("查看", "修改", "删除")
                .itemsCallback(new MaterialDialog.ListCallback() {
                    @Override
                    public void onSelection(MaterialDialog dialog, View itemView, int position, CharSequence text) {
                        if (position == 0) {
                            context.startActivity(GoodsDetailActivity.getCallingIntent(context, goodsBean));
                        } else if (position == 1) {
                            Intent intent = GoodsSaveActivity.getCallingIntent(context, goodsBean);
                            context.startActivity(intent);
                        } else {
                            showEnsureDeleteDialog(goodsBean);
                        }
                    }
                })
                .show();
    }

    private void showEnsureDeleteDialog(final GoodsResultBean.ResultBean.GoodsBean goodsBean) {
        new MaterialDialog.Builder(getView().getGoodsActivity())
                .negativeText("取消")
                .positiveText("确定")
                .title("提示")
                .content("确定要删除此商品吗")
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        if (Integer.parseInt(goodsBean.getRepertory()) > 0) {
                            ToastUtils.showShort("该商品存在库存，不得删除");
                        } else {
                            deleteGoods(goodsBean);
                        }
                    }
                })
                .show();
    }

    private void deleteGoods(GoodsResultBean.ResultBean.GoodsBean goodsBean) {
        goodsModel.removeGoods(goodsBean.getGoodsId(), goodsBean.getSupplierId(), new HttpCallback<SimpleResultBean>() {
            @Override
            public void onSuccess(SimpleResultBean simpleResultBean) {
                ToastUtils.showShort(simpleResultBean.getMsg());
                refreshGoods();
            }

            @Override
            public void onFailure(Throwable e) {
                ToastUtils.showShort(e.getMessage());
            }
        });
    }
}

package com.trade.home.detail.inbill.save.mvp;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.architecture.di.components.ApplicationComponent;
import com.architecture.model.DropMenuBean;
import com.architecture.mvp.lce.BaseNovateLcePresenterImpl;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.tamic.novate.BaseSubscriber;
import com.tamic.novate.Throwable;
import com.trade.BR;
import com.trade.R;
import com.trade.home.detail.inbill.save.di.DaggerInBillSavePresenterComponent;
import com.trade.home.detail.inbill.save.di.InBillSavePresenterModule;
import com.trade.home.detail.inbill.save.model.InBillSaveBean;
import com.trade.home.detail.inbill.save.model.InBillSaveService;
import com.trade.model.GoodsResultBean;
import com.trade.model.InBillResultBean;
import com.trade.model.SimpleResultBean;
import com.trade.popup.DropItemClickListener;
import com.trade.popup.PopupHelper;
import com.trade.popup.PopupItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.R.attr.min;

/**
 * Created by Stephen Sun on 2017/7/5 0005.
 * Email:suncunx@qq.com
 */
public class InBillSavePresenterImpl extends BaseNovateLcePresenterImpl<InBillSaveView, InBillSaveService> implements InBillSavePresenter, DropItemClickListener, View.OnClickListener {

    private static final String TAG = "OutBillSavePresenterImpl";
    private static final String HEADER = "选择商品";
    private List<String> goodsStrList = new ArrayList<>();
    private List<GoodsResultBean.ResultBean.GoodsBean> goodsList;
    private DropMenuBean dropMenuBean;
    private InBillSaveBean saveBean;
    private View contentView;
    private String goodsId;
    private String supplierId;
    private int repertory = -1;
    private int originCount;

    private InBillResultBean.ResultBean.InBillBean inBillBean;

    @Override
    protected void init() {
        saveBean = new InBillSaveBean("", InBillSavePresenterImpl.this);
        ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.content_in_bill_save, null, false);
        binding.setVariable(BR.inBillSaveBean, saveBean);
        contentView = binding.getRoot();
    }

    @Override
    protected void initializeInjector(ApplicationComponent applicationComponent) {
        DaggerInBillSavePresenterComponent.builder()
                .applicationComponent(applicationComponent)
                .inBillSavePresenterModule(new InBillSavePresenterModule())
                .build()
                .inject(this);
    }

    @Override
    public void onDropItemClick(BaseQuickAdapter adapter, int position, int tabPosition) {
        // 获取点击的商品，然后上传
        PopupItem popupItem = (PopupItem) adapter.getData().get(position);
        int i = 0;
        while (!goodsStrList.get(i).equals(popupItem.getText())) {
            i++;
        }
        goodsId = goodsList.get(i).getGoodsId();
        supplierId = goodsList.get(i).getSupplierId();
//        ToastUtils.showShort(goodsId);
    }

    // 添加
    @Override
    public void onClick(View view) {
        if (goodsId == null) {
            ToastUtils.showShort("请选择商品！");
            return;
        }
        if (TextUtils.isEmpty(saveBean.getGoodsCount().trim())) {
            ToastUtils.showShort("请输入商品数量！");
            return;
        }
        if (inBillBean != null) {
            int min = originCount - repertory;
            Log.d(TAG, "onClick: originCount = " + originCount + " repertory = " + repertory);
            if (Integer.parseInt(saveBean.getGoodsCount().trim()) < min) {
                ToastUtils.showLong("该商品存在出货，至少进货数量：" + min);
                return;
            }
        }
        Map<String, String> map = new HashMap<>();
        map.put("goodsId", goodsId);
        String goodsCount = saveBean.getGoodsCount();
        Log.d(TAG, "onClick: goodsCount = " + goodsCount);
        map.put("goodsCount", goodsCount);
        map.put("supplierId", supplierId);
        if (inBillBean == null) {
            saveInBill(map);
        } else {
            map.put("inBillId", inBillBean.getInBillId());
            updateInBill(map);
        }

    }

    private void updateInBill(Map<String, String> map) {
        novate.call(service.updateInBill(map), new BaseSubscriber<SimpleResultBean>() {
            @Override
            public void onError(Throwable e) {
                getView().showError(e, false);
            }

            @Override
            public void onNext(SimpleResultBean simpleResultBean) {
                ToastUtils.showShort(simpleResultBean.getMsg());
                getView().getActivity().finish();
            }
        });
    }

    private void saveInBill(Map<String, String> map) {
        novate.call(service.saveInBill(map), new BaseSubscriber<SimpleResultBean>() {
            @Override
            public void onError(Throwable e) {
                getView().showError(e, false);
            }

            @Override
            public void onNext(SimpleResultBean simpleResultBean) {
                ToastUtils.showShort(simpleResultBean.getMsg());
            }
        });
    }

    @Override
    public void loadGoods(final boolean pullToRefresh, final InBillResultBean.ResultBean.InBillBean inBillBean) {
        getView().showLoading(pullToRefresh);
        if (inBillBean != null) {
            this.inBillBean = inBillBean;
            originCount = Integer.parseInt(inBillBean.getGoodsCount());
            requestRepertory(inBillBean.getGoodsId());
        } else {
            loadContent();
        }
    }

    private void requestRepertory(String goodsId) {
        novate.call(service.getRepertory(goodsId), new BaseSubscriber<SimpleResultBean>() {
            @Override
            public void onError(Throwable e) {
                getView().showError(e, false);
            }

            @Override
            public void onNext(SimpleResultBean simpleResultBean) {
                if (!TextUtils.isEmpty(simpleResultBean.getResult()))
                    repertory = Integer.parseInt(simpleResultBean.getResult());
                loadContent();
            }
        });
    }

    private void loadContent() {
        novate.call(service.getGoods(), new BaseSubscriber<GoodsResultBean>() {
            @Override
            public void onError(Throwable e) {
                getView().showError(e, false);
            }

            @Override
            public void onNext(GoodsResultBean resultBean) {
                goodsList = resultBean.getResult().getGoodss();
                for (GoodsResultBean.ResultBean.GoodsBean goodsBean :
                        goodsList) {
                    goodsStrList.add(goodsBean.getName() + "（" + goodsBean.getUnit() + "，¥" + goodsBean.getInUnitPrice() + "）");
                }
                if (inBillBean != null) {
                    goodsId = inBillBean.getGoodsId();
                    supplierId = inBillBean.getSupplierId();
                    saveBean.setGoodsCount(inBillBean.getGoodsCount());
                    goodsStrList.clear();
                    dropMenuBean = PopupHelper.getSimpleDropMenuBean(InBillSavePresenterImpl.this.context, InBillSavePresenterImpl.this, inBillBean.getGoodsName() + "（" + inBillBean.getGoodsUnit() + "）", goodsStrList, contentView);
                } else {
                    dropMenuBean = PopupHelper.getSimpleDropMenuBean(InBillSavePresenterImpl.this.context, InBillSavePresenterImpl.this, HEADER, goodsStrList, contentView);
                }

                getView().setData(dropMenuBean);
                getView().showContent();
            }
        });
    }
}

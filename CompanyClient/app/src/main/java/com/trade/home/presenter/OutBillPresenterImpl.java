package com.trade.home.presenter;

import android.view.View;

import com.afollestad.materialdialogs.MaterialDialog;
import com.architecture.di.components.ApplicationComponent;
import com.architecture.mvp.BaseNovatePresenterImpl;
import com.architecture.util.BasePreferUtil;
import com.blankj.utilcode.util.ToastUtils;
import com.tamic.novate.BaseSubscriber;
import com.tamic.novate.Throwable;
import com.trade.model.DeliverBean;
import com.trade.home.model.OutBillService;
import com.trade.home.view.OutBillView;
import com.trade.home.di.DaggerHomePresenterComponent;
import com.trade.home.di.HomePresenterModule;
import com.trade.main.ui.MainActivity;
import com.trade.other.model.DeliverResultBean;
import com.trade.home.model.OutBillResultBean;
import com.trade.model.SimpleResultBean;
import com.trade.util.Constants;
import com.trade.util.PhoneUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Stephen Sun on 2017/7/11 0011.
 * Email:suncunx@qq.com
 */

public class OutBillPresenterImpl extends BaseNovatePresenterImpl<OutBillView, OutBillService> implements OutBillPresenter {

    @Override
    protected void initializeInjector(ApplicationComponent applicationComponent) {
        DaggerHomePresenterComponent.builder()
                .applicationComponent(applicationComponent)
                .homePresenterModule(new HomePresenterModule())
                .build()
                .inject(this);
    }

    @Override
    public OutBillClickListener getClickListener() {
        return new OutBillClickListener();
    }

    public class OutBillClickListener {
        public void onClickLeft(OutBillResultBean.ResultBean.OutBillBean outBillBean) {
            PhoneUtil.callPhone(getView().getApplicationComponent().context(), outBillBean.getCustomerPhone());
        }

        public void onClickRight(OutBillResultBean.ResultBean.OutBillBean outBillBean) {
            boolean deliverNot = outBillBean.getDeliverStatus().equals(Constants.DELIVER_NOT);// 未发货
            if (deliverNot) {
                // 弹出选择送货员的对话框
                showSelectDeliverDialog();
            } else {
                PhoneUtil.callPhone(getView().getApplicationComponent().context(), outBillBean.getDeliverPhone());
            }
        }
    }

    private String deliverId;
    private void showSelectDeliverDialog() {
        novate.call(service.getDelivers(), new BaseSubscriber<DeliverResultBean>() {
            @Override
            public void onError(Throwable e) {
                ToastUtils.showShort(e.getMessage());
            }

            @Override
            public void onNext(DeliverResultBean bean) {
                final List<DeliverResultBean.ResultBean.DeliverBean> delivers = bean.getResult().getDelivers();
                List<String> names = new ArrayList<String>();
                for (DeliverResultBean.ResultBean.DeliverBean tempBean :
                        delivers) {
                    names.add(tempBean.getDeliverMan());
                }
                new MaterialDialog.Builder(getView().getActivity())
                        .title("选择送货员")
                        .items(names)
                        .itemsCallback(new MaterialDialog.ListCallback() {
                            @Override
                            public void onSelection(MaterialDialog dialog, View itemView, int position, CharSequence text) {
                                deliverId = delivers.get(position).getDeliverId();
                                DeliverBean deliverBean = new DeliverBean(true, BasePreferUtil.getInstance().getUserId(), deliverId, getView().getOutBillBean().getOutBillId(), "1");
                                MainActivity.sendMsg(deliverBean);
                                updateOutBill();
                            }
                        })
                        .show();
            }
        });

    }

    private void updateOutBill() {
        OutBillResultBean.ResultBean.OutBillBean outBillBean = getView().getOutBillBean();
        Map<String, String> map = new HashMap<>();
        map.put("goodsId", outBillBean.getGoodsId());
        map.put("goodsCount", outBillBean.getGoodsCount());
        map.put("customerId", outBillBean.getCustomerId());
        map.put("payStatus", outBillBean.getPayStatus());
        map.put("deliverId", deliverId == null ? "" : deliverId); // 送货员可以不选，订单为未发货状态

        map.put("outBillId", outBillBean.getOutBillId());
        map.put("deliverStatus", Constants.DELIVER_ING_CODE + ""); // 配送中
        novate.call(service.updateOutBill(map), new BaseSubscriber<SimpleResultBean>() {
            @Override
            public void onError(Throwable e) {
                ToastUtils.showShort(e.getMessage());
            }

            @Override
            public void onNext(SimpleResultBean simpleResultBean) {
                ToastUtils.showShort("派送成功");
                getView().getActivity().finish();
            }
        });
    }

}

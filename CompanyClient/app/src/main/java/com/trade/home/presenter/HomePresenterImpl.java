package com.trade.home.presenter;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.afollestad.materialdialogs.MaterialDialog;
import com.architecture.di.components.ApplicationComponent;
import com.architecture.model.AdapterBean;
import com.architecture.mvp.rv.BaseNovateRvPresenterImpl;
import com.blankj.utilcode.util.LogUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.tamic.novate.BaseSubscriber;
import com.tamic.novate.Throwable;
import com.trade.R;
import com.trade.home.adapter.InBillAdapter;
import com.trade.home.adapter.OutBillAdapter;
import com.trade.home.ui.InBillActivity;
import com.trade.home.ui.InBillSaveActivity;
import com.trade.home.ui.OutBillActivity;
import com.trade.home.ui.OutBillSaveActivity;
import com.trade.home.di.DaggerHomePresenterComponent;
import com.trade.home.di.HomePresenterModule;
import com.trade.home.model.HomeService;
import com.trade.home.view.HomeView;
import com.trade.home.model.InBillResultBean;
import com.trade.home.model.OutBillResultBean;
import com.trade.util.Constants;
import com.trade.util.PreferUtil;
import com.trade.widget.HorizontalDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stephen Sun on 2017/7/5 0005.
 * Email:suncunx@qq.com
 */
public class HomePresenterImpl extends BaseNovateRvPresenterImpl<HomeView, HomeService> implements HomePresenter {

    private boolean oldOutBill;
    private AdapterBean adapterBean;
    private BaseQuickAdapter outBillAdapter = new OutBillAdapter(R.layout.item_list_out_bill);
    private BaseQuickAdapter inBillAdapter = new InBillAdapter(R.layout.item_list_in_bill);

    private int pageNo = 1;   // 当前页面的编号
    private int pages;

    @Override
    protected void init() {
        oldOutBill = PreferUtil.getInstance().isOutBill();
        outBillAdapter.setOnLoadMoreListener(this, getView().getRecyclerView());
        inBillAdapter.setOnLoadMoreListener(this, getView().getRecyclerView());
        outBillAdapter.setOnItemClickListener(this);
        inBillAdapter.setOnItemClickListener(this);
        adapterBean = new AdapterBean(new LinearLayoutManager(HomePresenterImpl.this.context), adapter, new HorizontalDecoration(HomePresenterImpl.this.context));
    }

    @Override
    public void loadTitles(boolean pullToRefresh, FragmentManager fragmentManager) {
        getView().showLoading(pullToRefresh);
        getData(pullToRefresh);

    }

    @Override
    public void onTitleChange(boolean isOutBill) {
        if (isOutBill && !oldOutBill) {
            adapter = outBillAdapter;
            pageNo = 1;
            clearData();
            requestOutBills(false);
            oldOutBill = !oldOutBill;
        } else if (!isOutBill && oldOutBill){
            adapter = inBillAdapter;
            pageNo = 1;
            clearData();
            requestInBills(false);
            oldOutBill = !oldOutBill;
        }
    }

    private void clearData() {
        if (adapter.getData() != null) {
            adapter.getData().clear();
        }
    }

    private void getData(final boolean pullToRefresh) {
        if (pullToRefresh) {
            pageNo = 1;
            clearData();
        }
        if (isOutBill()) {
            requestOutBills(pullToRefresh);
        } else {
            requestInBills(pullToRefresh);
        }

    }

    private void requestInBills(final boolean pullToRefresh) {
        novate.call(service.getInBills(pageNo), new BaseSubscriber<InBillResultBean>() {
            @Override
            public void onError(Throwable e) {
                getView().showError(e, pullToRefresh);
            }

            @Override
            public void onNext(InBillResultBean resultBean) {
                pages = Integer.parseInt(resultBean.getResult().getPageTotal());
                List<InBillResultBean.ResultBean.InBillBean> list = resultBean.getResult().getInBills();
                if (list.size() == 0) {
                    getView().showError(new IndexOutOfBoundsException(), pullToRefresh);
                    return;
                }
                adapter.setNewData(list);
                adapter.notifyDataSetChanged();
                adapterBean.setAdapter(adapter);
                getView().setData(adapterBean);

                getView().showContent();
            }
        });
    }

    private void requestOutBills(final boolean pullToRefresh) {
        novate.call(service.getOutBills(pageNo), new BaseSubscriber<OutBillResultBean>() {
            @Override
            public void onError(Throwable e) {
                LogUtils.d("onError");
                getView().showError(e, pullToRefresh);
            }

            @Override
            public void onNext(OutBillResultBean resultBean) {
                LogUtils.d("onNext resultBean = " + resultBean);
                pages = Integer.parseInt(resultBean.getResult().getPageTotal());
                List<OutBillResultBean.ResultBean.OutBillBean> list = resultBean.getResult().getOutBills();
                LogUtils.d(list);
                if (list.size() == 0) {
                    getView().showError(new IndexOutOfBoundsException(), pullToRefresh);
                    return;
                }
                adapter.setNewData(list);
                adapter.notifyDataSetChanged();
                adapterBean.setAdapter(adapter);
                getView().setData(adapterBean);

                getView().showContent();
            }
        });
    }

    @Override
    protected void initializeInjector(ApplicationComponent applicationComponent) {
        DaggerHomePresenterComponent.builder()
                .applicationComponent(applicationComponent)
                .homePresenterModule(new HomePresenterModule())
                .build()
                .inject(this);
    }

    private boolean isOutBill() {
        return PreferUtil.getInstance().isOutBill();
    }

    @Override
    protected BaseQuickAdapter createAdapter() {
        if (isOutBill()) {
            return outBillAdapter;
        } else {
            return inBillAdapter;
        }
    }

    @Override
    protected void loadMore() {
        if (pageNo >= pages) {
            loadMoreEnd(false);
            return;
        }
        pageNo++;
        if (isOutBill()) {
            loadMoreOutBills();
        } else {
            loadMoreInBills();
        }
    }

    private void loadMoreInBills() {
        novate.call(service.getInBills(pageNo), new BaseSubscriber<InBillResultBean>() {
            @Override
            public void onError(Throwable e) {
                getView().showError(e, false);
            }

            @Override
            public void onNext(InBillResultBean resultBean) {
                pages = Integer.parseInt(resultBean.getResult().getPageTotal());
                List<InBillResultBean.ResultBean.InBillBean> list = resultBean.getResult().getInBills();
                adapter.addData(list);
                loadComplete();
            }
        });
    }

    private void loadMoreOutBills() {
        novate.call(service.getOutBills(pageNo), new BaseSubscriber<OutBillResultBean>() {
            @Override
            public void onError(Throwable e) {
                LogUtils.d("onError");
                getView().showError(e, false);
            }

            @Override
            public void onNext(OutBillResultBean resultBean) {
                LogUtils.d("onNext resultBean = " + resultBean);
                pages = Integer.parseInt(resultBean.getResult().getPageTotal());
                List<OutBillResultBean.ResultBean.OutBillBean> list = resultBean.getResult().getOutBills();
                LogUtils.d(list);
                adapter.addData(list);
                loadComplete();
            }
        });
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        if (isOutBill()) {
            OutBillResultBean.ResultBean.OutBillBean outBillBean = (OutBillResultBean.ResultBean.OutBillBean) adapter.getData().get(position);
            showOutBillActionSelectDialog(outBillBean);
        } else {
            InBillResultBean.ResultBean.InBillBean inBillBean = (InBillResultBean.ResultBean.InBillBean) adapter.getData().get(position);
            showInBillActionSelectDialog(inBillBean);
        }
    }

    private void showOutBillActionSelectDialog(final OutBillResultBean.ResultBean.OutBillBean outBillBean) {
        List<String> items = new ArrayList<>();

        if (outBillBean.getDeliverStatus().equals(Constants.DELIVER_NOT)) {
            items.add("查看（派送）");
            items.add("修改");
        } else {
            items.add("查看");
        }
        new MaterialDialog.Builder(getView().getHomeActivity())
                .items(items)
                .itemsCallback(new MaterialDialog.ListCallback() {
                    @Override
                    public void onSelection(MaterialDialog dialog, View itemView, int position, CharSequence text) {
                        if (position == 0) {
                            context.startActivity(OutBillActivity.getCallingIntent(context, outBillBean));
                        } else if (position == 1) {
                            Intent intent = OutBillSaveActivity.getCallingIntent(context, outBillBean);
                            context.startActivity(intent);
                        }
                    }
                })
                .show();
    }

    private void showInBillActionSelectDialog(final InBillResultBean.ResultBean.InBillBean inBillBean) {
        new MaterialDialog.Builder(getView().getHomeActivity())
                .items("查看", "修改")
                .itemsCallback(new MaterialDialog.ListCallback() {
                    @Override
                    public void onSelection(MaterialDialog dialog, View itemView, int position, CharSequence text) {
                        if (position == 0) {
                            context.startActivity(InBillActivity.getCallingIntent(context, inBillBean));
                        } else if (position == 1) {
                            Intent intent = InBillSaveActivity.getCallingIntent(context, inBillBean);
                            context.startActivity(intent);
                        }
                    }
                })
                .show();
    }
}

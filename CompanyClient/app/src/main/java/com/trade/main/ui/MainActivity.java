package com.trade.main.ui;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.apkfuns.logutils.LogUtils;
import com.architecture.mvp.BaseActivity;
import com.architecture.util.BaseConstants;
import com.architecture.util.BasePreferUtil;
import com.blankj.utilcode.util.ToastUtils;
import com.trade.model.DeliverBean;
import com.trade.R;
import com.trade.goods.ui.GoodsFragment;
import com.trade.goods.ui.GoodsSaveActivity;
import com.trade.home.ui.HomeFragment;
import com.trade.main.di.DaggerMainComponent;
import com.trade.main.di.MainModule;
import com.trade.main.presenter.MainPresenter;
import com.trade.main.view.MainView;
import com.trade.model.ToolbarBean;
import com.trade.other.ui.OtherFragment;
import com.trade.user.UserFragment;
import com.trade.util.BottomNavigationViewHelper;
import com.trade.util.Constants;
import com.trade.util.PreferUtil;
import com.xuhao.android.libsocket.sdk.ConnectionInfo;
import com.xuhao.android.libsocket.sdk.OkSocketOptions;
import com.xuhao.android.libsocket.sdk.SocketActionAdapter;
import com.xuhao.android.libsocket.sdk.bean.IPulseSendable;
import com.xuhao.android.libsocket.sdk.bean.ISendable;
import com.xuhao.android.libsocket.sdk.bean.OriginalData;
import com.xuhao.android.libsocket.sdk.connection.IConnectionManager;
import com.xuhao.android.libsocket.sdk.connection.NoneReconnect;

import java.nio.charset.Charset;

import javax.inject.Inject;

import static com.xuhao.android.libsocket.sdk.OkSocket.open;

public class MainActivity extends BaseActivity<MainView, MainPresenter> implements MainView {

    private static final String TAG = "MainActivity";

    @Inject
    ToolbarBean toolbarBean;

    private HomeFragment homeFragment;
    private GoodsFragment goodsFragment;
    private OtherFragment otherFragment;
    private UserFragment userFragment;

    private TextView textView;

    public static Intent getCallingIntent(Context context) {
        return new Intent(context, MainActivity.class);
    }

    public static Intent getCallingIntent(Context context, String city) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra(Constants.EXTRA_CITY, city);
        return intent;
    }

    // 自动设置字体大小
    @Override
    public Resources getResources() {
        Resources res = super.getResources();
        Configuration config = res.getConfiguration();
        int size = PreferUtil.getInstance().getGlobalFont();
        switch (size) {
            case 1:
                config.fontScale = 0.75f; //1 设置正常字体大小的倍数
                break;
            case 2:
                config.fontScale = 1.0f; //1 设置正常字体大小的倍数
                break;
            case 3:
                config.fontScale = 1.25f; //1 设置正常字体大小的倍数
                break;
            case 4:
                config.fontScale = 1.5f; //1 设置正常字体大小的倍数
                break;
        }
        res.updateConfiguration(config, res.getDisplayMetrics());
        return res;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        LogUtils.d("onNewIntent");
        //        String city = intent.getStringExtra(Constants.EXTRA_CITY);
        //        if (!StringUtils.isEmpty(city)) {
        //            if (!StringUtils.equals(city, PreferUtil.getInstance().getCity())) {
        //                toolbarBean.setCity(city);
        //                PreferUtil.getInstance().setCity(city);
        //            }
        //        }
        if (homeFragment != null) {
            homeFragment.refresh();
        }
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            final FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            hideFragments(fragmentTransaction);

            switch (item.getItemId()) {
                case R.id.navigation_home:
                    showHome(fragmentTransaction);
                    return true;
                case R.id.navigation_goods:
                    showGoods(fragmentTransaction);
                    return true;
                case R.id.navigation_other:
                    showOther(fragmentTransaction);
                    return true;
                case R.id.navigation_user:
                    showUser(fragmentTransaction);
                    return true;
            }
            return false;
        }

    };

    private void showHome(FragmentTransaction fragmentTransaction) {
        showHome();
        showNotGoods();
        if (homeFragment == null) {
            homeFragment = HomeFragment.newInstance();
            addFragment(R.id.contentView, homeFragment);
        }
        fragmentTransaction.show(homeFragment);
        fragmentTransaction.commit();
    }

    private void showGoods(FragmentTransaction fragmentTransaction) {
        showNotHome(Constants.MAIN_GOODS);
        showGoods();
        if (goodsFragment == null) {
            goodsFragment = GoodsFragment.newInstance();
            addFragment(R.id.contentView, goodsFragment);
        }
        fragmentTransaction.show(goodsFragment);
        fragmentTransaction.commit();
    }

    private void showOther(FragmentTransaction fragmentTransaction) {
        showNotHome(Constants.MAIN_OTHER);
        showNotGoods();
        if (otherFragment == null) {
            otherFragment = OtherFragment.newInstance();
            addFragment(R.id.contentView, otherFragment);
        }
        fragmentTransaction.show(otherFragment);
        fragmentTransaction.commit();
    }

    private void showUser(FragmentTransaction fragmentTransaction) {
        showNotHome(Constants.MAIN_USER);
        showNotGoods();
        if (userFragment == null) {
            userFragment = UserFragment.newInstance();
            addFragment(R.id.contentView, userFragment);
        }
        fragmentTransaction.show(userFragment);
        fragmentTransaction.commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initSocket();
        initializeNavigationView();
        viewDataBinding.setVariable(com.trade.BR.toolBarBean, toolbarBean);
    }
    private ConnectionInfo mInfo;

    private static IConnectionManager mManager;
    private OkSocketOptions mOkOptions;

    private void initSocket() {
        //        mInfo = new ConnectionInfo("192.168.89.2", 8081);
        //        mInfo = new ConnectionInfo("192.168.23.1", 8081);
        mInfo = new ConnectionInfo(BaseConstants.BASE_URL_SOCKET, BaseConstants.BASE_PORT_SOCKET);
        mOkOptions = new OkSocketOptions.Builder()
                .setReconnectionManager(new NoneReconnect())
                .setWritePackageBytes(1024)
                .setConnectTimeoutSecond(10)
                .build();
        mManager = open(mInfo).option(mOkOptions);
        registerSocketReceiver();
        mManager.connect();
    }

    private SocketActionAdapter adapter = new SocketActionAdapter() {

        @Override
        public void onSocketConnectionSuccess(Context context, ConnectionInfo info, String action) {
            mManager.send(new DeliverBean(true, BasePreferUtil.getInstance().getUserId(), "", "", ""));
        }

        @Override
        public void onSocketDisconnection(Context context, ConnectionInfo info, String action, Exception e) {
            if (e != null) {
                //                ToastUtils.showShort("异常断开");
            } else {
                //                ToastUtils.showShort("正常断开");
            }
        }

        @Override
        public void onSocketConnectionFailed(Context context, ConnectionInfo info, String action, Exception e) {
            ToastUtils.showShort("连接socket服务器失败");
        }

        @Override
        public void onSocketReadResponse(Context context, ConnectionInfo info, String action, OriginalData data) {
            super.onSocketReadResponse(context, info, action, data);
            String str = new String(data.getBodyBytes(), Charset.forName("utf-8"));
            Log.d(TAG, "onSocketReadResponse: " + str);
        }

        @Override
        public void onSocketWriteResponse(Context context, ConnectionInfo info, String action, ISendable data) {
            super.onSocketWriteResponse(context, info, action, data);
            String str = new String(data.parse(), Charset.forName("utf-8"));
            Log.d(TAG, "onSocketWriteResponse: " + str);
        }

        @Override
        public void onPulseSend(Context context, ConnectionInfo info, IPulseSendable data) {
            super.onPulseSend(context, info, data);
            String str = new String(data.parse(), Charset.forName("utf-8"));
            Log.d(TAG, "onPulseSend: " + str);
        }
    };

    private void registerSocketReceiver() {
        mManager.registerReceiver(adapter);
    }

    public static void sendMsg(DeliverBean bean) {
        Log.d(TAG, "sendMsg: deliverBean = " + bean);
        if (!mManager.isConnect()) {
            mManager.connect();
        }
        mManager.send(bean);
    }


    @Override
    public void initializeInjector() {
        DaggerMainComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .mainModule(new MainModule())
                .build()
                .inject(this);
    }

    @Override
    public ViewDataBinding createViewDataBinding() {
        return DataBindingUtil.setContentView(this, R.layout.activity_main);
    }

    @Override
    public void init() {
        textView = viewDataBinding.getRoot().findViewById(R.id.text_add);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.this.startActivity(GoodsSaveActivity.getCallingIntent(MainActivity.this));
            }
        });
    }

    private void initializeNavigationView() {
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        BottomNavigationViewHelper.disableShiftMode(navigation);
        navigation.setSelectedItemId(R.id.navigation_home);

    }

    private void hideFragments(FragmentTransaction transaction) {
        if (homeFragment != null) {
            transaction.hide(homeFragment);
        }
        if (goodsFragment != null) {
            transaction.hide(goodsFragment);
        }
        if (otherFragment != null) {
            transaction.hide(otherFragment);
        }
        if (userFragment != null) {
            transaction.hide(userFragment);
        }
    }

    private void showHome() {
        toolbarBean.setHome(true);
        toolbarBean.setTitle(PreferUtil.getInstance().isOutBill() ? Constants.BILL_OUT : Constants.BILL_IN);
    }

    private void showNotHome(String title) {
        toolbarBean.setHome(false);
        toolbarBean.setTitle(title);
    }

    private void showGoods() {
        textView.setVisibility(View.VISIBLE);
    }

    private void showNotGoods() {
        textView.setVisibility(View.GONE);
    }

    protected void addFragment(int containerViewId, Fragment fragment) {
        final FragmentTransaction fragmentTransaction = this.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(containerViewId, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onTitleChange(boolean isOutBill) {
        toolbarBean.setTitle(isOutBill ? Constants.BILL_OUT : Constants.BILL_IN);
        homeFragment.onTitleChange(isOutBill);
    }
    long waitTime = 2000;
    long touchTime = 0;
    @Override
    public void onBackPressed() {

        long currentTime = System.currentTimeMillis();
        if ((currentTime - touchTime) >= waitTime) {
            //让Toast的显示时间和等待时间相同
            Toast.makeText(this, "再按一次退出智贸易", Toast.LENGTH_LONG).show();
            touchTime = currentTime;
        } else {
            finish();
        }

    }

    private void test() {

    }
}

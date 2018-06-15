package com.trade.deliver;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.tamic.novate.BaseSubscriber;
import com.tamic.novate.Novate;
import com.tamic.novate.Throwable;
import com.xuhao.android.libsocket.sdk.ConnectionInfo;
import com.xuhao.android.libsocket.sdk.OkSocketOptions;
import com.xuhao.android.libsocket.sdk.SocketActionAdapter;
import com.xuhao.android.libsocket.sdk.bean.IPulseSendable;
import com.xuhao.android.libsocket.sdk.bean.ISendable;
import com.xuhao.android.libsocket.sdk.bean.OriginalData;
import com.xuhao.android.libsocket.sdk.connection.IConnectionManager;
import com.xuhao.android.libsocket.sdk.connection.NoneReconnect;

import java.nio.charset.Charset;

import static com.xuhao.android.libsocket.sdk.OkSocket.open;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";
    private ConnectionInfo mInfo;

    private static IConnectionManager mManager;
    private OkSocketOptions mOkOptions;

    private TextView textName, textPhone, textAddress, textGoodsName, textUnit, textUnitPrice, textCount, textTotalPrice;
    private ImageView imageView;
    private Button button;

    private LinearLayout content;
    private TextView textNoMission;

    private MaterialDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initContent();
        initSocket();
    }

    private void initContent() {
        showProgressDialog();
        Novate.Builder builder = new Novate.Builder(this)
                .baseUrl(Constant.BASE_URL)
                .connectTimeout(10)
                .addLog(true);
        Novate novate = builder.build();
        DeliverService service = novate.create(DeliverService.class);
        novate.call(service.mission(PreferUtil.getInstance().getDeliverId()), new BaseSubscriber<OutBillDetailResultBean>() {
            @Override
            public void onError(Throwable e) {
                dismissProgressDialog();
                if (!e.getMessage().equals("解析错误"))
                    Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e(TAG, "onError: " + e.getMessage());
            }

            @Override
            public void onNext(OutBillDetailResultBean resultBean) {
                dismissProgressDialog();
                if (resultBean.getCode() == 200) {
                    PreferUtil.getInstance().setUserId(resultBean.getResult().getOutBill().getUserId());
                    PreferUtil.getInstance().setOutBillId(resultBean.getResult().getOutBill().getOutBillId());
                    showBillDetail(resultBean.getResult().getOutBill());
                }
            }
        });

        //        boolean hasMission = PreferUtil.getInstance().getOutBillId() != null;
        //        if (hasMission) {
        //            requestBillDetail(PreferUtil.getInstance().getUserId(), PreferUtil.getInstance().getOutBillId());

        //            textNoMission.setVisibility(View.GONE);
        //            content.setVisibility(View.VISIBLE);
        //        } else {
        //            textNoMission.setVisibility(View.VISIBLE);
        //            content.setVisibility(View.GONE);
        //        }
    }

    private void initViews() {
        textNoMission = (TextView) findViewById(R.id.tv_no_mission);
        content = (LinearLayout) findViewById(R.id.content);

        textName = (TextView) findViewById(R.id.tv_name);
        textPhone = (TextView) findViewById(R.id.tv_phone);
        textAddress = (TextView) findViewById(R.id.tv_address);
        textGoodsName = (TextView) findViewById(R.id.tv_goods_name);
        textUnit = (TextView) findViewById(R.id.tv_unit);
        textUnitPrice = (TextView) findViewById(R.id.tv_unit_price);
        textCount = (TextView) findViewById(R.id.tv_count);
        textTotalPrice = (TextView) findViewById(R.id.tv_total_price);

        imageView = (ImageView) findViewById(R.id.iv_goods);
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(this);
        textNoMission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initContent();
            }
        });

        content.setVisibility(View.GONE);
    }

    private void initSocket() {
        //        mInfo = new ConnectionInfo("192.168.42.230", 8081);
        //        mInfo = new ConnectionInfo("192.168.23.1", 8081);
        mInfo = new ConnectionInfo(Constant.BASE_URL_SOCKET, Constant.BASE_PORT_SOCKET);
        mOkOptions = new OkSocketOptions.Builder()
                .setReconnectionManager(new NoneReconnect())
                .setWritePackageBytes(1024)
                .build();
        mManager = open(mInfo).option(mOkOptions);
        registerSocketReceiver();
        mManager.connect();
    }

    private SocketActionAdapter adapter = new SocketActionAdapter() {

        @Override
        public void onSocketConnectionSuccess(Context context, ConnectionInfo info, String action) {
            mManager.send(new DeliverBean(false, "", PreferUtil.getInstance().getDeliverId(), "", ""));
        }

        @Override
        public void onSocketDisconnection(Context context, ConnectionInfo info, String action, Exception e) {
            if (e != null) {
                Toast.makeText(MainActivity.this, "异常断开", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity.this, "正常断开", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onSocketConnectionFailed(Context context, ConnectionInfo info, String action, Exception e) {
            Toast.makeText(MainActivity.this, "连接socket服务器失败", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onSocketReadResponse(Context context, ConnectionInfo info, String action, OriginalData data) {
            super.onSocketReadResponse(context, info, action, data);
            String str = new String(data.getBodyBytes(), Charset.forName("utf-8"));
            Log.d(TAG, "onSocketReadResponse: " + str);
            onReadResponse(str);
        }

        @Override
        public void onSocketWriteResponse(Context context, ConnectionInfo info, String action, ISendable data) {
            super.onSocketWriteResponse(context, info, action, data);
            String str = new String(data.parse(), Charset.forName("utf-8"));
            str = str.substring(str.lastIndexOf("{"));
            DeliverBean bean = new Gson().fromJson(str, DeliverBean.class);
            if (bean.getStatus().equals("0")) {
                Toast.makeText(MainActivity.this, "送货成功！", Toast.LENGTH_SHORT).show();
                resetStatus();
            }
            Log.d(TAG, "onSocketWriteResponse: " + str);
        }

        @Override
        public void onPulseSend(Context context, ConnectionInfo info, IPulseSendable data) {
            super.onPulseSend(context, info, data);
            String str = new String(data.parse(), Charset.forName("utf-8"));
            Log.d(TAG, "onPulseSend: " + str);
        }
    };

    private void resetStatus() {
        PreferUtil.getInstance().setOutBillId(null);
        PreferUtil.getInstance().setUserId(null);
        content.setVisibility(View.GONE);
        textNoMission.setVisibility(View.VISIBLE);
    }

    private void onReadResponse(String response) {
        Gson gson = new Gson();
        DeliverBean deliverBean = gson.fromJson(response, DeliverBean.class);
        PreferUtil.getInstance().setDeliverBean(deliverBean);

        requestBillDetail(deliverBean.getUserId(), deliverBean.getObId());
    }

    private void requestBillDetail(String userId, String obId) {
        Novate.Builder builder = new Novate.Builder(this)
                .baseUrl(Constant.BASE_URL)
                .connectTimeout(10)
                .addLog(true);
        Novate novate = builder.build();
        DeliverService service = novate.create(DeliverService.class);
        novate.call(service.detailOutBill(userId, obId), new BaseSubscriber<OutBillResultBean>() {
            @Override
            public void onError(Throwable e) {
//                Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e(TAG, "onError: " + e.getMessage());
            }

            @Override
            public void onNext(OutBillResultBean outBillResultBean) {
                showBillDetail(outBillResultBean.getResult().getOutBill());
            }
        });
    }

    private void showBillDetail(OutBillResultBean.ResultBean.OutBillBean outBillBean) {
        textNoMission.setVisibility(View.GONE);
        content.setVisibility(View.VISIBLE);

        textName.setText(outBillBean.getCustomerName());
        textPhone.setText(outBillBean.getCustomerPhone());
        textAddress.setText(outBillBean.getCustomerAddress());

        textGoodsName.setText(outBillBean.getGoodsName());
        textUnit.setText(outBillBean.getGoodsUnit());
        textUnitPrice.setText(outBillBean.getGoodsOutUnitPrice());
        textCount.setText(outBillBean.getGoodsCount());

        textTotalPrice.setText(outBillBean.getTotalPrice());
        Glide.with(this).load(outBillBean.getGoodsImage()).into(imageView);
    }

    private void showBillDetail(OutBillDetailResultBean.ResultBean.OutBillBean outBillBean) {
        textNoMission.setVisibility(View.GONE);
        content.setVisibility(View.VISIBLE);

        textName.setText(outBillBean.getCustomerName());
        textPhone.setText(outBillBean.getCustomerPhone());
        textAddress.setText(outBillBean.getCustomerAddress());

        textGoodsName.setText(outBillBean.getGoodsName());
        textUnit.setText(outBillBean.getGoodsUnit());
        textUnitPrice.setText(outBillBean.getGoodsOutUnitPrice());
        textCount.setText(outBillBean.getGoodsCount());

        textTotalPrice.setText(outBillBean.getTotalPrice());
        Glide.with(this).load(outBillBean.getGoodsImage()).into(imageView);
    }

    private void registerSocketReceiver() {
        mManager.registerReceiver(adapter);
    }

    public static void registerSocketReceiver(SocketActionAdapter actionAdapter) {
        mManager.registerReceiver(actionAdapter);
    }

    public static void sendMsg(DeliverBean bean) {
        Log.d(TAG, "sendMsg: deliverBean = " + bean);
        if (!mManager.isConnect()) {
            mManager.connect();
        }
        mManager.send(bean);
    }

    @Override
    public void onClick(View view) {
        showEnsureDialog();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        showLogOutDialog();
        return true;
    }

    private void showLogOutDialog() {
        new MaterialDialog.Builder(this)
                .negativeText("取消")
                .positiveText("确定")
                .title("提示")
                .content("确认退出登录吗")
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        PreferUtil.getInstance().setLoginOut();
                        PreferUtil.getInstance().setDeliverId(null);
                        PreferUtil.getInstance().setOutBillId(null);
                        PreferUtil.getInstance().setUserId(null);
                        finish();
                    }
                })
                .show();
    }

    private void showEnsureDialog() {
        new MaterialDialog.Builder(this)
                .negativeText("取消")
                .positiveText("确定")
                .title("提示")
                .content("确认货物已送达吗")
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        DeliverBean deliverBean = new DeliverBean(false, PreferUtil.getInstance().getUserId(), PreferUtil.getInstance().getDeliverId(), PreferUtil.getInstance().getOutBillId(), "0");
                        Log.d(TAG, "onClick: " + deliverBean.toString());
                        sendMsg(deliverBean);
                    }
                })
                .show();
    }

    public void showProgressDialog() {
        MaterialDialog.Builder builder = new MaterialDialog.Builder(this)
                //                .title("提示")
                .content("正在刷新...")
                .progress(true, 0);
        progress = builder.cancelable(false)
                .canceledOnTouchOutside(false)
                .show();
    }

    public void dismissProgressDialog() {
        if (progress != null) {
            progress.dismiss();
        }
    }

    long waitTime = 2000;
    long touchTime = 0;

    @Override
    public void onBackPressed() {

        long currentTime = System.currentTimeMillis();
        if ((currentTime - touchTime) >= waitTime) {
            //让Toast的显示时间和等待时间相同
            Toast.makeText(this, "再按一次退出", Toast.LENGTH_LONG).show();
            touchTime = currentTime;
        } else {
            finish();
        }

    }
}

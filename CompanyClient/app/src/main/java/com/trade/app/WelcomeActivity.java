package com.trade.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.trade.R;
import com.trade.login.ui.LoginActivity;
import com.trade.main.ui.MainActivity;
import com.trade.util.PreferUtil;

/**
 * Created by Stephen Sun on 2018/4/30 0030.
 * Email:suncunx@qq.com
 */

public class WelcomeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);
//        if (PreferUtil.getInstance().isLogin()) {
            startActivity(new Intent(MainActivity.getCallingIntent(this)));
            finish();
//        } else {
//            startActivity(new Intent(this, LoginActivity.class));
//            finish();
//        }
    }
}

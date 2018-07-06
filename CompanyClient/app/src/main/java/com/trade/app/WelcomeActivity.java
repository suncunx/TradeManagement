package com.trade.app;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

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
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_welcome);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (PreferUtil.getInstance().isLogin()) {
                    startActivity(new Intent(MainActivity.getCallingIntent(WelcomeActivity.this)));
                    finish();
                } else {
                    startActivity(new Intent(WelcomeActivity.this, LoginActivity.class));
                    finish();
                }
            }
        }, 2000);

    }
}

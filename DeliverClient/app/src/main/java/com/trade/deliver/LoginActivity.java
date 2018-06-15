package com.trade.deliver;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.tamic.novate.BaseSubscriber;
import com.tamic.novate.Novate;
import com.tamic.novate.Throwable;

import static com.tamic.novate.Novate.TAG;
import static com.trade.deliver.Constant.BASE_URL;

/**
 * Created by Stephen Sun on 2018/5/5 0005.
 * Email:suncunx@qq.com
 */

public class LoginActivity extends Activity implements View.OnClickListener {

    private EditText editPhone, editPassword;
    private ImageView imageEye;
    private Button btnLogin;
    private boolean showPassword = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initViews();
    }

    private void initViews() {
        editPhone = findViewById(R.id.et_phone);
        editPassword = findViewById(R.id.et_password);
        imageEye = findViewById(R.id.iv_eye);
        btnLogin = findViewById(R.id.btn_login);

        imageEye.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_eye:
                if (!showPassword) {
                    editPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    editPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
                showPassword = !showPassword;
                break;
            case R.id.btn_login:
                onLogin();
                break;
        }
    }

    private void onLogin() {
        String phone = editPhone.getText().toString().trim();
        String password = editPassword.getText().toString().trim();

        if (!checkPhone(phone)) {
            Toast.makeText(this, "手机号码不正确，请重新输入", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
        } else {
            requestLogin(phone, password);
        }
    }

    private void requestLogin(final String phone, String password) {

        Novate.Builder builder = new Novate.Builder(this)
                .baseUrl(BASE_URL)
                .connectTimeout(10)
                .addLog(true);
        Novate novate = builder.build();
        DeliverService service = novate.create(DeliverService.class);
        novate.call(service.login(phone, password), new BaseSubscriber<SimpleResultBean>() {
            @Override
            public void onError(Throwable e) {
                Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e(TAG, "onError: " + e.getMessage());
            }

            @Override
            public void onNext(SimpleResultBean loginResultBean) {
                Log.e("Login", "onNext: result = " + loginResultBean.getResult());
                if (loginResultBean.getCode().equals("200")) {
                    PreferUtil.getInstance().setUserInfo(loginResultBean.getResult(), phone, "已登录");
                    PreferUtil.getInstance().setHasLogin();
                    loginSuccess();
                } else {
                    Toast.makeText(LoginActivity.this, "账号或密码错误", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void loginSuccess() {
        Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public static boolean checkPhone(String phone) {
        if (phone.trim().length() != 11 || phone.charAt(0) != '1') {
            return false;
        }
        for (int i = 0; i < phone.length(); i++) {
            if (phone.charAt(i) > '9' || phone.charAt(i) < '0')
                return false;
        }
        return true;
    }
}

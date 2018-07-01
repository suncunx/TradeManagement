package com.trade.login.model;

/**
 * Created by Stephen Sun on 2017/7/11 0011.
 * Email:suncunx@qq.com
 */

public class LoginBean {

    private String phone;
    private String password;
    private String verify;

    public LoginBean(String phone, String password, String verify) {
        this.phone = phone;
        this.password = password;
        this.verify = verify;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getVerify() {
        return verify;
    }

    public void setVerify(String verify) {
        this.verify = verify;
    }
}

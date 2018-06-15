package com.trade.deliver;

/**
 * Created by Stephen Sun on 2017/8/15 0015.
 * Email:suncunx@qq.com
 */

public class SimpleResultBean {
    private String code;
    private String msg;
    private String result;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "SimpleResultBean{" +
                "code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                ", result='" + result + '\'' +
                '}';
    }
}

package com.trade.other.focus.news.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Stephen Sun on 2017/8/16 0016.
 * Email:suncunx@qq.com
 */

public class WeatherBean {

    /**
     * code : 200
     * msg : 查询成功
     * result : {"tmp":"11°C","code":"https://cdn.heweather.com/cond_icon/103.png","txt":"晴间多云","time":"08-01"}
     */

    private String code;
    private String msg;
    private ResultBean result;

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

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * tmp : 11°C
         * code : https://cdn.heweather.com/cond_icon/103.png
         * txt : 晴间多云
         * time : 08-01
         */

        @SerializedName("time")
        private String date;
        @SerializedName("code")
        private String icon;
        @SerializedName("txt")
        private String detail;
        @SerializedName("tmp")
        private String temp;

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getDetail() {
            return detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }

        public String getTemp() {
            return temp;
        }

        public void setTemp(String temp) {
            this.temp = temp;
        }
    }
}

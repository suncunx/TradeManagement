package com.trade.other.model;

import java.util.List;

/**
 * Created by Stephen Sun on 2018/5/3 0003.
 * Email:suncunx@qq.com
 */

public class DeliverResultBean {

    /**
     * code : 200
     * msg : 查询成功
     * result : {"delivers":[{"deliverId":"1002","deliverMan":"李勇","deliverPhone":"114","deliverManStatus":"0"}]}
     */

    private int code;
    private String msg;
    private ResultBean result;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
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
        private List<DeliverBean> delivers;

        public List<DeliverBean> getDelivers() {
            return delivers;
        }

        public void setDelivers(List<DeliverBean> delivers) {
            this.delivers = delivers;
        }

        public static class DeliverBean {
            /**
             * deliverId : 1002
             * deliverMan : 李勇
             * deliverPhone : 114
             * deliverManStatus : 0
             */

            private String deliverId;
            private String deliverMan;
            private String deliverPhone;
            private String deliverManStatus;

            public String getDeliverId() {
                return deliverId;
            }

            public void setDeliverId(String deliverId) {
                this.deliverId = deliverId;
            }

            public String getDeliverMan() {
                return deliverMan;
            }

            public void setDeliverMan(String deliverMan) {
                this.deliverMan = deliverMan;
            }

            public String getDeliverPhone() {
                return deliverPhone;
            }

            public void setDeliverPhone(String deliverPhone) {
                this.deliverPhone = deliverPhone;
            }

            public String getDeliverManStatus() {
                return deliverManStatus;
            }

            public void setDeliverManStatus(String deliverManStatus) {
                this.deliverManStatus = deliverManStatus;
            }
        }
    }
}

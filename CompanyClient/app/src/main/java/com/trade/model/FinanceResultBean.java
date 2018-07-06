package com.trade.model;

import java.util.List;

/**
 * Created by Stephen Sun on 2018/4/29 0029.
 * Email:suncunx@qq.com
 */

public class FinanceResultBean {

    /**
     * code : 200
     * msg : 查询成功
     * result : {"finances":[{"inBillPrice":0,"outBillPrice":0,"profit":0,"finances":[{"inBillPrice":0,"outBillPrice":0,"profit":0},{"inBillPrice":0,"outBillPrice":0,"profit":0},{"inBillPrice":0,"outBillPrice":0,"profit":0},{"inBillPrice":0,"outBillPrice":0,"profit":0},{"inBillPrice":0,"outBillPrice":0,"profit":0},{"inBillPrice":0,"outBillPrice":0,"profit":0},{"inBillPrice":0,"outBillPrice":0,"profit":0},{"inBillPrice":0,"outBillPrice":0,"profit":0},{"inBillPrice":0,"outBillPrice":0,"profit":0},{"inBillPrice":0,"outBillPrice":0,"profit":0},{"inBillPrice":0,"outBillPrice":0,"profit":0},{"inBillPrice":0,"outBillPrice":0,"profit":0},{"inBillPrice":0,"outBillPrice":0,"profit":0},{"inBillPrice":0,"outBillPrice":0,"profit":0},{"inBillPrice":0,"outBillPrice":0,"profit":0},{"inBillPrice":0,"outBillPrice":0,"profit":0},{"inBillPrice":0,"outBillPrice":0,"profit":0},{"inBillPrice":0,"outBillPrice":0,"profit":0},{"inBillPrice":0,"outBillPrice":0,"profit":0},{"inBillPrice":0,"outBillPrice":0,"profit":0},{"inBillPrice":0,"outBillPrice":0,"profit":0},{"inBillPrice":0,"outBillPrice":0,"profit":0},{"inBillPrice":0,"outBillPrice":0,"profit":0},{"inBillPrice":0,"outBillPrice":0,"profit":0},{"inBillPrice":0,"outBillPrice":0,"profit":0},{"inBillPrice":0,"outBillPrice":0,"profit":0},{"inBillPrice":0,"outBillPrice":0,"profit":0},{"inBillPrice":0,"outBillPrice":0,"profit":0},{"inBillPrice":0,"outBillPrice":0,"profit":0},{"inBillPrice":0,"outBillPrice":0,"profit":0},{"inBillPrice":0,"outBillPrice":0,"profit":0}]},{"inBillPrice":0,"outBillPrice":0,"profit":0}]}
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
        private List<FinanceMonthBean> finances;

        public List<FinanceMonthBean> getFinances() {
            return finances;
        }

        public void setFinances(List<FinanceMonthBean> finances) {
            this.finances = finances;
        }

        public static class FinanceMonthBean {
            /**
             * inBillPrice : 0.0
             * outBillPrice : 0.0
             * profit : 0.0
             * finances : [{"inBillPrice":0,"outBillPrice":0,"profit":0},{"inBillPrice":0,"outBillPrice":0,"profit":0},{"inBillPrice":0,"outBillPrice":0,"profit":0},{"inBillPrice":0,"outBillPrice":0,"profit":0},{"inBillPrice":0,"outBillPrice":0,"profit":0},{"inBillPrice":0,"outBillPrice":0,"profit":0},{"inBillPrice":0,"outBillPrice":0,"profit":0},{"inBillPrice":0,"outBillPrice":0,"profit":0},{"inBillPrice":0,"outBillPrice":0,"profit":0},{"inBillPrice":0,"outBillPrice":0,"profit":0},{"inBillPrice":0,"outBillPrice":0,"profit":0},{"inBillPrice":0,"outBillPrice":0,"profit":0},{"inBillPrice":0,"outBillPrice":0,"profit":0},{"inBillPrice":0,"outBillPrice":0,"profit":0},{"inBillPrice":0,"outBillPrice":0,"profit":0},{"inBillPrice":0,"outBillPrice":0,"profit":0},{"inBillPrice":0,"outBillPrice":0,"profit":0},{"inBillPrice":0,"outBillPrice":0,"profit":0},{"inBillPrice":0,"outBillPrice":0,"profit":0},{"inBillPrice":0,"outBillPrice":0,"profit":0},{"inBillPrice":0,"outBillPrice":0,"profit":0},{"inBillPrice":0,"outBillPrice":0,"profit":0},{"inBillPrice":0,"outBillPrice":0,"profit":0},{"inBillPrice":0,"outBillPrice":0,"profit":0},{"inBillPrice":0,"outBillPrice":0,"profit":0},{"inBillPrice":0,"outBillPrice":0,"profit":0},{"inBillPrice":0,"outBillPrice":0,"profit":0},{"inBillPrice":0,"outBillPrice":0,"profit":0},{"inBillPrice":0,"outBillPrice":0,"profit":0},{"inBillPrice":0,"outBillPrice":0,"profit":0},{"inBillPrice":0,"outBillPrice":0,"profit":0}]
             */

            private double inBillPrice;
            private double outBillPrice;
            private double profit;
            private List<FinanceBean> finances;

            public double getInBillPrice() {
                return inBillPrice;
            }

            public void setInBillPrice(double inBillPrice) {
                this.inBillPrice = inBillPrice;
            }

            public double getOutBillPrice() {
                return outBillPrice;
            }

            public void setOutBillPrice(double outBillPrice) {
                this.outBillPrice = outBillPrice;
            }

            public double getProfit() {
                return profit;
            }

            public void setProfit(double profit) {
                this.profit = profit;
            }

            public List<FinanceBean> getFinances() {
                return finances;
            }

            public void setFinances(List<FinanceBean> finances) {
                this.finances = finances;
            }

            public static class FinanceBean {
                /**
                 * inBillPrice : 0.0
                 * outBillPrice : 0.0
                 * profit : 0.0
                 */

                private double inBillPrice;
                private double outBillPrice;
                private double profit;

                public double getInBillPrice() {
                    return inBillPrice;
                }

                public void setInBillPrice(double inBillPrice) {
                    this.inBillPrice = inBillPrice;
                }

                public double getOutBillPrice() {
                    return outBillPrice;
                }

                public void setOutBillPrice(double outBillPrice) {
                    this.outBillPrice = outBillPrice;
                }

                public double getProfit() {
                    return profit;
                }

                public void setProfit(double profit) {
                    this.profit = profit;
                }
            }
        }
    }
}

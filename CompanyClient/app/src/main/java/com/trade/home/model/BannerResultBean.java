package com.trade.home.model;

import java.util.List;

/**
 * Created by Stephen Sun on 2017/8/18 0018.
 * Email:suncunx@qq.com
 */

public class BannerResultBean {

    /**
     * code : 200
     * msg : 查询成功
     * result : [{"id":237,"title":"五官里面，眼睛和鼻子，哪个对颜值的影响更大？","picture":"http://07.imgmini.eastday.com/mobile/20170815/20170815134552_d41d8cd98f00b204e9800998ecf8427e_6_mwpm_03200403.jpg","url":"http://106.15.199.8/jraz/admin/news/test/id/237"},{"id":212,"title":"警惕！二手车会让年轻人的死亡率暴涨300%！","picture":"http://cdn36.jinriaozhou.com/2017/ad/20170813/95b46c186d976f7d5041cb32f9950ffe.jpg","url":"http://106.15.199.8/jraz/admin/news/test/id/212"},{"id":213,"title":"外国人眼中的各类中国人 看完竟无力反驳！","picture":"http://cdn36.jinriaozhou.com/2017/ad/20170813/f78be902fad89698fb0306a1d3c33add.jpg","url":"http://106.15.199.8/jraz/admin/news/test/id/213"},{"id":209,"title":"惊！亚裔小哥火车上公然\u201c打飞机\u201d 全程跟拍！","picture":"http://cdn36.jinriaozhou.com/2017/ad/20170814/425b3677e45160c4a2eb25286df99db1.jpg","url":"http://106.15.199.8/jraz/admin/news/test/id/209"},{"id":211,"title":"中国人还是澳洲人？矛盾的华人移民第二代！","picture":"http://cdn36.jinriaozhou.com/2017/ad/20170813/efd2d2bbe19bf65b70ef21a0bb03cd42.jpg","url":"http://106.15.199.8/jraz/admin/news/test/id/211"}]
     */

    private String code;
    private String msg;
    private List<ResultBean> result;

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

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * id : 237
         * title : 五官里面，眼睛和鼻子，哪个对颜值的影响更大？
         * picture : http://07.imgmini.eastday.com/mobile/20170815/20170815134552_d41d8cd98f00b204e9800998ecf8427e_6_mwpm_03200403.jpg
         * url : http://106.15.199.8/jraz/admin/news/test/id/237
         */

        private int id;
        private String title;
        private String picture;
        private String url;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getPicture() {
            return picture;
        }

        public void setPicture(String picture) {
            this.picture = picture;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}

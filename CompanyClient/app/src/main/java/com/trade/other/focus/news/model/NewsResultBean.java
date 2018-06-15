package com.trade.other.focus.news.model;

import java.util.List;

/**
 * Created by Stephen Sun on 2017/8/16 0016.
 * Email:suncunx@qq.com
 */

public class NewsResultBean {

    /**
     * status : ok
     * totalResults : 20
     * articles : [{"source":{"id":null,"name":"Nmgnews.com.cn"},"author":null,"title":"高水平自主开放推动高质量发展","description":"最近，中国主动扩大开放的一系列重磅举措加快落地，赢得国内外广泛关注的同时，也将有力推动中国经济加速迈向高质量发展。\n\r　　刚刚进入5月，瑞银、益博睿、野村证券先后向中国发出申请，希望参与中国金融业的开放。作为这轮开放的重头戏，金融领域动作连连，中国正在用实际行动展示新时代扩大开放的决心和力...","url":"http://china.nmgnews.com.cn/system/2018/05/11/012491559.shtml","urlToImage":null,"publishedAt":"2018-05-11T16:51:59Z"},{"source":{"id":null,"name":"Sina.com.cn"},"author":"中国新闻网","title":"小马智行人工智能研究院在广州南沙揭牌","description":"小马智行人工智能研究院在广州南沙揭牌","url":"http://finance.sina.com.cn/roll/2018-05-12/doc-ihamfahw7022103.shtml","urlToImage":"http://n.sinaimg.cn/default/transform/116/w550h366/20180418/7RAA-fzihnep5208659.png","publishedAt":"2018-05-11T16:22:00Z"},{"source":{"id":"xinhua-net","name":"Xinhua Net"},"author":null,"title":"李克强参观丰田汽车北海道厂区","description":"李克强参观丰田汽车北海道厂区\r\n---国务院总理李克强当地时间5月11日上午在日本首相安倍晋三陪同下，来到位于苫小牧市的丰田汽车北海道厂区参观考察。","url":"http://www.xinhuanet.com/politics/2018-05/11/c_1122820696.htm","urlToImage":null,"publishedAt":"2018-05-11T12:43:06Z"},{"source":{"id":null,"name":"Chinanews.com"},"author":"chinanews","title":"习近平主持召开中央全面深化改革委员会第二次会议","description":"会议指出，加强国有企业资产负债约束，是落实党的十九大精神，推动国有企业降杠杆、防范化解国有企业债务风险的重要举措。","url":"http://www.chinanews.com/gn/2018/05-11/8511853.shtml","urlToImage":null,"publishedAt":"2018-05-11T11:39:57Z"},{"source":{"id":null,"name":"People.com.cn"},"author":"L_103978","title":"西城国际金融体育康乐节开幕太极拳打出\u201c精气神儿\u201d","description":"人民网北京5月11日电今天上午，由西城区金融街街道办事处、西城区体育局主办，西城区金融街创新发展服务中心承办的\u201c北京市西城国际金融体育康乐节开幕暨2018年金融街太极拳展演\u201d在西城区金融街购物中心广场","url":"http://bj.people.com.cn/n2/2018/0511/c337122-31567571.html","urlToImage":null,"publishedAt":"2018-05-11T09:27:53Z"},{"source":{"id":null,"name":"People.com.cn"},"author":"L_104278","title":"\u201c北京书市\u201d开张迎客民众游园淘书两不误","description":"5月11日，2018\u201c北京书市\u201d在朝阳公园开张迎客。图为多位读者在主旋律精品图书展示展销区挑选图书。（完）　尹力　摄　　中新网北京5月11日电(记者尹力)一年一度的\u201c北京书市\u201d于11日开张迎客，集","url":"http://bj.people.com.cn/n2/2018/0511/c82846-31568210.html","urlToImage":null,"publishedAt":"2018-05-11T09:19:00Z"}]
     */

    private String status;
    private int totalResults;
    private List<ArticleBean> articles;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public List<ArticleBean> getArticles() {
        return articles;
    }

    public void setArticles(List<ArticleBean> articles) {
        this.articles = articles;
    }

    public static class ArticleBean {
        /**
         * source : {"id":null,"name":"Nmgnews.com.cn"}
         * author : null
         * title : 高水平自主开放推动高质量发展
         * description : 最近，中国主动扩大开放的一系列重磅举措加快落地，赢得国内外广泛关注的同时，也将有力推动中国经济加速迈向高质量发展。
         　　刚刚进入5月，瑞银、益博睿、野村证券先后向中国发出申请，希望参与中国金融业的开放。作为这轮开放的重头戏，金融领域动作连连，中国正在用实际行动展示新时代扩大开放的决心和力...
         * url : http://china.nmgnews.com.cn/system/2018/05/11/012491559.shtml
         * urlToImage : null
         * publishedAt : 2018-05-11T16:51:59Z
         */

        private SourceBean source;
        private Object author;
        private String title;
        private String description;
        private String url;
        private String urlToImage;
        private String publishedAt;

        public SourceBean getSource() {
            return source;
        }

        public void setSource(SourceBean source) {
            this.source = source;
        }

        public Object getAuthor() {
            return author;
        }

        public void setAuthor(Object author) {
            this.author = author;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getUrlToImage() {
            return urlToImage;
        }

        public void setUrlToImage(String urlToImage) {
            this.urlToImage = urlToImage;
        }

        public String getPublishedAt() {
            return publishedAt;
        }

        public void setPublishedAt(String publishedAt) {
            this.publishedAt = publishedAt;
        }

        public static class SourceBean {
            /**
             * id : null
             * name : Nmgnews.com.cn
             */

            private Object id;
            private String name;

            public Object getId() {
                return id;
            }

            public void setId(Object id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }
}

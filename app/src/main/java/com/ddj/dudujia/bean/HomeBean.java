package com.ddj.dudujia.bean;

import java.util.List;

/**
 * Created by hanshaobo on 11/01/2018.
 */

public class HomeBean {
    /**
     * data : {"mainTitle":"值得信赖的二手车检测专家","submainTitle":"TRUSTWORTHY USED CAR DETECTION EXPERTS","news":[{"newid":"10000","title":"三天掌握精华，AIS二手车检测培\n训（深圳站）开讲啦！","heat":"16","updatetime":"2017-11-09 11:19:07","image":"news_10000.png"},{"newid":"10001","title":"2018年进口 曝新奥迪A8L国内无伪装谍照","heat":"9","updatetime":"2017-11-13 08:13:53","image":"news_10001.png"},{"newid":"10002","title":"出了这些交通事故千万别私了，吃了哑巴亏都没地说","heat":"7","updatetime":"2017-11-13 11:44:39","image":"news_10002.png"},{"newid":"10003","title":"你的车在榜单内吗？8月汽车销售量Top20","heat":"9","updatetime":"2017-11-13 08:13:53","image":"news_10003.png"}]}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * mainTitle : 值得信赖的二手车检测专家
         * submainTitle : TRUSTWORTHY USED CAR DETECTION EXPERTS
         * news : [{"newid":"10000","title":"三天掌握精华，AIS二手车检测培\n训（深圳站）开讲啦！","heat":"16","updatetime":"2017-11-09 11:19:07","image":"news_10000.png"},{"newid":"10001","title":"2018年进口 曝新奥迪A8L国内无伪装谍照","heat":"9","updatetime":"2017-11-13 08:13:53","image":"news_10001.png"},{"newid":"10002","title":"出了这些交通事故千万别私了，吃了哑巴亏都没地说","heat":"7","updatetime":"2017-11-13 11:44:39","image":"news_10002.png"},{"newid":"10003","title":"你的车在榜单内吗？8月汽车销售量Top20","heat":"9","updatetime":"2017-11-13 08:13:53","image":"news_10003.png"}]
         */

        private String mainTitle;
        private String submainTitle;
        private List<NewsBean> news;

        public String getMainTitle() {
            return mainTitle;
        }

        public void setMainTitle(String mainTitle) {
            this.mainTitle = mainTitle;
        }

        public String getSubmainTitle() {
            return submainTitle;
        }

        public void setSubmainTitle(String submainTitle) {
            this.submainTitle = submainTitle;
        }

        public List<NewsBean> getNews() {
            return news;
        }

        public void setNews(List<NewsBean> news) {
            this.news = news;
        }

        public static class NewsBean {
            /**
             * newid : 10000
             * title : 三天掌握精华，AIS二手车检测培
             * 训（深圳站）开讲啦！
             * heat : 16
             * updatetime : 2017-11-09 11:19:07
             * image : news_10000.png
             */

            private String newid;
            private String title;
            private String heat;
            private String updatetime;
            private String image;
            private String url;

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getNewid() {
                return newid;
            }

            public void setNewid(String newid) {
                this.newid = newid;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getHeat() {
                return heat;
            }

            public void setHeat(String heat) {
                this.heat = heat;
            }

            public String getUpdatetime() {
                return updatetime;
            }

            public void setUpdatetime(String updatetime) {
                this.updatetime = updatetime;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }
        }
    }
}


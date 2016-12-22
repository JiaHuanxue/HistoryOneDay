package com.result.homepage.bean;

import java.util.List;

/**
 * Created by 贾焕雪 on 2016-12-19.
 */
public class DetailsBean {


    /**
     * reason : success
     * result : [{"e_id":"14071","title":"金大中当选韩国第十五届总统","content":"    在19年前的今天，1997年12月19日 (农历冬月二十)，金大中当选韩国第十五届总统。\r\n    1997年12月19日，韩国中央选举管理委员会正式公布总统选举结果，新政治国民会议的总统候选人金大中以40.4%的得票率，战胜大国家党候选人李会昌和国民新党候选人李仁济，当选韩国第15届总统。金大中1925年12月出生于韩国全罗南道。60年代起他开始从政，曾6次当选国会议员，3次竞选总统均遭失败。在朴正熙、全斗焕执政时期，金大中曾遭绑架、软禁并被捕入狱。1995年，他重返政坛，创建新政治国民会议，任总裁，并于1997年再度竞选总统。\r\n\r\n","picNo":"1","picUrl":[{"pic_title":"金大中在支持者中间","id":1,"url":"http://images.juheapi.com/history/14071_1.jpg"}]}]
     * error_code : 0
     */

    private String reason;
    private int error_code;
    private List<ResultBean> result;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * e_id : 14071
         * title : 金大中当选韩国第十五届总统
         * content :     在19年前的今天，1997年12月19日 (农历冬月二十)，金大中当选韩国第十五届总统。
         1997年12月19日，韩国中央选举管理委员会正式公布总统选举结果，新政治国民会议的总统候选人金大中以40.4%的得票率，战胜大国家党候选人李会昌和国民新党候选人李仁济，当选韩国第15届总统。金大中1925年12月出生于韩国全罗南道。60年代起他开始从政，曾6次当选国会议员，3次竞选总统均遭失败。在朴正熙、全斗焕执政时期，金大中曾遭绑架、软禁并被捕入狱。1995年，他重返政坛，创建新政治国民会议，任总裁，并于1997年再度竞选总统。


         * picNo : 1
         * picUrl : [{"pic_title":"金大中在支持者中间","id":1,"url":"http://images.juheapi.com/history/14071_1.jpg"}]
         */

        private String e_id;
        private String title;
        private String content;
        private String picNo;
        private List<PicUrlBean> picUrl;

        public ResultBean(String title, String content, List<PicUrlBean> picUrl) {
            this.title = title;
            this.content = content;
            this.picUrl = picUrl;
        }

        public String getE_id() {
            return e_id;
        }

        public void setE_id(String e_id) {
            this.e_id = e_id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getPicNo() {
            return picNo;
        }

        public void setPicNo(String picNo) {
            this.picNo = picNo;
        }

        public List<PicUrlBean> getPicUrl() {
            return picUrl;
        }

        public void setPicUrl(List<PicUrlBean> picUrl) {
            this.picUrl = picUrl;
        }

        public static class PicUrlBean {
            /**
             * pic_title : 金大中在支持者中间
             * id : 1
             * url : http://images.juheapi.com/history/14071_1.jpg
             */

            private String pic_title;
            private int id;
            private String url;

            public String getPic_title() {
                return pic_title;
            }

            public void setPic_title(String pic_title) {
                this.pic_title = pic_title;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }
    }

}

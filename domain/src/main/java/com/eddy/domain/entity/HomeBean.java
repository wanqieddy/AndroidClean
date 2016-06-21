package com.eddy.domain.entity;

import java.util.List;

/**
 * Created by wanqi on 16-6-20.
 */
public class HomeBean {


    /**
     * statusCode : 0
     * message : 成功
     * data : {"item":{"name":"银票宝 第5967期","rates":"6.30","surplus":49677,"cycle":"37","delId":"2014_aeGd1Zw"},"url":{"ltemList":["/item/drafts/","/item/ticket/"]}}
     */



    private int statusCode;
    private String message;
    /**
     * item : {"name":"银票宝 第5967期","rates":"6.30","surplus":49677,"cycle":"37","delId":"2014_aeGd1Zw"}
     * url : {"ltemList":["/item/drafts/","/item/ticket/"]}
     */

    private DataBean data;


    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * name : 银票宝 第5967期
         * rates : 6.30
         * surplus : 49677
         * cycle : 37
         * delId : 2014_aeGd1Zw
         */

        private ItemBean item;
        private UrlBean url;

        public ItemBean getItem() {
            return item;
        }

        public void setItem(ItemBean item) {
            this.item = item;
        }

        public UrlBean getUrl() {
            return url;
        }

        public void setUrl(UrlBean url) {
            this.url = url;
        }

        public static class ItemBean {
            private String name;
            private String rates;
            private int surplus;
            private String cycle;
            private String delId;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getRates() {
                return rates;
            }

            public void setRates(String rates) {
                this.rates = rates;
            }

            public int getSurplus() {
                return surplus;
            }

            public void setSurplus(int surplus) {
                this.surplus = surplus;
            }

            public String getCycle() {
                return cycle;
            }

            public void setCycle(String cycle) {
                this.cycle = cycle;
            }

            public String getDelId() {
                return delId;
            }

            public void setDelId(String delId) {
                this.delId = delId;
            }
        }

        public static class UrlBean {
            private List<String> ltemList;

            public List<String> getLtemList() {
                return ltemList;
            }

            public void setLtemList(List<String> ltemList) {
                this.ltemList = ltemList;
            }
        }
    }


    @Override
    public String toString() {
        return "HomeBean{" +
                "data=" + data +
                ", statusCode=" + statusCode +
                ", message='" + message + '\'' +
                '}';
    }
}

package com.example.administrator.yipen.bean;

import java.util.List;

public class BannerDataBean {

    @Override
    public String toString() {
        return "BannerDataBean{" +
                "status=" + status +
                ", message='" + message + '\'' +
                ", result=" + result +
                '}';
    }

    /**
     * status : 1
     * message : banner获取成功
     * result : [{"truename":null,"bis_id":1,"image":"https://yp.dxshuju.com/img/20180902\\9629b88822b10266da941d36a215cde9.jpg","rout_ios":null,"rout_android":null},{"truename":null,"bis_id":1,"image":"https://yp.dxshuju.com/img/20180902\\9629b88822b10266da941d36a215cde9.jpg","rout_ios":null,"rout_android":null},{"truename":null,"bis_id":1,"image":"https://yp.dxshuju.com/img/20180902\\9629b88822b10266da941d36a215cde9.jpg","rout_ios":null,"rout_android":null},{"truename":null,"bis_id":1,"image":"https://yp.dxshuju.com/img/20180902\\3d8644097eace43a8d1c36a151657baf.jpg","rout_ios":"www.ios.com","rout_android":"www.android.com"}]
     */

    private int status;
    private String message;
    private List<ResultBean> result;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        @Override
        public String toString() {
            return "ResultBean{" +
                    "truename=" + truename +
                    ", bis_id=" + bis_id +
                    ", image='" + image + '\'' +
                    ", rout_ios=" + rout_ios +
                    ", rout_android=" + rout_android +
                    '}';
        }

        /**
         * truename : null
         * bis_id : 1
         * image : https://yp.dxshuju.com/img/20180902\9629b88822b10266da941d36a215cde9.jpg
         * rout_ios : null
         * rout_android : null
         */

        private Object truename;
        private int bis_id;
        private String image;
        private Object rout_ios;
        private Object rout_android;

        public Object getTruename() {
            return truename;
        }

        public void setTruename(Object truename) {
            this.truename = truename;
        }

        public int getBis_id() {
            return bis_id;
        }

        public void setBis_id(int bis_id) {
            this.bis_id = bis_id;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public Object getRout_ios() {
            return rout_ios;
        }

        public void setRout_ios(Object rout_ios) {
            this.rout_ios = rout_ios;
        }

        public Object getRout_android() {
            return rout_android;
        }

        public void setRout_android(Object rout_android) {
            this.rout_android = rout_android;
        }
    }
}

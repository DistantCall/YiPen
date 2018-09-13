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
     * result : [{"truename":"用户名","bis_id":"商户id","image":"图片路径","rout_ios":"ios链接地址","rout_android":"Android链接地址"}]
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
                    "truename='" + truename + '\'' +
                    ", bis_id='" + bis_id + '\'' +
                    ", image='" + image + '\'' +
                    ", rout_ios='" + rout_ios + '\'' +
                    ", rout_android='" + rout_android + '\'' +
                    '}';
        }

        /**
         * truename : 用户名
         * bis_id : 商户id
         * image : 图片路径
         * rout_ios : ios链接地址
         * rout_android : Android链接地址
         */

        private String truename;
        private String bis_id;
        private String image;
        private String rout_ios;
        private String rout_android;

        public String getTruename() {
            return truename;
        }

        public void setTruename(String truename) {
            this.truename = truename;
        }

        public String getBis_id() {
            return bis_id;
        }

        public void setBis_id(String bis_id) {
            this.bis_id = bis_id;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getRout_ios() {
            return rout_ios;
        }

        public void setRout_ios(String rout_ios) {
            this.rout_ios = rout_ios;
        }

        public String getRout_android() {
            return rout_android;
        }

        public void setRout_android(String rout_android) {
            this.rout_android = rout_android;
        }
    }
}

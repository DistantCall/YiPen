package com.example.administrator.yipen.bean;

import java.util.List;

public class SelectInfo {

    @Override
    public String toString() {
        return "SelectInfo{" +
                "status=" + status +
                ", message='" + message + '\'' +
                ", result=" + result +
                '}';
    }

    /**
     * status : 1
     * message : 用户信息查询成功
     * result : [{"truename":"用户名","address":"住址","assessment":"所需缴费金额"}]
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
                    ", address='" + address + '\'' +
                    ", assessment='" + assessment + '\'' +
                    '}';
        }

        /**
         * truename : 用户名
         * address : 住址
         * assessment : 所需缴费金额
         */

        private String truename;
        private String address;
        private String assessment;

        public String getTruename() {
            return truename;
        }

        public void setTruename(String truename) {
            this.truename = truename;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getAssessment() {
            return assessment;
        }

        public void setAssessment(String assessment) {
            this.assessment = assessment;
        }
    }
}

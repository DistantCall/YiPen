package com.example.administrator.yipen.bean;

import android.widget.Spinner;

import java.util.List;

public class StagesBean {

    @Override
    public String toString() {
        Spinner
        return "StagesBean{" +
                "status=" + status +
                ", message='" + message + '\'' +
                ", res=" + res +
                '}';
    }

    /**
     * status : 1
     * message : 分期信息查询成功
     * res : [{"general_finance":"申请总金额","apply_number":"申请期数","interest_rate":"利率","telephone":"手机号(用户新添加的手机号)"}]
     */

    private int status;
    private String message;
    private List<ResBean> res;

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

    public List<ResBean> getRes() {
        return res;
    }

    public void setRes(List<ResBean> res) {
        this.res = res;
    }

    public static class ResBean {
        @Override
        public String toString() {
            return "ResBean{" +
                    "general_finance='" + general_finance + '\'' +
                    ", apply_number='" + apply_number + '\'' +
                    ", interest_rate='" + interest_rate + '\'' +
                    ", telephone='" + telephone + '\'' +
                    '}';
        }

        /**
         * general_finance : 申请总金额
         * apply_number : 申请期数
         * interest_rate : 利率
         * telephone : 手机号(用户新添加的手机号)
         */

        private String general_finance;
        private String apply_number;
        private String interest_rate;
        private String telephone;

        public String getGeneral_finance() {
            return general_finance;
        }

        public void setGeneral_finance(String general_finance) {
            this.general_finance = general_finance;
        }

        public String getApply_number() {
            return apply_number;
        }

        public void setApply_number(String apply_number) {
            this.apply_number = apply_number;
        }

        public String getInterest_rate() {
            return interest_rate;
        }

        public void setInterest_rate(String interest_rate) {
            this.interest_rate = interest_rate;
        }

        public String getTelephone() {
            return telephone;
        }

        public void setTelephone(String telephone) {
            this.telephone = telephone;
        }
    }
}

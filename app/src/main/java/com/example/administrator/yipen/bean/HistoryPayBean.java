package com.example.administrator.yipen.bean;

import java.util.List;

public class HistoryPayBean {

    /**
     * status : 1
     * message : 历史欠费信息以查询成功
     * res : [{"truename":"芳芳","area_name":"长丰元1区","floor":1,"unit":"2","room":"2309","area":"98","assessment":"100.00"},{"truename":"芳芳","area_name":"波普中心","floor":312,"unit":"321","room":"11","area":"123","assessment":"30052.30"}]
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
        /**
         * truename : 芳芳
         * area_name : 长丰元1区
         * floor : 1
         * unit : 2
         * room : 2309
         * area : 98
         * assessment : 100.00
         */

        private String truename;
        private String area_name;
        private int floor;
        private String unit;
        private String room;
        private String area;
        private String assessment;

        public String getTruename() {
            return truename;
        }

        public void setTruename(String truename) {
            this.truename = truename;
        }

        public String getArea_name() {
            return area_name;
        }

        public void setArea_name(String area_name) {
            this.area_name = area_name;
        }

        public int getFloor() {
            return floor;
        }

        public void setFloor(int floor) {
            this.floor = floor;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public String getRoom() {
            return room;
        }

        public void setRoom(String room) {
            this.room = room;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public String getAssessment() {
            return assessment;
        }

        public void setAssessment(String assessment) {
            this.assessment = assessment;
        }
    }
}

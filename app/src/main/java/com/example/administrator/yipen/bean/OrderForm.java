package com.example.administrator.yipen.bean;

import java.util.List;

public class OrderForm {

    @Override
    public String toString() {
        return "OrderForm{" +
                "status=" + status +
                ", message='" + message + '\'' +
                ", result=" + result +
                '}';
    }

    /**
     * status : 1
     * message : 订单获取成功
     * result : [{"order_id":"订单表id","order_no":"订单号","truename":"用户名","area_name":"小区名","floor":"几号楼","unit":"几单元","room":"多少房间","area":"多少平米","assessment":"价格","create_time":"开始时间","update_time":"结束时间"},{"order_id":"订单表id","order_no":"订单号","truename":"用户名","area_name":"小区名","floor":"几号楼","unit":"几单元","room":"多少房间","area":"多少平米","assessment":"价格","create_time":"开始时间","update_time":"结束时间"}]
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
                    "order_id='" + order_id + '\'' +
                    ", order_no='" + order_no + '\'' +
                    ", truename='" + truename + '\'' +
                    ", area_name='" + area_name + '\'' +
                    ", floor='" + floor + '\'' +
                    ", unit='" + unit + '\'' +
                    ", room='" + room + '\'' +
                    ", area='" + area + '\'' +
                    ", assessment='" + assessment + '\'' +
                    ", create_time='" + create_time + '\'' +
                    ", update_time='" + update_time + '\'' +
                    '}';
        }

        /**
         * order_id : 订单表id
         * order_no : 订单号
         * truename : 用户名
         * area_name : 小区名
         * floor : 几号楼
         * unit : 几单元
         * room : 多少房间
         * area : 多少平米
         * assessment : 价格
         * create_time : 开始时间
         * update_time : 结束时间
         */

        private String order_id;
        private String order_no;
        private String truename;
        private String area_name;
        private String floor;
        private String unit;
        private String room;
        private String area;
        private String assessment;
        private String create_time;
        private String update_time;

        public String getOrder_id() {
            return order_id;
        }

        public void setOrder_id(String order_id) {
            this.order_id = order_id;
        }

        public String getOrder_no() {
            return order_no;
        }

        public void setOrder_no(String order_no) {
            this.order_no = order_no;
        }

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

        public String getFloor() {
            return floor;
        }

        public void setFloor(String floor) {
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

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getUpdate_time() {
            return update_time;
        }

        public void setUpdate_time(String update_time) {
            this.update_time = update_time;
        }
    }
}

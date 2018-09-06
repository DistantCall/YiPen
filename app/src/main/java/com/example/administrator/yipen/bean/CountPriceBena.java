package com.example.administrator.yipen.bean;

public class CountPriceBena {

    @Override
    public String toString() {
        return "CountPriceBena{" +
                "status=" + status +
                ", message='" + message + '\'' +
                ", result='" + result + '\'' +
                '}';
    }

    /**
     * status : 1
     * message : 用户信息查询成功
     * result : 共缴金额总和
     */

    private int status;
    private String message;
    private String result;

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

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}

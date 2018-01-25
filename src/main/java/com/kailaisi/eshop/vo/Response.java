package com.kailaisi.eshop.vo;

public class Response {
    public static final String SUCCESS="success";
    public static final String FAILURE="failure";
    private String status;
    private String msg;

    public Response() {
    }

    public Response(String status) {
        this.status = status;
    }

    public Response(String status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}

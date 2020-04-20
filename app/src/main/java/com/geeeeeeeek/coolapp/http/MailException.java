package com.geeeeeeeek.coolapp.http;

public class MailException extends RuntimeException {

    public String status;
    public String msg;


    public MailException(String status, String msg) {
        this.status = status;
        this.msg = msg;
    }
}

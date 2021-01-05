package com.sya.view;

public class Error {
    private Integer errorCode;
    private String message;

    public Error(Integer errorCode,String message){
        this.errorCode=errorCode;
        this.message=message;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

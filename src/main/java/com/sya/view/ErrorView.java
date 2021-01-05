package com.sya.view;

public class ErrorView {
    private Integer errorCode;
    private String message;

    public ErrorView(Integer errorCode, String message){
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

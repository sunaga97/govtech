package com.example.demo.message;

public class ResponseMessage {
    private String error;

    public ResponseMessage(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}


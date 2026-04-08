package com.gl.app.utility;

import java.time.LocalDateTime;
import java.util.List;

public class Error {
    private List<String> message;

    private String statusCode;

    private LocalDateTime timeStamp;

    public Error(){}

    public Error(List<String> message, String statusCode, LocalDateTime timeStamp) {
        this.message = message;
        this.statusCode = statusCode;
        this.timeStamp = timeStamp;
    }

    public List<String> getMessage() {
        return message;
    }

    public void setMessage(List<String> message) {
        this.message = message;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }
}

package com.guerlak.challenge01.usersapi.resources.exceptions;

import java.io.Serializable;
import java.time.Instant;

public class StandardError implements Serializable {

    private Instant timeStamp;
    private Integer code;
    private String error;
    private String message;
    private String path;

    public StandardError() {
    }

    public StandardError(Instant timeStamp, Integer code, String error, String message, String path) {
        this.timeStamp = timeStamp;
        this.code = code;
        this.error = error;
        this.message = message;
        this.path = path;
    }

    public Instant getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Instant timeStamp) {
        this.timeStamp = timeStamp;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}



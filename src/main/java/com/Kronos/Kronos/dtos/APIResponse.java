package com.Kronos.Kronos.dtos;

public class APIResponse<T> {

    private T data;
    private String message;

    public APIResponse(T data, String message) {
        this.data = data;
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }

}

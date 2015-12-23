package com.mobile.api;

/**
 * Created by wangyao5 on 15/12/21.
 */
public class Result<T> {
    private int status;
    private T data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}

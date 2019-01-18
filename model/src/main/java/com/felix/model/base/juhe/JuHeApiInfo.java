package com.felix.model.base.juhe;

/**
 * Created by chaichuanfa on 2019/1/17
 */
public class JuHeApiInfo<T> {

    private String reason;

    private int error_code;

    private T result;

    public JuHeApiInfo(String reason, int error_code, T result) {
        this.reason = reason;
        this.error_code = error_code;
        this.result = result;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}

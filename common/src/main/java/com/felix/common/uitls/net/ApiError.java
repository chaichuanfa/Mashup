package com.felix.common.uitls.net;

/**
 * Created by chaichuanfa on 2019/1/18
 */
public class ApiError extends RuntimeException {

    private final String resultcode;

    private final String reason;

    private final int error_code;

    public ApiError(String resultcode, String reason, int error_code) {
        super("api error, resultcode: " + resultcode + ", reason: " + reason + ", error_code: "
                + error_code);
        this.resultcode = resultcode;
        this.reason = reason;
        this.error_code = error_code;
    }

    public String getResultcode() {
        return resultcode;
    }

    public String getReason() {
        return reason;
    }

    public int getErrorCode() {
        return error_code;
    }

    public boolean isApiError() {
        return error_code > 0;
    }

    @Override
    public String toString() {
        return "resultcode: " + resultcode + ", reason: " + reason + ", error_code: " + error_code;
    }
}

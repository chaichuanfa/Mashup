package com.felix.common.uitls.net;


import retrofit2.HttpException;

/**
 * Created by chaichuanfa on 2019/1/18
 */
public final class RetrofitUtils {

    private RetrofitUtils() {
        //no instance
    }

    public static int getErrorCode(Throwable throwable) {
        if (throwable instanceof ApiError) {
            return ((ApiError) throwable).getErrorCode();
        } else if (throwable instanceof HttpException) {
            return ((HttpException) throwable).code();
        }

        return -1;
    }

    public static String getErrorMessage(Throwable throwable) {
        if (throwable instanceof ApiError) {
            return ((ApiError) throwable).getReason();
        } else if (throwable instanceof HttpException) {
            return ((HttpException) throwable).message();
        }
        return "";
    }
}

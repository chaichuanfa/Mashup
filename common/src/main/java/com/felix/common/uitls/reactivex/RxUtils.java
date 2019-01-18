package com.felix.common.uitls.reactivex;

import com.felix.common.uitls.net.ApiError;
import com.felix.common.uitls.net.ApiErrorProcessor;

import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import timber.log.Timber;

/**
 * Created by chaichuanfa on 2019/1/17
 */
public final class RxUtils {

    private RxUtils() {
    }

    public static <T> Consumer<T> idleConsumer() {
        return t -> {

        };
    }

    public static Action idleAction() {
        return () -> {

        };
    }

    public static final Consumer<Throwable> NetErrorProcessor = throwable -> {
        if (throwable instanceof ApiError) {
            ApiErrorProcessor.process((ApiError) throwable);
        } else {
            ApiErrorProcessor.processOtherException(throwable);
        }
    };

    public static final Consumer<Throwable> IgnoreErrorProcessor = throwable -> {
        Timber.e(throwable, "RxUtils.IgnoreErrorProcessor");
    };
}

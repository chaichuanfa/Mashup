package com.felix.common.uitls.net;


import com.felix.common.R;
import com.felix.common.uitls.ui.ToastUtils;

import org.greenrobot.eventbus.EventBus;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;

import java.net.ConnectException;
import java.net.UnknownHostException;

import retrofit2.HttpException;
import timber.log.Timber;

/**
 * Created by chaichuanfa on 2019/1/18
 */
public final class ApiErrorProcessor {

    private static final int UNAUTHORIZED = 401;

    private static final int FORBIDDEN = 403;

    private static final int NOT_FOUND = 404;

    private static final int REQUEST_TIMEOUT = 408;

    private static final int INTERNAL_SERVER_ERROR = 500;

    private static final int BAD_GATEWAY = 502;

    private static final int SERVICE_UNAVAILABLE = 503;

    private static final int GATEWAY_TIMEOUT = 504;

    private static Context sContext = null;

    private static EventBus sBus = null;

    private static Handler mMainThreadHandler = null;

    public static void init(Context context, EventBus bus) {
        sContext = context;
        sBus = bus;
        mMainThreadHandler = new Handler(Looper.getMainLooper());
    }

    private ApiErrorProcessor() {
        //no instance
    }

    public static void process(final ApiError apiError) {
        if (apiError.getErrorCode() > 0) {
            String errorStrResName = "error_" + apiError.getErrorCode();
            final int resourceId = sContext.getResources()
                    .getIdentifier(errorStrResName, "string",
                            sContext.getPackageName());
            if (resourceId > 0) {
                mMainThreadHandler.post(() -> ToastUtils.toastError(resourceId));
            } else {
                if (!TextUtils.isEmpty(apiError.getReason())) {
                    mMainThreadHandler.post(() -> ToastUtils.toastError(apiError.getReason()));
                } else {
                    mMainThreadHandler.post(() -> ToastUtils.toastError(R.string.error_default));
                }
            }
        }
    }

    public static void processOtherException(Throwable throwable) {
        if (throwable instanceof HttpException) {
            HttpException httpException = (HttpException) throwable;
            switch (httpException.code()) {
                case UNAUTHORIZED:
                case FORBIDDEN:
                case NOT_FOUND:
                case REQUEST_TIMEOUT:
                case GATEWAY_TIMEOUT:
                case INTERNAL_SERVER_ERROR:
                case BAD_GATEWAY:
                case SERVICE_UNAVAILABLE:
                    mMainThreadHandler.post(() -> ToastUtils.toastError(R.string.network_error));
                    break;
            }
        } else if (throwable instanceof UnknownHostException) {
            sBus.post(new NoNetworkEvent());
        } else if (throwable instanceof ConnectException) {
            sBus.post(new NoNetworkEvent());
        } else {
            Timber.e(throwable, "RxUtils.NetErrorProcessor");
        }
    }
}

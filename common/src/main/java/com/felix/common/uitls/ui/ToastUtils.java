package com.felix.common.uitls.ui;

import com.felix.atoast.library.AToast;
import com.felix.atoast.library.config.AToastConfig;
import com.felix.common.R;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.text.TextUtils;

/**
 * Created by chaichuanfa on 2019/1/18
 */
public final class ToastUtils {

    public static void setContext(Context context) {
        AToast.onInit(context,
                new AToastConfig.Builder()
                        .info_color(context.getResources().getColor(R.color.toast_info_bg))
                        .error_color(context.getResources().getColor(R.color.toast_error_bg))
                        .success_color(context.getResources().getColor(R.color.toast_success_bg))
                        .warning_color(context.getResources().getColor(R.color.toast_waring_bg))
                        .normal_color(context.getResources().getColor(R.color.toast_normal_bg))
                        .build());
    }

    private ToastUtils() {
        //no instance
    }

    public static void toastInfo(@StringRes int stringRId) {
        AToast.info(stringRId);
    }

    public static void toastInfo(@NonNull String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        AToast.info(str);
    }

    public static void toastError(@StringRes int stringRId) {
        AToast.error(stringRId);
    }

    public static void toastError(@NonNull String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        AToast.error(str);
    }

    public static void toastWarning(@StringRes int stringRId) {
        AToast.warning(stringRId);
    }

    public static void toastWarning(@NonNull String str) {
        AToast.warning(str);
    }

    public static void toastSuccess(@StringRes int stringRId) {
        AToast.success(stringRId);
    }

    public static void toastSuccess(@NonNull String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        AToast.success(str);
    }

    @Deprecated
    public static void toastNormal(@StringRes int stringRId) {
        AToast.normal(stringRId);
    }

    @Deprecated
    public static void toastNormal(@NonNull String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        AToast.normal(str);
    }

}

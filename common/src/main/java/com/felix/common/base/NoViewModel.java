package com.felix.common.base;

import org.greenrobot.eventbus.EventBus;

import android.app.Application;
import android.support.annotation.NonNull;

/**
 * Created by chaichuanfa on 2019/1/15
 */
public class NoViewModel extends BaseViewModel {

    public NoViewModel(@NonNull Application application) {
        super(application);
    }

    @Override
    protected EventBus getBus() {
        return null;
    }
}

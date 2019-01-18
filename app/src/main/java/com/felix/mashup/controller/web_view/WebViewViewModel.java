package com.felix.mashup.controller.web_view;

import com.felix.common.base.BaseViewModel;

import org.greenrobot.eventbus.EventBus;

import android.app.Application;
import android.support.annotation.NonNull;

import javax.inject.Inject;

public class WebViewViewModel extends BaseViewModel {

    @Inject
    EventBus mBus;

    public WebViewViewModel(@NonNull Application application) {
        super(application);
    }

    @Override
    protected EventBus getBus() {
        return mBus;
    }
}

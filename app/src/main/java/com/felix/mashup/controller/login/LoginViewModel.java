package com.felix.mashup.controller.login;

import com.felix.common.base.BaseViewModel;

import org.greenrobot.eventbus.EventBus;

import android.app.Application;
import android.support.annotation.NonNull;

import javax.inject.Inject;

public class LoginViewModel extends BaseViewModel {

    @Inject
    EventBus mBus;

    public LoginViewModel(@NonNull Application application) {
        super(application);
    }

    @Override
    protected EventBus getBus() {
        return mBus;
    }
}

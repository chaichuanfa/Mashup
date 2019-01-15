package com.felix.mashup.controller.ui.main;

import com.felix.common.base.BaseViewModel;
import com.felix.common.uitls.net.NetUtils;

import android.app.Application;
import android.support.annotation.NonNull;
import android.widget.Toast;

import javax.inject.Inject;

public class MainViewModel extends BaseViewModel {

    @Inject
    NetUtils mNetUtils;

    public MainViewModel(@NonNull Application application) {
        super(application);
    }

    public void showNetworkType() {
        Toast.makeText(getApplication(), mNetUtils.getNetTypeString(), Toast.LENGTH_SHORT).show();
    }

}

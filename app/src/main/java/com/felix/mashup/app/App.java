package com.felix.mashup.app;

import com.alibaba.android.arouter.launcher.ARouter;
import com.felix.common.uitls.log.LogToFileTree;
import com.felix.mashup.BuildConfig;
import com.felix.mashup.app.di.ApplicationComponent;
import com.felix.mashup.app.di.ApplicationModule;
import com.felix.mashup.app.di.DaggerApplicationComponent;

import android.support.multidex.MultiDexApplication;

import timber.log.Timber;

/**
 * Created by chaichuanfa on 2018/1/10.
 */

public class App extends MultiDexApplication {

    private static App mInstance;

    private ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        initTimber();
        injectComponent();
        initARouter();
    }

    private void initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(new LogToFileTree(this, getPackageName()));
        }
    }

    private void initARouter() {
        if (BuildConfig.DEBUG) {
            ARouter.openLog();
            ARouter.openDebug();
        }
        ARouter.init(this);
    }

    private void injectComponent() {
        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(getInstance()))
                .build();
        mApplicationComponent.inject(this);
    }

    public static App getInstance() {
        return mInstance;
    }

    public ApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }
}

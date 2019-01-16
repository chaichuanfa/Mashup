package com.felix.mashup.app;

import com.alibaba.android.arouter.launcher.ARouter;
import com.facebook.stetho.Stetho;
import com.felix.common.uitls.log.LogToFileTree;
import com.felix.mashup.app.di.AppConfigModule;
import com.felix.mashup.app.di.ApplicationComponent;
import com.felix.mashup.app.di.ApplicationModule;
import com.felix.mashup.app.di.DaggerApplicationComponent;
import com.jakewharton.threetenabp.AndroidThreeTen;

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
        logCollect();
        injectComponent();
        initARouter();
        AndroidThreeTen.init(this);
    }

    private void logCollect() {
        if (AppConfigModule.IS_TEST) {
            Timber.plant(new LogToFileTree(this, getPackageName()));
            Stetho.initialize(Stetho.newInitializerBuilder(this)
                    .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                    .enableWebKitInspector(
                            Stetho.defaultInspectorModulesProvider(this))
                    .build());
        }
    }

    private void initARouter() {
        if (AppConfigModule.SHOW_LOG) {
            ARouter.openLog();
            ARouter.openDebug();
        }
        ARouter.init(this);
    }

    private void injectComponent() {
        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(getInstance()))
                .appConfigModule(new AppConfigModule(""))
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

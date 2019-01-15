package com.felix.mashup.app.di;

import com.felix.common.uitls.net.NetUtils;
import com.felix.mashup.app.App;

import org.greenrobot.eventbus.EventBus;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by chaichuanfa on 2019/1/14
 */

@Singleton
@Component(modules = {
        ApplicationModule.class,
        AppConfigModule.class
})
public interface ApplicationComponent {

    void inject(App app);

    Application application();

    Context context();

    EventBus eventBus();

    NetUtils netUtils();
}

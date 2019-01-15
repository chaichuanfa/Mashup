package com.felix.mashup.controller.ui.main.di;

import com.felix.common.di.PerActivity;
import com.felix.mashup.app.di.ApplicationComponent;
import com.felix.mashup.controller.MainActivity;
import com.felix.mashup.controller.ui.main.MainFragment;
import com.felix.mashup.controller.ui.main.MainViewModel;
import com.felix.model.user_info.module.UserInfoModule;

import dagger.Component;

/**
 * Created by chaichuanfa on 2019/1/15
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class,
        modules = {
                MainModule.class,
                UserInfoModule.class
        }
)
public interface MainComponent {

    void inject(MainActivity activity);

    void inject(MainFragment fragment);

    void inject(MainViewModel module);
}

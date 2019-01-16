package com.felix.mashup.controller.splash.di;

import com.felix.common.di.PerActivity;
import com.felix.mashup.controller.splash.SplashActivity;
import com.felix.mashup.controller.splash.ui.SplashFragment;
import com.felix.mashup.controller.splash.SplashViewModel;
import com.felix.mashup.app.di.ApplicationComponent;

import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class)
public interface SplashComponent {

    void inject(SplashActivity activity);

    void inject(SplashFragment fragment);

    void inject(SplashViewModel viewmodel);
}

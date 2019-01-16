package com.felix.mashup.controller.login.di;

import com.felix.common.di.PerActivity;
import com.felix.mashup.controller.login.LoginActivity;
import com.felix.mashup.controller.login.ui.LoginFragment;
import com.felix.mashup.controller.login.LoginViewModel;
import com.felix.mashup.app.di.ApplicationComponent;

import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class)
public interface LoginComponent {

    void inject(LoginActivity activity);

    void inject(LoginFragment fragment);

    void inject(LoginViewModel viewmodel);
}

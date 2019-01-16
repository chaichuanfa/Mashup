package com.felix.mashup.controller.login;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.felix.common.di.HasComponent;
import com.felix.mashup.base.BaseActivity;
import com.felix.mashup.controller.login.di.DaggerLoginComponent;
import com.felix.mashup.controller.login.di.LoginComponent;
import com.felix.mashup.controller.login.ui.LoginFragment;

import android.os.Bundle;

@Route(path = "/login/activity")
public class LoginActivity extends BaseActivity implements HasComponent<LoginComponent> {

    private LoginComponent mComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            LoginFragment fragment = findFragment(LoginFragment.class);
            if (fragment == null) {
                loadRootFragment(android.R.id.content,
                        (LoginFragment) ARouter.getInstance()
                                .build("/login/fragment")
                                .navigation());
            }
        }
    }

    @Override
    protected void injectDependencies() {
        mComponent = DaggerLoginComponent.builder()
                .applicationComponent(getApplicationComponent())
                .build();
        mComponent.inject(this);
    }

    @Override
    public LoginComponent getComponent() {
        return mComponent;
    }
}

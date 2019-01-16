package com.felix.mashup.controller.splash;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.felix.common.di.HasComponent;
import com.felix.mashup.base.BaseActivity;
import com.felix.mashup.controller.splash.di.DaggerSplashComponent;
import com.felix.mashup.controller.splash.di.SplashComponent;
import com.felix.mashup.controller.splash.ui.SplashFragment;

import android.os.Bundle;
import android.transition.Fade;
import android.transition.TransitionSet;

@Route(path = "/splash/activity")
public class SplashActivity extends BaseActivity implements HasComponent<SplashComponent> {

    private SplashComponent mComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setEnterTransition(new TransitionSet());
        getWindow().setReturnTransition(new Fade(Fade.OUT));
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            SplashFragment fragment = findFragment(SplashFragment.class);
            if (fragment == null) {
                loadRootFragment(android.R.id.content,
                        (SplashFragment) ARouter.getInstance()
                                .build("/splash/fragment")
                                .navigation());
            }
        }
    }

    @Override
    protected void injectDependencies() {
        mComponent = DaggerSplashComponent.builder()
                .applicationComponent(getApplicationComponent())
                .build();
        mComponent.inject(this);
    }

    @Override
    public SplashComponent getComponent() {
        return mComponent;
    }

    @Override
    public void onBackPressedSupport() {
    }
}

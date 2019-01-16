package com.felix.mashup.controller.main;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.felix.common.di.HasComponent;
import com.felix.mashup.base.BaseActivity;
import com.felix.mashup.controller.main.di.DaggerMainComponent;
import com.felix.mashup.controller.main.di.MainComponent;
import com.felix.mashup.controller.main.ui.MainFragment;

import org.greenrobot.eventbus.Subscribe;

import android.os.Bundle;
import android.transition.TransitionSet;

/**
 * Created by chaichuanfa on 2019/1/15
 */
@Route(path = "/main/activity")
public class MainActivity extends BaseActivity implements HasComponent<MainComponent> {

    private MainComponent mMainComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setEnterTransition(new TransitionSet());
        getWindow().setReturnTransition(new TransitionSet());
        super.onCreate(savedInstanceState);
        MainFragment fragment = findFragment(MainFragment.class);
        if (fragment == null) {
            loadRootFragment(android.R.id.content, (MainFragment) ARouter.getInstance()
                    .build("/main/fragment")
                    .navigation());
        }
    }

    @Override
    protected void injectDependencies() {
        mMainComponent = DaggerMainComponent.builder()
                .applicationComponent(getApplicationComponent())
                .build();
        mMainComponent.inject(this);
    }

    @Override
    public MainComponent getComponent() {
        return mMainComponent;
    }

    @Subscribe
    public void onStringEvent(String event) {
    }

    @Override
    public void onBackPressedSupport() {
        if (!moveTaskToBack(true)) {
            finish();
        }
    }
}

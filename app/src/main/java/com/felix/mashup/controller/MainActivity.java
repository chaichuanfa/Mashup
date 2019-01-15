package com.felix.mashup.controller;

import com.felix.common.di.HasComponent;
import com.felix.mashup.R;
import com.felix.mashup.base.BaseActivity;
import com.felix.mashup.controller.ui.main.MainFragment;
import com.felix.mashup.controller.ui.main.di.DaggerMainComponent;
import com.felix.mashup.controller.ui.main.di.MainComponent;

import org.greenrobot.eventbus.Subscribe;

import android.os.Bundle;

public class MainActivity extends BaseActivity implements HasComponent<MainComponent> {

    private MainComponent mMainComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        MainFragment fragment = findFragment(MainFragment.class);
        if (fragment == null) {
            loadRootFragment(R.id.container, MainFragment.newInstance());
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
}

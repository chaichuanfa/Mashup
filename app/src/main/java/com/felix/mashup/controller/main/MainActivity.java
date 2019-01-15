package com.felix.mashup.controller.main;

import com.felix.common.di.HasComponent;
import com.felix.mashup.R;
import com.felix.mashup.base.BaseActivity;
import com.felix.mashup.controller.main.di.DaggerMainComponent;
import com.felix.mashup.controller.main.di.MainComponent;
import com.felix.mashup.controller.main.ui.MainFragment;

import org.greenrobot.eventbus.Subscribe;

import android.os.Bundle;

/**
 * Created by chaichuanfa on 2019/1/15
 */
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

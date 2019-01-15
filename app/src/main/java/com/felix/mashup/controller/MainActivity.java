package com.felix.mashup.controller;

import com.felix.common.di.HasComponent;
import com.felix.common.uitls.net.NetUtils;
import com.felix.mashup.R;
import com.felix.mashup.base.BaseActivity;
import com.felix.mashup.controller.ui.main.MainFragment;
import com.felix.mashup.controller.ui.main.di.DaggerMainComponent;
import com.felix.mashup.controller.ui.main.di.MainComponent;

import android.os.Bundle;

import javax.inject.Inject;

import static com.google.common.base.Preconditions.checkNotNull;

public class MainActivity extends BaseActivity implements HasComponent<MainComponent> {

    @Inject
    NetUtils mNetUtils;

    private MainComponent mMainComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        MainFragment fragment = findFragment(MainFragment.class);
        if (fragment == null) {
            loadRootFragment(R.id.container, MainFragment.newInstance());
        }

        checkNotNull(mNetUtils);
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
}

package com.felix.mashup.base;

import com.felix.common.base.BaseCommonActivity;
import com.felix.mashup.app.App;
import com.felix.mashup.app.di.ApplicationComponent;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by chaichuanfa on 2019/1/11
 */
public abstract class BaseActivity extends BaseCommonActivity {

    protected ApplicationComponent getApplicationComponent() {
        return App.getInstance().getApplicationComponent();
    }

    @Override
    protected EventBus getBus() {
        return null;
    }
}

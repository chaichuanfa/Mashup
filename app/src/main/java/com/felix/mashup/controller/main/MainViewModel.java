package com.felix.mashup.controller.main;

import com.felix.common.base.BaseViewModel;
import com.felix.mashup.R;
import com.felix.model.user_info.UserRepository;

import org.greenrobot.eventbus.EventBus;

import android.app.Application;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;

import javax.inject.Inject;

/**
 * Created by chaichuanfa on 2019/1/15
 */
public class MainViewModel extends BaseViewModel {

    public final ObservableField<String> mToolbarTitle;

    @Inject
    EventBus mBus;

    @Inject
    UserRepository mUserRepository;

    public MainViewModel(@NonNull Application application) {
        super(application);
        mToolbarTitle = new ObservableField<>(application.getString(R.string.nav_title_news));
    }

    @Override
    protected EventBus getBus() {
        return mBus;
    }

    public void setMenuItemSelected(int item) {
        switch (item) {
            case 0:
                mToolbarTitle.set(getApplication().getString(R.string.nav_title_news));
                break;
            case 1:
                mToolbarTitle.set(getApplication().getString(R.string.nav_title_wechat));
                break;
        }
    }
}

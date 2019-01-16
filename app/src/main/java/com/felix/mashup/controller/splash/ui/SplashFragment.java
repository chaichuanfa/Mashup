package com.felix.mashup.controller.splash.ui;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.felix.mashup.R;
import com.felix.mashup.base.BaseFragment;
import com.felix.mashup.controller.splash.SplashViewModel;
import com.felix.mashup.controller.splash.di.SplashComponent;
import com.felix.mashup.databinding.SplashFragmentBinding;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.EventBusException;

import android.view.View;

import javax.inject.Inject;

@Route(path = "/splash/fragment")
public class SplashFragment extends BaseFragment<SplashViewModel, SplashFragmentBinding> {

    @Inject
    EventBus mBus;

    @Override
    protected int getLayoutRes() {
        return R.layout.splash_fragment;
    }

    @Override
    protected void bindViewModel() {
        super.bindViewModel();
        mDataBinding.setViewmodel(mViewModel);
    }

    @Override
    protected void bindViews(View view) {
        mViewModel.getRouteLiveEvent().observe(this, aVoid -> {
            startRouter("/main/activity");
            mActivity.finishAfterTransition();
        });
    }

    @Override
    protected void injectDependencies() {
        SplashComponent component = this.getComponent(SplashComponent.class);
        component.inject(this);
        if (mViewModel != null) {
            component.inject(mViewModel);
            if (!mBus.isRegistered(mViewModel)) {
                try {
                    mBus.register(mViewModel);
                } catch (EventBusException e) {
                    // ignore
                }
            }
        }
    }

    @Override
    protected EventBus getBus() {
        return mBus;
    }

}

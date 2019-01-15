package com.felix.mashup.controller.ui.main;

import com.felix.mashup.R;
import com.felix.mashup.base.BaseFragment;
import com.felix.mashup.controller.ui.main.di.MainComponent;
import com.felix.mashup.databinding.MainFragmentBinding;

import org.greenrobot.eventbus.EventBus;

import android.view.View;

import javax.inject.Inject;

public class MainFragment extends BaseFragment<MainViewModel, MainFragmentBinding> {

    @Inject
    EventBus mBus;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.main_fragment;
    }

    @Override
    protected void bindViews(View view) {
        mViewModel.showNetworkType();
    }

    @Override
    protected void bindViewModel() {
        super.bindViewModel();
        mDataBinding.setViewmodel(mViewModel);
    }

    @Override
    protected void injectDependencies() {
        MainComponent component = this.getComponent(MainComponent.class);
        component.inject(this);
        if (mViewModel != null) {
            component.inject(mViewModel);
            if (!mBus.isRegistered(mViewModel)) {
                mBus.register(mViewModel);
            }
        }
    }

    @Override
    protected EventBus getBus() {
        return mBus;
    }
}

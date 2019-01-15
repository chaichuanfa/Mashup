package com.felix.mashup.controller.main.ui;

import com.felix.mashup.R;
import com.felix.mashup.base.BaseFragment;
import com.felix.mashup.controller.main.di.MainComponent;
import com.felix.mashup.controller.main.vm.MainViewModel;
import com.felix.mashup.databinding.MainFragmentBinding;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.EventBusException;

import android.view.View;

import javax.inject.Inject;

/**
 * Created by chaichuanfa on 2019/1/15
 */
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
                try {
                    mBus.register(mViewModel);
                } catch (EventBusException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    protected EventBus getBus() {
        return mBus;
    }
}
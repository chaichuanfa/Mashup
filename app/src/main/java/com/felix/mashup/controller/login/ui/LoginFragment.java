package com.felix.mashup.controller.login.ui;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.felix.mashup.R;
import com.felix.mashup.base.BaseFragment;
import com.felix.mashup.controller.login.LoginViewModel;
import com.felix.mashup.controller.login.di.LoginComponent;
import com.felix.mashup.databinding.LoginFragmentBinding;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.EventBusException;

import android.view.View;

import javax.inject.Inject;

@Route(path = "/login/fragment")
public class LoginFragment extends BaseFragment<LoginViewModel, LoginFragmentBinding> {

    @Inject
    EventBus mBus;

    @Override
    protected int getLayoutRes() {
        return R.layout.login_fragment;
    }

    @Override
    protected void bindViewModel() {
        super.bindViewModel();
        mDataBinding.setViewmodel(mViewModel);
        mDataBinding.setFragment(this);
    }

    @Override
    protected void bindViews(View view) {
    }

    @Override
    protected void injectDependencies() {
        LoginComponent component = this.getComponent(LoginComponent.class);
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

    public void finishActivity(View view) {
        mActivity.finishAfterTransition();
    }

}

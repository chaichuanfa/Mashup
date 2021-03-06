package com.felix.mashup.controller.main.ui;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.felix.mashup.R;
import com.felix.mashup.base.BaseFragment;
import com.felix.mashup.controller.main.MainViewModel;
import com.felix.mashup.controller.main.di.MainComponent;
import com.felix.mashup.databinding.WechatSiftFragmentBinding;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.EventBusException;

import android.view.View;
import android.view.animation.Animation;

import javax.inject.Inject;

import timber.log.Timber;

/**
 * Created by chaichuanfa on 2019/1/17
 */
@Route(path = "/wechat_sift/fragment")
public class WeChatSiftFragment
        extends BaseFragment<MainViewModel, WechatSiftFragmentBinding> {

    @Inject
    EventBus mBus;

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
                    // ignore
                }
            }
        }
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.wechat_sift_fragment;
    }

    @Override
    protected void bindViewModel() {
        super.bindViewModel();
        mDataBinding.setViewmodel(mViewModel);
    }

    @Override
    protected EventBus getBus() {
        return mBus;
    }

    @Override
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        return new Animation() {
        };
    }

    @Override
    protected void bindViews(View view) {
        Timber.d("bindViews --------- ");
    }

}

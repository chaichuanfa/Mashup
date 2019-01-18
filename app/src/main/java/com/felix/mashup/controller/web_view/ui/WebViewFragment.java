package com.felix.mashup.controller.web_view.ui;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.felix.mashup.R;
import com.felix.mashup.base.BaseFragment;
import com.felix.mashup.controller.web_view.WebViewViewModel;
import com.felix.mashup.controller.web_view.di.WebViewComponent;
import com.felix.mashup.databinding.WebViewFragmentBinding;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.EventBusException;

import android.view.View;

import javax.inject.Inject;

@Route(path = "/web_view/fragment")
public class WebViewFragment extends BaseFragment<WebViewViewModel, WebViewFragmentBinding> {

    @Autowired
    String url;

    @Autowired
    String title;

    @Inject
    EventBus mBus;

    @Override
    protected int getLayoutRes() {
        return R.layout.web_view_fragment;
    }

    @Override
    protected void bindViewModel() {
        super.bindViewModel();
        mDataBinding.setViewmodel(mViewModel);
    }

    @Override
    protected void bindViews(View view) {
        mDataBinding.mWebView.loadUrl(url);
        mDataBinding.toolbar.setNavigationOnClickListener(v -> {
            mActivity.finishAfterTransition();
        });
        mDataBinding.toolbar.setTitle(title);
    }

    @Override
    protected void injectDependencies() {
        WebViewComponent component = this.getComponent(WebViewComponent.class);
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

    @Override
    public void onResume() {
        super.onResume();
        mDataBinding.mWebView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mDataBinding.mWebView.onPause();
    }

    @Override
    protected void unbindViews() {
        mDataBinding.mWebView.destroy();
        super.unbindViews();
    }
}

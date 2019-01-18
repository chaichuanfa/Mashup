package com.felix.mashup.controller.web_view;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.felix.common.di.HasComponent;
import com.felix.mashup.base.BaseActivity;
import com.felix.mashup.controller.web_view.di.DaggerWebViewComponent;
import com.felix.mashup.controller.web_view.di.WebViewComponent;
import com.felix.mashup.controller.web_view.ui.WebViewFragment;

import android.os.Bundle;

@Route(path = "/web_view/activity")
public class WebViewActivity extends BaseActivity implements HasComponent<WebViewComponent> {

    @Autowired
    String url;

    @Autowired
    String title;

    private WebViewComponent mComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            WebViewFragment fragment = findFragment(WebViewFragment.class);
            if (fragment == null) {
                loadRootFragment(android.R.id.content,
                        (WebViewFragment) ARouter.getInstance()
                                .build("/web_view/fragment")
                                .withString("url", url)
                                .withString("title", title)
                                .navigation());
            }
        }
    }

    @Override
    protected void injectDependencies() {
        mComponent = DaggerWebViewComponent.builder()
                .applicationComponent(getApplicationComponent())
                .build();
        mComponent.inject(this);
    }

    @Override
    public WebViewComponent getComponent() {
        return mComponent;
    }
}

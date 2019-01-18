package com.felix.mashup.controller.web_view.di;

import com.felix.common.di.PerActivity;
import com.felix.mashup.controller.web_view.WebViewActivity;
import com.felix.mashup.controller.web_view.ui.WebViewFragment;
import com.felix.mashup.controller.web_view.WebViewViewModel;
import com.felix.mashup.app.di.ApplicationComponent;

import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class)
public interface WebViewComponent {

    void inject(WebViewActivity activity);

    void inject(WebViewFragment fragment);

    void inject(WebViewViewModel viewmodel);
}

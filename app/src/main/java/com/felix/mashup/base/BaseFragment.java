package com.felix.mashup.base;

import com.alibaba.android.arouter.launcher.ARouter;
import com.felix.common.base.BaseCommonFragment;
import com.felix.common.base.BaseViewModel;
import com.felix.common.di.HasComponent;

import org.greenrobot.eventbus.EventBus;

import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;

/**
 * Created by chaichuanfa on 2019/1/11
 */
public abstract class BaseFragment<V extends BaseViewModel, D extends ViewDataBinding>
        extends BaseCommonFragment<V, D> {

    @Override
    protected void unbindViews() {

    }

    @Override
    protected EventBus getBus() {
        return null;
    }

    /**
     * Gets a component for dependency injection by its type.
     */
    @SuppressWarnings("unchecked")
    protected <C> C getComponent(Class<C> componentType) {
        return componentType.cast(((HasComponent<C>) getActivity()).getComponent());
    }

    protected void startRouter(String path, @Nullable Bundle bundle) {
        ARouter.getInstance().build(path)
                .withOptionsCompat(ActivityOptionsCompat.makeSceneTransitionAnimation(mActivity))
                .with(bundle)
                .navigation(mActivity);
    }

    protected void startRouter(String path) {
        startRouter(path, null);
    }

}

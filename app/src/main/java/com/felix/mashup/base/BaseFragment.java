package com.felix.mashup.base;

import com.felix.common.base.BaseCommonFragment;
import com.felix.common.di.HasComponent;

import android.arch.lifecycle.AndroidViewModel;
import android.databinding.ViewDataBinding;

/**
 * Created by chaichuanfa on 2019/1/11
 */
public abstract class BaseFragment<V extends AndroidViewModel, D extends ViewDataBinding>
        extends BaseCommonFragment<V, D> {

    @Override
    protected void unbindViews() {

    }

    /**
     * Gets a component for dependency injection by its type.
     */
    @SuppressWarnings("unchecked")
    protected <C> C getComponent(Class<C> componentType) {
        return componentType.cast(((HasComponent<C>) getActivity()).getComponent());
    }

}

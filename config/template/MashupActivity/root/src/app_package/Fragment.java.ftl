package ${featurePackageName}.ui;

import com.felix.mashup.R;
import com.felix.mashup.base.BaseFragment;
import ${featurePackageName}.di.${componentClass};
import ${featurePackageName}.${viewModelClass};
import com.felix.mashup.databinding.${fragmentClass}Binding;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.EventBusException;
import com.alibaba.android.arouter.facade.annotation.Route;
import android.view.View;
import javax.inject.Inject;

@Route(path = "/${classToResource(featureName)}/fragment")
public class ${fragmentClass} extends BaseFragment<${viewModelClass}, ${fragmentClass}Binding> {

    @Inject
    EventBus mBus;

    @Override
    protected int getLayoutRes() {
        return R.layout.${fragmentLayout};
    }

    @Override
    protected void bindViewModel() {
        super.bindViewModel();
        mDataBinding.setViewmodel(mViewModel);
    }

    @Override
    protected void bindViews(View view) {
        // TODO: init view
    }

    @Override
    protected void injectDependencies() {
        ${componentClass} component = this.getComponent(${componentClass}.class);
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

package ${featurePackageName};

import android.os.Bundle;

import ${packageName}.${classToResource(featureName)}.ui.${fragmentClass};
import ${packageName}.${classToResource(featureName)}.di.${componentClass};
import com.felix.mashup.base.BaseActivity;
import com.felix.common.di.HasComponent;

public class ${activityClass} extends BaseActivity implements HasComponent<${componentClass}> {

    private ${componentClass} mComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            ${fragmentClass} fragment = findFragment(${fragmentClass}.class);
            if (fragment == null) {
                loadRootFragment(android.R.id.content, ${fragmentClass}.newInstance());
            }
        }
    }

    @Override
    protected void injectDependencies() {
        mComponent = Dagger${componentClass}.builder()
                .applicationComponent(getApplicationComponent())
                .build();
        mComponent.inject(this);
    }

    @Override
    public ${componentClass} getComponent() {
        return mComponent;
    }
}

package ${featurePackageName}.di;

import com.felix.common.di.PerActivity;
import ${featurePackageName}.${activityClass};
import ${featurePackageName}.ui.${fragmentClass};
import ${featurePackageName}.vm.${viewModelClass};
import com.felix.mashup.app.di.ApplicationComponent;

import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class)
public interface ${componentClass} {

    void inject(${activityClass} activity);

    void inject(${fragmentClass} fragment);

    void inject(${viewModelClass} viewmodel);
}

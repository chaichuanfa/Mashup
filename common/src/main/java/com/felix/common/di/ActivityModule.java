package com.felix.common.di;

import android.app.Activity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by chaichuanfa on 2019/1/15
 */
@Module
public class ActivityModule {

    private final Activity activity;

    public ActivityModule(Activity activity) {
        this.activity = activity;
    }

    /**
     * Expose the provideActivity to dependents in the graph.
     */
    @Provides
    @PerActivity
    Activity provideActivity() {
        return this.activity;
    }
}

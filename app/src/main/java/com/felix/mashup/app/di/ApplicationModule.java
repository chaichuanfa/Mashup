package com.felix.mashup.app.di;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by chaichuanfa on 2019/1/14
 */
@Module
public class ApplicationModule {

    private final Application application;

    public ApplicationModule(Application application) {
        this.application = application;
    }

    /**
     * Expose the application to the graph.
     */
    @Singleton
    @Provides
    Application provideApplication() {
        return this.application;
    }

    @Singleton
    @Provides
    Context provideAppContext() {
        return application.getApplicationContext();
    }
}

package com.felix.mashup.app.di;

import com.google.gson.Gson;

import com.felix.common.di.provider.ModelBaseModule;
import com.felix.common.uitls.net.NetUtils;
import com.felix.mashup.app.App;
import com.felix.model.db.DatabaseHelper;
import com.felix.model.provider.ModelDatabaseModule;

import org.greenrobot.eventbus.EventBus;
import org.threeten.bp.format.DateTimeFormatter;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;


/**
 * Created by chaichuanfa on 2019/1/14
 */

@Singleton
@Component(modules = {
        ApplicationModule.class,
        AppConfigModule.class,
        AppUtilModule.class,
        ModelBaseModule.class,
        ModelDatabaseModule.class
})
public interface ApplicationComponent {

    void inject(App app);

    Application application();

    Context context();

    EventBus eventBus();

    NetUtils netUtils();

    Gson gson();

    OkHttpClient okHttpClient();

    Retrofit retrofit();

    DateTimeFormatter dateTimeFormatter();

    DatabaseHelper databaseHelper();
}

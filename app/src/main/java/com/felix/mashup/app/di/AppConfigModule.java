package com.felix.mashup.app.di;

import com.felix.common.di.provider.config.GsonConfig;
import com.felix.common.di.provider.config.OkHttpConfig;
import com.felix.common.di.provider.config.RetrofitConfig;
import com.felix.common.uitls.system.DeviceUuidFactory;
import com.felix.mashup.BuildConfig;
import com.felix.mashup.utils.AppAutoGsonAdapterFactory;
import com.felix.model.factory.ModelAutoGsonAdapterFactory;
import com.felix.model.provider.ModelConfigModule;

import org.threeten.bp.format.DateTimeFormatter;

import android.content.Context;

import java.util.Arrays;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by chaichuanfa on 2019/1/15
 */
@Module(includes = ModelConfigModule.class)
public class AppConfigModule {

    public static final boolean IS_DEBUG = "debug".equals(BuildConfig.BUILD_TYPE);

    public static final boolean IS_REGRESSION = "regression".equals(BuildConfig.BUILD_TYPE);

    public static final boolean IS_RELEASE = "release".equals(BuildConfig.BUILD_TYPE);

    public static final boolean IS_PRODUCTION = "production".equals(BuildConfig.FLAVOR);

    public static final boolean IS_STAGING = "staging".equals(BuildConfig.FLAVOR);

    public static final boolean IS_DEV = "dev".equals(BuildConfig.FLAVOR);

    public static final boolean SHOW_LOG = IS_DEBUG || IS_REGRESSION;

    public static final boolean IS_TEST = !IS_PRODUCTION && !IS_STAGING;

    private static final boolean PIN_CERT = IS_RELEASE;

    private final String mUserAgent;

    public AppConfigModule(String userAgent) {
        mUserAgent = userAgent;
    }

    @Singleton
    @Provides
    GsonConfig provideGsonConfig(DateTimeFormatter dateTimeFormatter) {
        return GsonConfig.builder()
                .typeAdapterFactories(Arrays.asList(ModelAutoGsonAdapterFactory.create(),
                        AppAutoGsonAdapterFactory.create()))
                .dateTimeFormatter(dateTimeFormatter)
                .build();
    }

    @Singleton
    @Provides
    RetrofitConfig provideRetrofitConfig() {
        return RetrofitConfig.builder()
                .apiBaseUrl(BuildConfig.API_BASE_URL)
                .build();
    }

    @Singleton
    @Provides
    OkHttpConfig provideOkHttpConfig(Context context) {
        try {
            DeviceUuidFactory.init(context);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return OkHttpConfig.builder()
                .basicAuthClientId(BuildConfig.BASIC_AUTH_ID)
                .basicAuthClientPass(BuildConfig.BASIC_AUTH_PASS)
                .showLog(SHOW_LOG)
                .userAgent(mUserAgent)
                .pinCert(PIN_CERT)
                .deviceId(DeviceUuidFactory.getDeviceUuid().toString())
                .version(BuildConfig.VERSION_NAME)
                .build();
    }
}

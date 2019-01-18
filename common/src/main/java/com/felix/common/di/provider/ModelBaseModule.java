package com.felix.common.di.provider;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapterFactory;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.felix.common.di.provider.config.GsonConfig;
import com.felix.common.di.provider.config.OkHttpConfig;
import com.felix.common.di.provider.config.RetrofitConfig;
import com.felix.common.uitls.Constants;
import com.felix.common.uitls.net.ApiErrorAwareConverterFactory;
import com.felix.common.uitls.net.AuthInterceptor;
import com.felix.common.uitls.net.EmptyJsonLenientConverterFactory;
import com.felix.common.uitls.time.ZonedDateTimeJsonConverter;
import com.moczul.ok2curl.CurlInterceptor;

import org.threeten.bp.ZonedDateTime;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

/**
 * Created by chaichuanfa on 2019/1/15
 */
@Module
public class ModelBaseModule {

    @Singleton
    @Provides
    AuthInterceptor provideAuthInterceptor(OkHttpConfig config) {
        return new AuthInterceptor(config);
    }

    @Singleton
    @Provides
    OkHttpClient provideOkHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        return builder.build();
    }

    @Singleton
    @Provides
    Retrofit provideRetrofit(OkHttpClient okHttpClient, AuthInterceptor authInterceptor,
            Gson gson, RetrofitConfig config, OkHttpConfig okHttpConfig) {
        OkHttpClient.Builder builder = okHttpClient.newBuilder().addInterceptor(authInterceptor);
        if (okHttpConfig.showLog()) {
            builder.addInterceptor(new HttpLoggingInterceptor(
                    message -> Timber.d("OkHttp: " + message))
                    .setLevel(HttpLoggingInterceptor.Level.BODY))
                    .addInterceptor(
                            new CurlInterceptor(message -> Timber.d("Ok2Curl: " + message)))
                    .addNetworkInterceptor(new StethoInterceptor());
        }
        return new Retrofit.Builder().client(builder.build())
                .baseUrl(config.apiBaseUrl())
                //https://github.com/ReactiveX/RxAndroid/issues/387
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync())
                // Handle Empty Body. https://github.com/square/retrofit/issues/1554
                .addConverterFactory(new ApiErrorAwareConverterFactory(
                        new EmptyJsonLenientConverterFactory(GsonConverterFactory.create(gson))))
                .build();
    }

    @Singleton
    @Provides
    Gson provideGson(GsonConfig config) {
        GsonBuilder builder = new GsonBuilder()
                .setDateFormat(Constants.DATE_FORMAT)
                .setPrettyPrinting();
        for (TypeAdapterFactory factory : config.typeAdapterFactories()) {
            builder.registerTypeAdapterFactory(factory);
        }
        builder.registerTypeAdapter(ZonedDateTime.class,
                new ZonedDateTimeJsonConverter(config.dateTimeFormatter()));
        return builder.create();
    }
}

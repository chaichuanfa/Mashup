package com.felix.model.news_info.module;

import com.felix.model.news_info.NewsInfoApi;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by chaichuanfa on 2019/1/17
 */
@Module
public class NewsModule {

    @Provides
    NewsInfoApi provideNewsInfoApi(Retrofit retrofit) {
        return retrofit.create(NewsInfoApi.class);
    }

}

package com.felix.model.joke_info.module;

import com.felix.model.joke_info.JokeInfoApi;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by chaichuanfa on 2019/1/18
 */
@Module
public class JokeModule {

    @Provides
    JokeInfoApi provideJokeInfoApi(Retrofit retrofit) {
        return retrofit.create(JokeInfoApi.class);
    }
}

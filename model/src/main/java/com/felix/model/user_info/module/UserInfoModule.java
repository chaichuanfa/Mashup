package com.felix.model.user_info.module;

import com.felix.model.user_info.UserInfoApi;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by chaichuanfa on 2019/1/15
 */
@Module
public class UserInfoModule {

    @Provides
    UserInfoApi provideUserInfoApi(Retrofit retrofit) {
        return retrofit.create(UserInfoApi.class);
    }

}

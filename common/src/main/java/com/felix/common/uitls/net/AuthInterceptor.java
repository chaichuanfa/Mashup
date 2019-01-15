package com.felix.common.uitls.net;

import com.felix.common.di.provider.config.OkHttpConfig;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * Created by chaichuanfa on 2019/1/15
 */
public class AuthInterceptor implements Interceptor {

    private OkHttpConfig mOkHttpConfig;

    public AuthInterceptor(OkHttpConfig config) {
        mOkHttpConfig = config;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        // TODO: 2019/1/15 auth logic 
        return chain.proceed(chain.request());
    }
}

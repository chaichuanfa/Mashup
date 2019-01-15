package com.felix.common.di.provider.config;

import com.google.auto.value.AutoValue;

/**
 * Created by chaichuanfa on 2019/1/15
 */
@AutoValue
public abstract class RetrofitConfig {

    public abstract String apiBaseUrl();

    public static Builder builder() {
        return new AutoValue_RetrofitConfig.Builder();
    }

    @AutoValue.Builder
    public abstract static class Builder {

        public abstract Builder apiBaseUrl(String apiBaseUrl);

        public abstract RetrofitConfig build();
    }
}

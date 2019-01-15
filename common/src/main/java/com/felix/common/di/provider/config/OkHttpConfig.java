package com.felix.common.di.provider.config;

import com.google.auto.value.AutoValue;

/**
 * Created by chaichuanfa on 2019/1/15
 */
@AutoValue
public abstract class OkHttpConfig {

    public abstract boolean showLog();

    public abstract String basicAuthClientId();

    public abstract String basicAuthClientPass();

    public abstract String deviceId();

    public abstract String userAgent();

    public abstract boolean pinCert();

    public abstract String version();

    public static Builder builder() {
        return new AutoValue_OkHttpConfig.Builder();
    }

    @AutoValue.Builder
    public abstract static class Builder {

        public abstract Builder showLog(boolean showLog);

        public abstract Builder basicAuthClientId(String basicAuthClientId);

        public abstract Builder basicAuthClientPass(String basicAuthClientPass);

        public abstract Builder deviceId(String deviceId);

        public abstract Builder userAgent(String userAgent);

        public abstract Builder pinCert(boolean pinCert);

        public abstract Builder version(String version);

        public abstract OkHttpConfig build();
    }
}

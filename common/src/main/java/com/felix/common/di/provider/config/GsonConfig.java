package com.felix.common.di.provider.config;

import com.google.auto.value.AutoValue;
import com.google.gson.TypeAdapterFactory;

import org.threeten.bp.format.DateTimeFormatter;

import java.util.List;

/**
 * Created by chaichuanfa on 2019/1/15
 */
@AutoValue
public abstract class GsonConfig {

    public abstract List<TypeAdapterFactory> typeAdapterFactories();

    public static Builder builder() {
        return new AutoValue_GsonConfig.Builder();
    }

    public abstract DateTimeFormatter dateTimeFormatter();

    @AutoValue.Builder
    public abstract static class Builder {

        public abstract Builder typeAdapterFactories(List<TypeAdapterFactory> typeAdapterFactories);
        public abstract Builder dateTimeFormatter(DateTimeFormatter dateTimeFormatter);

        public abstract GsonConfig build();
    }
}

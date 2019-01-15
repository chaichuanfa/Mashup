package com.felix.model.factory;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;

import com.ryanharter.auto.value.gson.GsonTypeAdapterFactory;

@GsonTypeAdapterFactory
public abstract class ModelAutoGsonAdapterFactory implements TypeAdapterFactory {
    public static TypeAdapterFactory create() {
        final TypeAdapterFactory factory = new AutoValueGson_ModelAutoGsonAdapterFactory();

        return new TypeAdapterFactory() {
            @Override
            public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
                TypeAdapter<T> typeAdapter = factory.create(gson, type);
                return typeAdapter != null ? typeAdapter.nullSafe() : null;
            }
        };
    }
}

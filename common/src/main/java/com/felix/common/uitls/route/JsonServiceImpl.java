package com.felix.common.uitls.route;

import com.google.gson.Gson;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.facade.service.SerializationService;

import android.content.Context;

import java.lang.reflect.Type;

/**
 * Created by chaichuanfa on 2019/1/16
 */
@Route(path = "/mashup/json")
public class JsonServiceImpl implements SerializationService {

    private Gson mGson;

    @Override
    public void init(Context context) {
        mGson = new Gson();
    }

    @Override
    public <T> T json2Object(String text, Class<T> clazz) {
        return mGson.fromJson(text, clazz);
    }

    @Override
    public String object2Json(Object instance) {
        return mGson.toJson(instance);
    }

    @Override
    public <T> T parseObject(String input, Type clazz) {
        return mGson.fromJson(input, clazz);
    }
}

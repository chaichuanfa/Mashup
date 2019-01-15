package com.felix.model.user_info.types;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

/**
 * Created by chaichuanfa on 2019/1/15
 */
@AutoValue
public abstract class UserInfo {

    public abstract long uid();

    public abstract String username();

    public abstract String avatar();

    public static TypeAdapter<UserInfo> typeAdapter(final Gson gson) {
        return new AutoValue_UserInfo.GsonTypeAdapter(gson);
    }
}

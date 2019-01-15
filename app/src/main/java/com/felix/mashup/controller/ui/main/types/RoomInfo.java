package com.felix.mashup.controller.ui.main.types;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

/**
 * Created by chaichuanfa on 2019/1/15
 */
@AutoValue
public abstract class RoomInfo {

    public abstract long room_id();

    public static TypeAdapter<RoomInfo> typeAdapter(final Gson gson) {
        return new AutoValue_RoomInfo.GsonTypeAdapter(gson);
    }
}

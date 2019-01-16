package com.felix.mashup.controller.main.types;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

import android.os.Parcelable;


/**
 * Created by chaichuanfa on 2019/1/15
 */
@AutoValue
public abstract class RoomInfo implements Parcelable {

    public abstract long room_id();

    public static RoomInfo create(long room_id) {
        return new AutoValue_RoomInfo(room_id);
    }

    public static TypeAdapter<RoomInfo> typeAdapter(final Gson gson) {
        return new AutoValue_RoomInfo.GsonTypeAdapter(gson);
    }
}

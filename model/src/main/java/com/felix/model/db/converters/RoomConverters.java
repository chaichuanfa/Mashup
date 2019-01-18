package com.felix.model.db.converters;

import org.threeten.bp.Instant;
import org.threeten.bp.ZoneId;
import org.threeten.bp.ZonedDateTime;

import android.arch.persistence.room.TypeConverter;

/**
 * Created by chaichuanfa on 2019/1/17
 */
public class RoomConverters {

    @TypeConverter
    public static ZonedDateTime fromTimestamp(Long value) {
        return value == null ? null
                : ZonedDateTime.ofInstant(Instant.ofEpochSecond(value), ZoneId.systemDefault());
    }

    @TypeConverter
    public static Long dateToTimestamp(ZonedDateTime date) {
        return date == null ? null : date.toEpochSecond();
    }

}

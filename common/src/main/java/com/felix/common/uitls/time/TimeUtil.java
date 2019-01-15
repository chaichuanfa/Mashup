package com.felix.common.uitls.time;

import org.threeten.bp.ZoneId;
import org.threeten.bp.ZonedDateTime;

/**
 * Created by chaichuanfa on 2019/1/15
 */
public final class TimeUtil {

    private TimeUtil() {
    }

    public static ZonedDateTime utc2DefaultZone(ZonedDateTime utcDateTime) {
        return utcDateTime.withZoneSameInstant(ZoneId.systemDefault());
    }
}

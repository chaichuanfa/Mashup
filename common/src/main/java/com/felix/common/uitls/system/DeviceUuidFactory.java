package com.felix.common.uitls.system;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.provider.Settings.Secure;

import java.io.UnsupportedEncodingException;
import java.util.UUID;

public final class DeviceUuidFactory {

    private DeviceUuidFactory() {
        //no instance
    }

    protected static final String PREFS_FILE = "device_id";

    protected static final String PREFS_DEVICE_ID = "device_id";

    protected static volatile UUID uuid;

    public static void init(Context context) {
        if (uuid == null) {
            synchronized (DeviceUuidFactory.class) {
                if (uuid == null) {
                    final SharedPreferences prefs = context
                            .getSharedPreferences(PREFS_FILE, Context.MODE_PRIVATE);
                    final String id = prefs.getString(PREFS_DEVICE_ID, null);
                    if (id != null) {
                        // Use the ids previously computed and stored in the
                        // prefs file
                        uuid = UUID.fromString(id);
                    } else {
                        @SuppressLint("HardwareIds") final String androidId = Secure.getString(
                                context.getContentResolver(), Secure.ANDROID_ID);
                        // Use the Android ID unless it's broken, in which case
                        // fallback on deviceId,
                        // unless it's not available, then fallback on a random
                        // number which we store to a prefs file
                        try {
                            //ANDROID_ID有一个普遍发现的bug，这些有问题的手机相同的ANDROID_ID: 9774d56d682e549c
                            //如果返厂的手机，或者被root的手机，可能会变
                            if (!"9774d56d682e549c".equals(androidId)) {
                                uuid = UUID.nameUUIDFromBytes(androidId.getBytes("utf8"));
                            } else {
                                uuid = UUID.randomUUID();
                            }
                        } catch (UnsupportedEncodingException e) {
                            throw new RuntimeException(e);
                        }
                        // Write the value out to the prefs file
                        prefs.edit().putString(PREFS_DEVICE_ID, uuid.toString()).apply();
                    }
                }
            }
        }
    }

    public static UUID getDeviceUuid() {
        return uuid;
    }
}

package com.felix.common.uitls;

import com.facebook.common.internal.Supplier;
import com.facebook.common.util.ByteConstants;
import com.facebook.imagepipeline.cache.MemoryCacheParams;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;

/**
 * Created by chaichuanfa on 17/4/28.
 */

public class FrescoCacheParams implements Supplier<MemoryCacheParams> {

    private static final int MAX_CACHE_ENTRIES = 128;

    private static final int MAX_CACHE_ENTRIES_LOLLIPOP = 50;

    private static final int MAX_CACHE_EVICTION_SIZE = 10 * ByteConstants.MB;

    private static final int MAX_CACHE_EVICTION_ENTRIES = 20;

    private int maxSize;

    public FrescoCacheParams(Context context) {
        ActivityManager activityManager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);
        maxSize = getMaxCacheSize(activityManager);
    }

    @Override
    public MemoryCacheParams get() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            return new MemoryCacheParams(
                    maxSize,                                // 内存缓存中总图片的最大大小,以字节为单位。
                    MAX_CACHE_ENTRIES_LOLLIPOP,             // 内存缓存中图片的最大数量。
                    MAX_CACHE_EVICTION_SIZE,                // 内存缓存中准备清除但尚未被删除的总图片的最大大小,以字节为单位。
                    MAX_CACHE_EVICTION_ENTRIES,             // 内存缓存中准备清除的总图片的最大数量。
                    Integer.MAX_VALUE);                     // 内存缓存中单个图片的最大大小。
        } else {
            return new MemoryCacheParams(
                    maxSize,
                    MAX_CACHE_ENTRIES,
                    Integer.MAX_VALUE,
                    Integer.MAX_VALUE,
                    Integer.MAX_VALUE);
        }
    }

    private int getMaxCacheSize(ActivityManager activityManager) {
        final int maxMemory = Math.min(activityManager.getMemoryClass()
                * ByteConstants.MB, Integer.MAX_VALUE);
        if (maxMemory < 32 * ByteConstants.MB) {
            return 6 * ByteConstants.MB;
        } else if (maxMemory < 64 * ByteConstants.MB) {
            return 11 * ByteConstants.MB;
        } else {
            if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.GINGERBREAD) {
                return 8 * ByteConstants.MB;
            } else {
                return maxMemory / 5;
            }
        }
    }
}

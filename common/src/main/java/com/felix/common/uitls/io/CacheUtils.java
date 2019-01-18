package com.felix.common.uitls.io;

import android.content.Context;
import android.support.annotation.UiThread;
import android.text.format.Formatter;
import android.webkit.WebView;

import java.io.File;

/**
 * Created by chaichuanfa on 2017/11/30.
 */

public final class CacheUtils {


    public static final String FRESCO_CACHE_DIR = "image_cache";

    public static final String FRESCO_SMALL_IMAGE_CACHE_DIR = "fresco_small_image_cache";

    private CacheUtils() {
    }

    /**
     * 清除内外部缓存
     */
    public static boolean clearCache(Context context) {

        File internalCache = new File(PathUtil.getInternalCacheDir(context, ""));
        if (internalCache.isDirectory()) {
            deleteDir(internalCache);
        }

        cleanAppWebView(context);

        if (PathUtil.isSDCardVisable()) {
            //外部存储缓存：Android/data/com.tongzhuo.tongzhuogame.international/cache/
            File externalCache = new File(PathUtil.getExternalCacheDir(context, ""));
            if (externalCache.isDirectory()) {
                deleteDir(externalCache);
            }
        }
        return true;
    }

    private static void cleanAppWebView(Context context) {
        File fileCache = new File(context.getCacheDir().getParent() + "/app_webview");
        if (fileCache.exists() && fileCache.isDirectory()) {
            deleteDir(fileCache);
        }
    }

    @UiThread
    public static void clearWebViewCache(Context context) {
        WebView webview = new WebView(context);
        webview.clearCache(false);
        webview.destroy();
    }

    /**
     * 获取内部存储缓存和外部存储缓存总大小
     */
    public static long getCacheSize(Context context) {
        long internalCacheSize = getInternalCacheSize(context);
        long appWebViewCacheSize = getAppWebViewCacheSize(context);
        long externalCacheSize = 0;
        if (PathUtil.isSDCardVisable()) {
            externalCacheSize = getFolderSize(new File(PathUtil.getExternalCacheDir(context, "")));
        }
        return internalCacheSize + appWebViewCacheSize + externalCacheSize;
    }

    private static long getAppWebViewCacheSize(Context context) {
        File fileCache = new File(context.getCacheDir().getParent() + "/app_webview");
        if (fileCache.exists() && fileCache.isDirectory()) {
            try {
                return getFolderSize(fileCache);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    private static long getInternalCacheSize(Context context) {
        long size = 0;
        try {
            File internalCache = new File(PathUtil.getInternalCacheDir(context, ""));
            if (internalCache.isDirectory()) {
                size = getFolderSize(internalCache);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return size;
    }

    public static long getFolderSize(File file) {
        long size = 0;
        try {
            File[] fileList = file.listFiles();
            for (int i = 0; i < fileList.length; i++) {
                if (fileList[i].isDirectory()) {
                    size = size + getFolderSize(fileList[i]);
                } else {
                    size = size + fileList[i].length();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return size;
    }

    public static String formatFileSize(Context context, long fileSize) {
        return fileSize == 0 ? "0M" : Formatter.formatFileSize(context, fileSize);
    }

    public static boolean deleteDir(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            for (String child : children) {
                boolean success = deleteDir(new File(dir, child));
                if (!success) {
                    return false;
                }
            }
        }
        return dir.delete();
    }
}
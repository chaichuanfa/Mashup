package com.felix.common.uitls.io;

import android.content.Context;
import android.os.Environment;

import java.io.File;

public final class PathUtil {

    private PathUtil() {
        //no instance
    }

    public static final String APP_NAME = "Mashup";

    private static final String CACHE_DIR = APP_NAME;

    /**
     * check whether external storage visible
     */
    public static boolean isSDCardVisable() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

    /**
     * 外部存储，App删除后会删除该目录下文件
     * Android/data/packagename/files/
     * cache dir
     */
    public static String getExternalFilesDir(Context context, String dirName) {
        File dir = context.getExternalFilesDir(null);
        if (dir == null) {
            dir = context.getFilesDir();
        }
        String footDir = dir.getAbsolutePath();
        File file = new File(footDir + File.separator + dirName);
        if (!file.exists()) {
            file.mkdirs();
        }
        return file.getAbsolutePath();
    }

    /**
     * app删除后不保留
     * Android/data/packagename/cache/
     */
    public static String getExternalCacheDir(Context context, String dirName) {
        File dir = context.getExternalCacheDir();
        if (dir == null) {
            dir = context.getCacheDir();
        }
        String footDir = dir.getAbsolutePath();
        File path = new File(footDir + File.separator + dirName);
        if (!path.exists()) {
            path.mkdirs();
        }
        return path.getAbsolutePath();
    }

    /**
     * 内部存储，App删除后不保留，用户不可见，root后可见
     */
    public static String getInternalCacheDir(Context context, String dirName) {
        File dir = context.getCacheDir();
        String footDir = dir.getAbsolutePath();
        File path = new File(footDir + File.separator + dirName);
        if (!path.exists()) {
            path.mkdirs();
        }
        return path.getAbsolutePath();
    }

    /**
     * 内部存储，App删除后不保留，用户不可见，root后可见
     */
    public static String getInternalFileDir(Context context, String dirName) {
        File dir = context.getFilesDir();
        String footDir = dir.getAbsolutePath();
        File path = new File(footDir + File.separator + dirName);
        if (!path.exists()) {
            path.mkdirs();
        }
        return path.getAbsolutePath();
    }

    /**
     * 外部存储，App删除后依然可以保留文件
     * sdcard/QwikMatch
     */
    public static String getExternalStorageDirectory() {
        String footDir = Environment.getExternalStorageDirectory().getAbsolutePath();
        File file = new File(footDir + File.separator + CACHE_DIR);
        if (!file.exists()) {
            file.mkdirs();
        }
        return footDir + File.separator + CACHE_DIR;
    }

    public static String getSystemDownloadDirectory() {
        File file = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        if (!file.exists()) {
            file.mkdirs();
        }
        return file.getAbsolutePath();
    }

    private static boolean deleteDir(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            for (String aChildren : children) {
                boolean success = deleteDir(new File(dir, aChildren));
                if (!success) {
                    return false;
                }
            }
        }
        return dir.delete();
    }

}
package com.felix.common.uitls.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by chaichuanfa on 2019/1/18
 * 用户退出清空
 */
public final class PrefUtils {

    private static final String DELIMITER = "‚‗‚";

    protected static Context mContext = null;

    protected static SharedPreferences mSharedPreferences = null;

    private PrefUtils() {
        //no instance
    }

    public static Context getContext() {
        return mContext;
    }

    public static void setContext(Context context) {
        mContext = context;
    }

    public static SharedPreferences getSettings() {
        if (mSharedPreferences == null) {
            mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
        }
        return mSharedPreferences;
    }

    public static boolean contains(final String key) {
        return getSettings().contains(key);
    }

    public static String getSettingString(final String key,
            final String defaultValue) {
        return getSettings().getString(key, defaultValue);
    }

    public static boolean getSettingBoolean(final String key,
            final boolean defaultValue) {
        return getSettings().getBoolean(key, defaultValue);
    }

    public static int getSettingInt(final String key, final int defaultValue) {
        return getSettings().getInt(key, defaultValue);
    }

    public static long getSettingLong(final String key, final long defaultValue) {
        return getSettings().getLong(key, defaultValue);
    }

    public static boolean setSettingString(final String key, final String value) {
        final SharedPreferences.Editor settingsEditor = getSettings().edit();
        settingsEditor.putString(key, value);
        return settingsEditor.commit();
    }

    public static float getSettingFloat(final String key, final float defaultValue) {
        return getSettings().getFloat(key, defaultValue);
    }

    public static boolean setSettingFloat(final String key, final float value) {
        final SharedPreferences.Editor settingsEditor = getSettings().edit();
        settingsEditor.putFloat(key, value);
        return settingsEditor.commit();
    }

    public static boolean setSettingBoolean(final String key,
            final boolean value) {
        final SharedPreferences.Editor settingsEditor = getSettings().edit();
        settingsEditor.putBoolean(key, value);
        return settingsEditor.commit();
    }

    public static boolean setSettingInt(final String key, final int value) {
        final SharedPreferences.Editor settingsEditor = getSettings().edit();
        settingsEditor.putInt(key, value);
        return settingsEditor.commit();
    }

    public static boolean setSettingLong(final String key, final long value) {
        final SharedPreferences.Editor settingsEditor = getSettings().edit();
        settingsEditor.putLong(key, value);
        return settingsEditor.commit();
    }

    public static void putList(String key, List<String> marray) {

        SharedPreferences.Editor editor = getSettings().edit();
        String[] mystringlist = marray.toArray(new String[marray.size()]);
        // the comma like character used below is not a comma it is the SINGLE
        // LOW-9 QUOTATION MARK unicode 201A and unicode 2017 they are used for
        // seprating the items in the list
        editor.putString(key, TextUtils.join(DELIMITER, mystringlist));
        editor.apply();
    }

    public static ArrayList<String> getList(String key) {
        String[] mylist = TextUtils.split(getSettings().getString(key, ""), DELIMITER);
        return new ArrayList<>(Arrays.asList(mylist));
    }

    public static void putListInt(String key, ArrayList<Integer> marray) {
        SharedPreferences.Editor editor = getSettings().edit();
        Integer[] mystringlist = marray.toArray(new Integer[marray.size()]);
        editor.putString(key, TextUtils.join(DELIMITER, mystringlist));
        editor.apply();
    }

    public static void putListLong(String key, ArrayList<Long> marray) {
        SharedPreferences.Editor editor = getSettings().edit();
        Long[] mystringlist = marray.toArray(new Long[marray.size()]);
        editor.putString(key, TextUtils.join(DELIMITER, mystringlist));
        editor.apply();
    }

    public static ArrayList<Integer> getListInt(String key) {
        String[] mylist = TextUtils
                .split(getSettings().getString(key, ""), DELIMITER);
        ArrayList<String> gottenlist = new ArrayList<String>(
                Arrays.asList(mylist));
        ArrayList<Integer> gottenlist2 = new ArrayList<Integer>();
        for (int i = 0; i < gottenlist.size(); i++) {
            gottenlist2.add(Integer.parseInt(gottenlist.get(i)));
        }

        return gottenlist2;
    }

    public static ArrayList<Long> getListLong(String key) {
        String[] mylist = TextUtils
                .split(getSettings().getString(key, ""), DELIMITER);
        ArrayList<String> gottenlist = new ArrayList<String>(
                Arrays.asList(mylist));
        ArrayList<Long> gottenlist2 = new ArrayList<Long>();
        for (int i = 0; i < gottenlist.size(); i++) {
            gottenlist2.add(Long.parseLong(gottenlist.get(i)));
        }

        return gottenlist2;
    }

    public static void putListBoolean(String key, ArrayList<Boolean> marray) {
        ArrayList<String> origList = new ArrayList<String>();
        for (Boolean b : marray) {
            if (b) {
                origList.add("true");
            } else {
                origList.add("false");
            }
        }
        putList(key, origList);
    }

    public static ArrayList<Boolean> getListBoolean(String key) {
        ArrayList<String> origList = getList(key);
        ArrayList<Boolean> mBools = new ArrayList<Boolean>();
        for (String b : origList) {
            if (b.equals("true")) {
                mBools.add(true);
            } else {
                mBools.add(false);
            }
        }
        return mBools;
    }


    public static void clearAll() {
        getSettings().edit().clear().apply();
    }

    public static void removeKey(String key) {
        getSettings().edit().remove(key).apply();
    }

}

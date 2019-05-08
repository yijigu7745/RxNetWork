package cn.com.yijigu.rxnetwork.datamanager;

import android.content.Context;
import android.content.SharedPreferences;

import cn.com.yijigu.rxnetwork.application.RetrofitUtilsApplication;

/**
 * @author yijigu
 */
public class DataManager {
    private static final String NAME = "RetrofitUtilsApplication";
    private static final String COOKIE = "cookie";

    public static String getCookie() {
        SharedPreferences preferences = RetrofitUtilsApplication.getRetrofitUtilsContext().getSharedPreferences(NAME, Context.MODE_PRIVATE);
        return preferences.getString(COOKIE, "");
    }
    public static void putCookie( String value) {
        SharedPreferences preferences = RetrofitUtilsApplication.getRetrofitUtilsContext().getSharedPreferences(NAME, Context.MODE_PRIVATE);
        preferences.edit().putString(COOKIE, value).commit();
    }

    public static boolean getCustomValue(String customName) {
        SharedPreferences preferences = RetrofitUtilsApplication.getRetrofitUtilsContext().getSharedPreferences(NAME, Context.MODE_PRIVATE);
        return preferences.getBoolean(customName,false);
    }
    public static void putCustomValue(String customName, boolean customValue) {
        SharedPreferences preferences = RetrofitUtilsApplication.getRetrofitUtilsContext().getSharedPreferences(NAME, Context.MODE_PRIVATE);
        preferences.edit().putBoolean(customName, customValue).commit();
    }



}

package cn.com.yijigu.rxnetwork.application;

import android.app.Application;
import android.content.SharedPreferences;

/**
 * @author yijigu
 */
public class RetrofitUtilsApplication {
    private static Application mInstance;

    public static RetrofitUtilsApplication mContext;

    public void onCreate(Application application) {
        mContext = this;
        mInstance = application;
    }

    public static RetrofitUtilsApplication getRetrofitUtilsContext() {
        return mContext;
    }
    public SharedPreferences getSharedPreferences(String spName, int modePrivate) {
        return mInstance.getSharedPreferences(spName,modePrivate);
    }
}

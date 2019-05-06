package cn.com.yijigu.rxnetwork.application;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import java.util.LinkedList;
import java.util.List;

public class BaseApplication  extends Application {
    private static BaseApplication mInstance;

    private List<Activity> activities = new LinkedList<Activity>();

    public static Context mContext;
    /** 主线程ID */
    private static int mMainThreadId = -1;
    /** 主线程 */
    private static Thread mMainThread;
    /** 主线程Handler */
    private static Handler mMainThreadHandler;

    /** 主线程Looper */
    private static Looper mMainLooper;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        mMainThreadId = android.os.Process.myTid();
        mMainThread = Thread.currentThread();
        mMainThreadHandler = new Handler();
        mMainLooper = getMainLooper();
        mInstance = this;
    }

    public static Context getAppContext() {
        return mContext;
    }

    /***
     * 添加一个activty
     *
     * @param activity 需要添加的activity
     */
    public void addActivity(Activity activity) {
        activities.add(activity);
    }

    /***
     * 退出应用
     */
    public void exitApp() {
        for (Activity activity : activities) {
            activity.finish();
            activity = null;
        }
        activities.clear();
        System.exit(0);
        ActivityManager activityMgr = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        activityMgr.restartPackage(getPackageName());
    }

    public static BaseApplication getApplication() {
        return mInstance;
    }

    /** 获取主线程ID */
    public static int getMainThreadId() {
        return mMainThreadId;
    }

    /** 获取主线程 */
    public static Thread getMainThread() {
        return mMainThread;
    }

    /** 获取主线程的handler */
    public static Handler getMainThreadHandler() {
        return mMainThreadHandler;
    }

    /** 获取主线程的looper */
    public static Looper getMainThreadLooper() {
        return mMainLooper;
    }
}

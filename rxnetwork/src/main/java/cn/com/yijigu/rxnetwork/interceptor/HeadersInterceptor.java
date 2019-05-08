package cn.com.yijigu.rxnetwork.interceptor;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;

import java.io.IOException;
import java.lang.ref.SoftReference;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import cn.com.yijigu.rxnetwork.R;
import cn.com.yijigu.rxnetwork.datamanager.DataManager;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author yijigu
 */
public class HeadersInterceptor implements Interceptor{
    private final static String TAG = "HeadersInterceptor";
    private SoftReference<Context> rxActivity;
    private static Headers headers;
    private Map<String,List<String>> listMap;

    public HeadersInterceptor(Context activity) {
        this.rxActivity = new SoftReference(activity);
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        String cookie = DataManager.getCookie();

        Request original = chain.request();
        Request request = null;
        Request.Builder builder = original.newBuilder();

        if (!cookie.isEmpty()){
            builder.addHeader("Cookie",cookie);
            Log.i(TAG, cookie);
        }

        //请求头，固定写法
        builder .header("Content-Type", "application/json; charset=utf-8");
        Log.i(TAG, "application/json; charset=utf-8");

        //请求头，固定写法
        builder.header("user-agent",getCurrentUserAgent());
        Log.i(TAG, getCurrentUserAgent());
        request=builder.build();



        //调试用的应用请求，打印，可以输出请求头的参数
        System.out.println("=====================================================");
        long t1 = System.nanoTime();
        String requestHeader = String.format(">>>>>Sending request %s on %s%n%s",
                request.url(), chain.connection(), request.headers());
        Log.i(TAG,requestHeader);



        Response response = chain.proceed(request);
        long t2 = System.nanoTime();
        String responseHeader =   String.format(">>>>>Received response for %s in %.1fms%n%s",
                response.request().url(), (t2 - t1) / 1e6d, response.headers());
        Log.i(TAG,responseHeader);
        System.out.println("=====================================================");

        return response;
    }

    /**
     * 请求头固定写法
     * @return
     */
    @SuppressLint("StringFormatInvalid")
    private String getCurrentUserAgent() {

        Locale locale = Locale.getDefault();

        StringBuffer buffer = new StringBuffer();
        // Add version
        final String version = Build.VERSION.RELEASE;
        if (version.length() > 0) {
            buffer.append(version);
        } else {
            // default to "1.0"
            buffer.append("1.0");
        }
        buffer.append("; ");
        final String language = locale.getLanguage();
        if (language != null) {
            buffer.append(language.toLowerCase());
            final String country = locale.getCountry();
            if (country != null) {
                buffer.append("-");
                buffer.append(country.toLowerCase());
            }
        } else {
            // default to "en"
            buffer.append("en");
        }
        // add the model for the release build
        if ("REL".equals(Build.VERSION.CODENAME)) {
            final String model = Build.MODEL;
            if (model.length() > 0) {
                buffer.append("; ");
                buffer.append(model);
            }
        }
        final String id = Build.ID;
        if (id.length() > 0) {
            buffer.append(" Build/");
            buffer.append(id);
        }


        buffer.append(" traffic/" + getVersionName(rxActivity.get()) + " ");

        String base = rxActivity.get().getResources().getString(
                R.string.web_user_agent);
        return String.format(base, version, buffer);
    }
    /**
     * 获取软件版本号
     *
     * @param a
     * @return
     */
    public static String getVersionName(Context a) {
        String version = "1.0";
        try {
            String pkgName = a.getPackageName();
            PackageManager pm = a.getPackageManager();
            version = pm.getPackageInfo(pkgName, 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return version;
    }
}

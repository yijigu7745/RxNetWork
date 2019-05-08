package cn.com.yijigu.rxnetwork.network;

import android.content.Context;

import java.util.concurrent.TimeUnit;

import cn.com.yijigu.rxnetwork.interceptor.CacheInterceptor;
import cn.com.yijigu.rxnetwork.interceptor.CookieInterceptor;
import cn.com.yijigu.rxnetwork.interceptor.HeadersInterceptor;
import cn.com.yijigu.rxnetwork.utils.Constants;
import okhttp3.OkHttpClient;

/**
 * @author yijigu
 */
public class HttpProvider {

    static OkHttpClient okHttpClient;

    public static OkHttpClient Builder(Context context){
        if (okHttpClient==null) {
            synchronized (HttpProvider.class) {
                if (okHttpClient==null) {
                    OkHttpClient client = new OkHttpClient.Builder()
                            .retryOnConnectionFailure(true)
                            .addNetworkInterceptor(new CacheInterceptor())
                            .cache(new CacheProvider(context).provideCache())
                            .connectTimeout(Constants.DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                            .addInterceptor(new CookieInterceptor())
                            .addInterceptor(new HeadersInterceptor(context))
                            .build();
                    okHttpClient = client;
                }
            }
        }
        return okHttpClient;
    }

    public static boolean checkNULL(String str)
    {
        return ((str == null) || ("null".equals(str)) || ("".equals(str)));
    }
}

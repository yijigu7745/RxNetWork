package cn.com.yijigu.rxnetwork.retrofit;

import android.content.Context;

import cn.com.yijigu.rxnetwork.network.HttpProvider;
import cn.com.yijigu.rxnetwork.utils.StringUtils;
import cn.com.yijigu.rxnetwork.view.IView;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Retrofit网络请求接口
 * @author yijigu
 */
public class RetrofitUtils {
    private static Retrofit mRetrofit;

    /**
     * @param url 请求路径
     * @param iView Activity的引用
     * @param iService 请求接口的引用
     * @param <T>
     * @return
     */
    public static <T> T getInterface(String url, IView iView, Class<T> iService){

        if(StringUtils.isEmpty(url)){
            throw new NullPointerException("url can't be null");
        }

        if(iView == null){
            throw new NullPointerException("IView can't be null");
        }

        mRetrofit = new Retrofit.Builder()
                .client(HttpProvider.Builder((Context) iView))
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return mRetrofit.create(iService);
    }
}

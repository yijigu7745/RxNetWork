package cn.com.yijigu.rxnetwork.retrofit;

import android.content.Context;

import cn.com.yijigu.rxnetwork.datamanager.IService;
import cn.com.yijigu.rxnetwork.network.HttpProvider;
import cn.com.yijigu.rxnetwork.utils.StringUtils;
import cn.com.yijigu.rxnetwork.view.IView;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitUtils {
    private static Retrofit mRetrofit;

    public static IService getInterface(String url, IView iView, IService iService){

        if(StringUtils.isEmpty(url)){
            throw new NullPointerException("url can't be null");
        }

        if(iView == null){
            throw new NullPointerException("IView can't be null");
        }

        if(iService == null){
            throw new NullPointerException("IService can't be null");
        }

        mRetrofit = new Retrofit.Builder()
                .client(HttpProvider.Builder((Context) iView))
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return mRetrofit.create(iService.getClass());
    }
}

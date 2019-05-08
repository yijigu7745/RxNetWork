package cn.com.yijigu.rxnetwork.interceptor;

import java.io.IOException;

import cn.com.yijigu.rxnetwork.network.HttpProvider;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author yijigu
 */
public class CacheInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Response response = chain.proceed(request);
        String cache = request.header("Cache-Time");
        if (!HttpProvider.checkNULL(cache)){
            Response response1 = response.newBuilder()
                    .removeHeader("Pargma")
                    .removeHeader("Cache-Control")
                    .header("Cache-Control","max-age="+cache)
                    .build();
            return response1;
        }
        return response;
    }
}

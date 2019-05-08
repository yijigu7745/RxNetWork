package cn.com.yijigu.rxnetworkframework.login.presenter;


import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.concurrent.TimeUnit;

import cn.com.yijigu.rxnetwork.interceptor.CookieInterceptor;
import cn.com.yijigu.rxnetwork.retrofit.RetrofitUtils;
import cn.com.yijigu.rxnetworkframework.constants.SealConstants;
import cn.com.yijigu.rxnetworkframework.login.datamanager.LoginService;
import cn.com.yijigu.rxnetworkframework.login.model.UserModel;
import cn.com.yijigu.rxnetworkframework.login.view.ILoginView;
import cn.com.yijigu.rxnetworkframework.utils.StringUtils;
import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class LoginPresenterCompl implements ILoginPresenter {

    private ILoginView iLoginView;

    public LoginPresenterCompl(ILoginView iLoginView) {
        this.iLoginView = iLoginView;
    }

    @Override
    public Observable login(String userName, String password) {

        if (StringUtils.isEmpty(userName) || StringUtils.isEmpty(password)){
            return Observable.error(NullPointerException::new);
        }

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        httpClient.connectTimeout(SealConstants.DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .addInterceptor(new CookieInterceptor());

        Retrofit retrofit = new Retrofit.Builder()
                .client(httpClient.build())
                .baseUrl(SealConstants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        LoginService loginService = retrofit.create(LoginService.class);
        String safeUserPwd;
        safeUserPwd = new String(Hex.encodeHex(DigestUtils.md5(password)));
//        Observable<UserModel> observable = loginService.getUserLogin(userName,safeUserPwd);
        Observable<UserModel> observable = RetrofitUtils.getInterface(SealConstants.BASE_URL,
                iLoginView,LoginService.class).getUserLogin(userName,safeUserPwd);

        return observable;
    }

    @Override
    public void clear() {
        iLoginView.clearText();
    }


}

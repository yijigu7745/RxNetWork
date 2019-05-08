package cn.com.yijigu.rxnetworkframework.utils;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.ResponseBody;

public class JSONTOBean {

    private final static JSONTOBean instance = new JSONTOBean();

    private JSONTOBean(){

    }

    public static JSONTOBean getInstance(){
        return instance;
    }

    public  Object JSONTOBean(ResponseBody body, Class clazz){

        Object  obj=null;
        try {
            String json = new String(body.bytes());
            Gson gson  =new Gson();
            obj=gson.fromJson(json,clazz);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return obj;
    }
}

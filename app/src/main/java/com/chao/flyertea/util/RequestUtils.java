package com.chao.flyertea.util;

import com.chao.flyertea.App;
import com.chao.flyertea.net.FlyerteaApi;
import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RequestUtils {

    private static final String TAG = "XUCHAO";

    private static PersistentCookieJar cookieJar = new PersistentCookieJar(new SetCookieCache(), new
            SharedPrefsCookiePersistor(App.getInstance()));

    /**
     * 构建retrofit 请求
     *
     * @return
     */
    public static FlyerteaApi createRequest(final HashMap<String, String> headers) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(genericClient(headers))
                .build();
        return retrofit.create(FlyerteaApi.class);
    }


    private static OkHttpClient genericClient(final HashMap<String, String> map) {
        return new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request originRequest = chain.request();
                HttpUrl.Builder builder = originRequest.url().newBuilder();
                Iterator it = map.entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry entry = (Map.Entry) it.next();
                    String key = (String) entry.getKey();
                    String value = (String) entry.getValue();
                    builder.addQueryParameter(key, value);
                }
                HttpUrl url = builder.build();
                Request.Builder requestBuilder = originRequest.newBuilder().url(url);
                Request request1 = requestBuilder.build();
                return chain.proceed(request1);
            }
        }).cookieJar(cookieJar).build();
    }

    /**
     * 构建GET 请求的公共参数
     *
     * @return
     */
    public static HashMap<String, String> createRequestParams() {
        HashMap<String, String> params = new HashMap<>();
        params.put("appkey", Constants.APPKEY);
        params.put("appversion", Constants.APPVERSION);
        params.put("token", Constants.TOKEN);
        return params;
    }

}

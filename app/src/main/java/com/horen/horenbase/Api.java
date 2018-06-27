package com.horen.horenbase;


import android.util.SparseArray;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.horen.base.app.BaseApplication;
import com.horen.horenbase.converter.CustomConverterFactory;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * des:retorfit api
 * Created by xsf
 * on 2016.06.15:47
 */
public class Api {
    //连接时长，单位：毫秒
    public static final int CONNECT_TIME_OUT = 1000 * 30;
    public Retrofit retrofit;
    public ApiService movieService;

    private static SparseArray<Api> sRetrofitManager = new SparseArray<>(3);

    /**
     * 箱箱共用 主要api url
     */
    public static final int HOREN_URL = 1001;

    /*************************缓存设置*********************/
/*
   1. noCache 不使用缓存，全部走网络

    2. noStore 不使用缓存，也不存储缓存

    3. onlyIfCached 只使用缓存

    4. maxAge 设置最大失效时间，失效则不使用 需要服务器配合

    5. maxStale 设置最大失效时间，失效则不使用 需要服务器配合 感觉这两个类似 还没怎么弄清楚，清楚的同学欢迎留言

    6. minFresh 设置有效时间，依旧如上

    7. FORCE_NETWORK 只走网络

    8. FORCE_CACHE 只走缓存*/

    /**
     * 设缓存有效期为两天
     */
    private static final long CACHE_STALE_SEC = 60 * 60 * 24 * 2;
    /**
     * 查询缓存的Cache-Control设置，为if-only-cache时只查询缓存而不会请求服务器，max-stale可以配合设置缓存失效时间
     * max-stale 指示客户机可以接收超出超时期间的响应消息。如果指定max-stale消息的值，那么客户机可接收超出超时期指定值之内的响应消息。
     */
    private static final String CACHE_CONTROL_CACHE = "only-if-cached, max-stale=" + CACHE_STALE_SEC;
    /**
     * 查询网络的Cache-Control设置，头部Cache-Control设为max-age=0
     * (假如请求了服务器并在a时刻返回响应结果，则在max-age规定的秒数内，浏览器将不会发送对应的请求到服务器，数据由缓存直接返回)时则不会使用缓存而请求服务器
     */
    private static final String CACHE_CONTROL_AGE = "max-age=0";


    //构造方法私有
    private Api(String baseUrl) {
        //开启Log
        HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor();
        logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        //缓存
        File cacheFile = new File(BaseApplication.getAppContext().getCacheDir(), "cache");
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 100); //100Mb
        //增加头部信息
        Interceptor headerInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request build = chain.request().newBuilder()
                        .addHeader("Accept", "application/json,application/xml,application/xhtml+xml,text/html;q=0.9,image/webp,*/*;q=0.8")
                        .addHeader("Accept-Encoding", "gzip, deflate")
                        .build();
                return chain.proceed(build);
            }
        };



        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(CONNECT_TIME_OUT, TimeUnit.MILLISECONDS)
                .connectTimeout(CONNECT_TIME_OUT, TimeUnit.MILLISECONDS)
                .writeTimeout(CONNECT_TIME_OUT, TimeUnit.MILLISECONDS)
//                .addInterceptor(mInterceptor)
                .addInterceptor(logInterceptor) // log拦截器
                .cache(cache)
                .build();
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                // 增加自定义解析
                .addConverterFactory(CustomConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(baseUrl)
                .build();
        movieService = retrofit.create(ApiService.class);
    }

    private static Interceptor mInterceptor=new Interceptor(){
        @Override
        public okhttp3.Response intercept(Chain chain)throws IOException{
            Request request=chain.request()
                    .newBuilder()
                    .addHeader("Content-Type","application/x-www-form-urlencoded; charset=UTF-8")
                    .addHeader("Accept-Encoding","gzip, deflate")
                    .addHeader("Connection","keep-alive")
                    .addHeader("Accept","*/*")
                    .addHeader("X-HB-Client-Type","ayb-android")
                    .addHeader("Content-Type","multipart/form-data; boundary=7db372eb000e2")
                    .build();
            return chain.proceed(request);
        }
    };


    /**
     * 获取 APiService
     */
    public static ApiService getDefult() {
        Api retrofitManager = sRetrofitManager.get(HOREN_URL);
        if (retrofitManager == null) {
            retrofitManager = new Api("http://api.hclyz.cn:81/");
            sRetrofitManager.put(HOREN_URL, retrofitManager);
        }
        return retrofitManager.retrofit.create(ApiService.class);
    }




}
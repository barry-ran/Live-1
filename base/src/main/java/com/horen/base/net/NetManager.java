package com.horen.base.net;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.horen.base.converter.CustomConverterFactory;

import java.util.concurrent.TimeUnit;

import me.jessyan.retrofiturlmanager.RetrofitUrlManager;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * @author :ChenYangYi
 * @date :2018/07/18/10:43
 * @description :
 * @github :https://github.com/chenyy0708
 */
public class NetManager {
    private OkHttpClient mOkHttpClient;
    private Retrofit mRetrofit;
    private LiveService liveService;
    private MovieService movieService;
    private SmallVideoService videoService;

    //连接时长，单位：毫秒
    private static final int CONNECT_TIME_OUT = 1000 * 30;

    private static class NetManagerHolder {
        private static final NetManager INSTANCE = new NetManager();
    }

    public static final NetManager getInstance() {
        return NetManagerHolder.INSTANCE;
    }

    private NetManager() {
        //开启Log
        HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor();
        logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        this.mOkHttpClient = RetrofitUrlManager.getInstance().with(new OkHttpClient.Builder()) //RetrofitUrlManager 初始化
                .readTimeout(CONNECT_TIME_OUT, TimeUnit.MILLISECONDS)
                .connectTimeout(CONNECT_TIME_OUT, TimeUnit.MILLISECONDS)
                .writeTimeout(CONNECT_TIME_OUT, TimeUnit.MILLISECONDS)
                .addInterceptor(logInterceptor) // log拦截器
                .build();
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        this.mRetrofit = new Retrofit.Builder()
                .baseUrl(UrlConstant.LIVE_SERVER)
                .addConverterFactory(CustomConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(mOkHttpClient)
                .build();
        this.liveService = mRetrofit.create(LiveService.class);
        this.movieService = mRetrofit.create(MovieService.class);
        this.videoService = mRetrofit.create(SmallVideoService.class);
    }

    public OkHttpClient getOkHttpClient() {
        return mOkHttpClient;
    }

    public Retrofit getRetrofit() {
        return mRetrofit;
    }

    public LiveService getLiveService() {
        return liveService;
    }

    public MovieService getMovieService() {
        return movieService;
    }

    public SmallVideoService getVideoService() {
        return videoService;
    }
}

package com.horen.horenbase.api;

import com.horen.horenbase.bean.BaseEntry;
import com.horen.horenbase.bean.DetailBean;
import com.horen.horenbase.bean.HomeBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by HOREN on 2017/11/15.
 */
public interface ApiService {
    /**
     * 直播平台列表
     */
    @GET("mf/json.txt")
    Observable<HomeBean> getHomeList();

    /**
     * 平台主播列表
     */
    @GET("mf/{url}")
    Observable<DetailBean> getDetailList(@Path("url") String url);

    /**
     * 视频首页
     */
    @GET("api/videos/listAll")
    Observable<BaseEntry<Object>> getMoviceList();

}
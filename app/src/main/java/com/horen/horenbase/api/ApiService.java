package com.horen.horenbase.api;

import com.horen.horenbase.bean.BaseEntry;
import com.horen.horenbase.bean.LiveDetail;
import com.horen.horenbase.bean.HomeLive;
import com.horen.horenbase.bean.HomeMovie;
import com.horen.horenbase.bean.HomeSearch;
import com.horen.horenbase.bean.SearchDetail;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by HOREN on 2017/11/15.
 */
public interface ApiService {
    /**
     * 直播平台列表
     */
    @GET("mf/json.txt")
    Observable<HomeLive> getHomeList();

    /**
     * 平台主播列表
     */
    @GET("mf/{url}")
    Observable<LiveDetail> getDetailList(@Path("url") String url);

    /**
     * 视频首页
     */
    @FormUrlEncoded
    @POST("api/videos/listAll")
    Observable<BaseEntry<HomeMovie>> getMoviceList(@FieldMap Map<String, String> parms);

    /**
     * 搜索视频
     */
    @GET(".")
    Observable<HomeSearch> searchVideo(@Query("k") String key, @Query("p") int page);

    /**
     * 视频详情
     */
    @GET(".")
    Observable<SearchDetail> videoDetail(@Query("url") String url);

}
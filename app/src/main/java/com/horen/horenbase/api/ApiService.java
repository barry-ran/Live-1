package com.horen.horenbase.api;

import com.horen.horenbase.bean.HomeLive;
import com.horen.horenbase.bean.HomeMovie;
import com.horen.horenbase.bean.HomeSearch;
import com.horen.horenbase.bean.LiveDetail;
import com.horen.horenbase.bean.SearchDetail;
import com.horen.horenbase.bean.d8.BaseEntry;
import com.horen.horenbase.bean.d8.SearchBean;
import com.horen.horenbase.bean.d8.VideoDetail;
import com.horen.horenbase.bean.d8.VideoPlayBean;

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

    /**
     * D8搜索视频
     */
    @GET("api/v2/videos")
    Observable<BaseEntry<SearchBean>> d8SearchVideo(@Query("title") String title, @Query("page") int page, @Query("per_page") int per_page);

    /**
     * D8视频相关信息
     */
    @GET("api/v2/video/{url}")
    Observable<BaseEntry<VideoDetail>> d8VideoDetail(@Path("url") String url);

    /**
     * D8视频播放链接
     */
    @GET("api/v2/video/channel/{url}")
    Observable<BaseEntry<VideoPlayBean>> d8VideoPlayUrl(@Path("url") String url, @Query("fingerprint") String fingerprint);

    /**
     * D8视频--首页
     */
    @GET("api/v2/videos/latest")
    Observable<BaseEntry<SearchBean>> d8HomeVideo(@Query("page") int page, @Query("per_page") int per_page);

    /**
     * D8视频--热门
     */
    @GET("api/v2/videos/rank")
    Observable<BaseEntry<SearchBean>> d8HotVideo();
}
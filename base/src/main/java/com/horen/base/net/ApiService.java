package com.horen.base.net;

import com.horen.domain.d8.NavigitionBean;
import com.horen.domain.live.HomeLive;
import com.horen.domain.HomeMovie;
import com.horen.domain.live.LiveDetail;
import com.horen.domain.d8.BaseEntry;
import com.horen.domain.d8.SearchBean;
import com.horen.domain.d8.VideoDetail;
import com.horen.domain.d8.VideoPlayBean;

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

    /**
     * D8视频--导航
     */
    @GET("api/v2/tags/alphabet")
    Observable<BaseEntry<NavigitionBean>> d8Navigation();

    /**
     * D8视频--根据tag获取视频列表
     */
    @GET("api/v2/videos")
    Observable<BaseEntry<SearchBean>> d8TagVideo(@Query("tag_name") String tag_name, @Query("page") int page, @Query("per_page") int per_page);


}
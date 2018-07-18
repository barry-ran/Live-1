package com.horen.base.net;

import com.horen.domain.d8.BaseEntry;
import com.horen.domain.d8.NavigitionBean;
import com.horen.domain.d8.SearchBean;
import com.horen.domain.d8.VideoDetail;
import com.horen.domain.d8.VideoPlayBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

import static me.jessyan.retrofiturlmanager.RetrofitUrlManager.DOMAIN_NAME_HEADER;

/**
 * Created by HOREN on 2017/11/15.
 */
public interface SmallVideoService {
    /**
     * D8搜索视频
     */
    @Headers({DOMAIN_NAME_HEADER + UrlConstant.SMALL_VIDEO})
    @GET("api/v2/videos")
    Observable<BaseEntry<SearchBean>> d8SearchVideo(@Query("title") String title, @Query("page") int page, @Query("per_page") int per_page);

    /**
     * D8视频相关信息
     */
    @Headers({DOMAIN_NAME_HEADER + UrlConstant.SMALL_VIDEO})
    @GET("api/v2/video/{url}")
    Observable<BaseEntry<VideoDetail>> d8VideoDetail(@Path("url") String url);

    /**
     * D8视频播放链接
     */
    @Headers({DOMAIN_NAME_HEADER + UrlConstant.SMALL_VIDEO})
    @GET("api/v2/video/channel/{url}")
    Observable<BaseEntry<VideoPlayBean>> d8VideoPlayUrl(@Path("url") String url, @Query("fingerprint") String fingerprint);

    /**
     * D8视频--首页
     */
    @Headers({DOMAIN_NAME_HEADER + UrlConstant.SMALL_VIDEO})
    @GET("api/v2/videos/latest")
    Observable<BaseEntry<SearchBean>> d8HomeVideo(@Query("page") int page, @Query("per_page") int per_page);

    /**
     * D8视频--热门
     */
    @Headers({DOMAIN_NAME_HEADER + UrlConstant.SMALL_VIDEO})
    @GET("api/v2/videos/rank")
    Observable<BaseEntry<SearchBean>> d8HotVideo();

    /**
     * D8视频--导航
     */
    @Headers({DOMAIN_NAME_HEADER + UrlConstant.SMALL_VIDEO})
    @GET("api/v2/tags/alphabet")
    Observable<BaseEntry<NavigitionBean>> d8Navigation();

    /**
     * D8视频--根据tag获取视频列表
     */
    @Headers({DOMAIN_NAME_HEADER + UrlConstant.SMALL_VIDEO})
    @GET("api/v2/videos")
    Observable<BaseEntry<SearchBean>> d8TagVideo(@Query("tag_name") String tag_name, @Query("page") int page, @Query("per_page") int per_page);
}
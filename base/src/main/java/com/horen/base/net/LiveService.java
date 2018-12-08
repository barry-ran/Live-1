package com.horen.base.net;

import com.horen.domain.live.HomeLive;
import com.horen.domain.live.HomeLive2;
import com.horen.domain.live.Live2Detail;
import com.horen.domain.live.LiveDetail;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

import static me.jessyan.retrofiturlmanager.RetrofitUrlManager.DOMAIN_NAME_HEADER;

/**
 * Created by HOREN on 2017/11/15.
 */
public interface LiveService {
    /**
     * 直播平台1列表
     */
    @Headers({DOMAIN_NAME_HEADER + UrlConstant.LIVE})
    @GET("mf/json.txt")
    Observable<HomeLive> getHomeList();

    /**
     * 直播平台2列表
     */
    @Headers({DOMAIN_NAME_HEADER + UrlConstant.LIVE_2})
    @POST("mobile/live/index")
    Observable<HomeLive2> getLive2List();

    /**
     * 平台主播列表
     */
    @Headers({DOMAIN_NAME_HEADER + UrlConstant.LIVE})
    @GET("mf/{url}")
    Observable<LiveDetail> getDetailList(@Path("url") String url);

    /**
     * 平台2主播列表
     */
    @Headers({DOMAIN_NAME_HEADER + UrlConstant.LIVE_2})
    @POST("mobile/live/anchors")
    Observable<Live2Detail> getLive2Detail(@Body RequestBody body);
}
package com.horen.base.net;

import com.horen.domain.live.HomeLive;
import com.horen.domain.live.LiveDetail;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

import static me.jessyan.retrofiturlmanager.RetrofitUrlManager.DOMAIN_NAME_HEADER;

/**
 * Created by HOREN on 2017/11/15.
 */
public interface LiveService {
    /**
     * 直播平台列表
     */
    @Headers({DOMAIN_NAME_HEADER + UrlConstant.LIVE})
    @GET("mf/json.txt")
    Observable<HomeLive> getHomeList();

    /**
     * 平台主播列表
     */
    @Headers({DOMAIN_NAME_HEADER + UrlConstant.LIVE})
    @GET("mf/{url}")
    Observable<LiveDetail> getDetailList(@Path("url") String url);
}
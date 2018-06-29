package com.horen.horenbase.api;

import com.horen.horenbase.bean.BaseEntry;
import com.horen.horenbase.bean.DetailBean;
import com.horen.horenbase.bean.HomeBean;
import com.horen.horenbase.bean.HomeMovie;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
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
    @FormUrlEncoded
    @POST("api/videos/listAll")
    Observable<BaseEntry<HomeMovie>> getMoviceList(@FieldMap Map<String, String> parms);

}
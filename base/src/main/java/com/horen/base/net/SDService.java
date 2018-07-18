package com.horen.base.net;

import com.horen.domain.sd.SDLiveList;
import com.horen.domain.sd.SDResponse;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

import static me.jessyan.retrofiturlmanager.RetrofitUrlManager.DOMAIN_NAME_HEADER;

/**
 * Created by HOREN on 2017/11/15.
 */
public interface SDService {

    /**
     * 平台主播列表
     */
    @Headers({DOMAIN_NAME_HEADER + UrlConstant.SD_LIVE})
    @POST("mapi/index.php")
    Observable<SDLiveList> getAllLive();

    /**
     * 通用请求地址
     */
    @Headers({DOMAIN_NAME_HEADER + UrlConstant.SD_LIVE})
    @FormUrlEncoded
    @POST("mapi/index.php")
    Observable<SDResponse> getLiveUrl(@Field("requestData") String requestData,
                                      @Field("i_type") String i_type, @Field("ctl") String ctl,
                                      @Field("act") String act);
}
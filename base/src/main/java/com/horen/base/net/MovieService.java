package com.horen.base.net;

import com.horen.domain.HomeMovie;
import com.horen.domain.d8.BaseEntry;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

import static me.jessyan.retrofiturlmanager.RetrofitUrlManager.DOMAIN_NAME_HEADER;

/**
 * Created by HOREN on 2017/11/15.
 */
public interface MovieService {

    /**
     * 视频首页
     */
    @Headers({DOMAIN_NAME_HEADER + UrlConstant.MAO_MI_1})
    @FormUrlEncoded
    @POST("api/videos/listAll")
    Observable<BaseEntry<HomeMovie>> getMoviceList(@FieldMap Map<String, String> parms);

}
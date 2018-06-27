package com.horen.horenbase;

import com.horen.horenbase.bean.DetailBean;
import com.horen.horenbase.bean.HomeBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by HOREN on 2017/11/15.
 */
public interface ApiService {
    @GET("mf/json.txt")
    Observable<HomeBean> getHomeList();

    @GET("mf/{url}")
    Observable<DetailBean> getDetailList(@Path("url") String url);

}
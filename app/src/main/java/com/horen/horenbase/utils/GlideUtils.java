package com.horen.horenbase.utils;

/**
 * Created by HOREN on 2017/10/25.
 */

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.horen.horenbase.R;

/**
 * Author:Hikin
 * Data:2016/12/12
 */

public class GlideUtils {

    public static void load(Context context, String url, ImageView iv) {    //不缓存，全部从网络加载
        Glide.with(context).load(url).crossFade()
                .error(R.drawable.video_error_normal)
                .into(iv);
    }
}
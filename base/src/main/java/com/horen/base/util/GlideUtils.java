package com.horen.base.util;

/**
 * Created by HOREN on 2017/10/25.
 */

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.horen.base.R;

/**
 * Author:Hikin
 * Data:2016/12/12
 */

public class GlideUtils {

    public static void load(Context context, String url, ImageView iv) {
        Glide.with(context).load(url).crossFade()
                .error(R.drawable.ic_error)
                .centerCrop()
                .into(iv);
    }
}
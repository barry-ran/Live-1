package com.horen.horenbase.utils;


import android.app.Activity;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.TextView;

import com.horen.horenbase.R;


/**
 * Created by Administrator on 2018/4/19 0019.
 */

public final class SnackbarUtils {

    /**
     * Show message
     *
     * @param activity Activity
     * @param msg message
     */
    public static void show(Activity activity, String msg) {
        Snackbar snackbar = Snackbar.make(activity.getWindow().getDecorView(), msg, Snackbar.LENGTH_SHORT);
        View view = snackbar.getView();
        ((TextView) view.findViewById(R.id.snackbar_text)).setTextColor(ContextCompat.getColor(activity, R.color.white));
        snackbar.show();
    }
}
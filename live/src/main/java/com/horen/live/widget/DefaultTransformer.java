package com.horen.live.widget;

/**
 * @author :ChenYangYi
 * @date :2018/08/16/17:34
 * @description :
 * @github :https://github.com/chenyy0708
 */

import android.support.v4.view.ViewPager;
import android.view.View;

public class DefaultTransformer implements ViewPager.PageTransformer {

    @Override
    public void transformPage(View view, float position) {
        view.setTranslationX(view.getWidth() * -position);
        float yPosition = position * view.getHeight();
        view.setTranslationY(yPosition);
    }
}

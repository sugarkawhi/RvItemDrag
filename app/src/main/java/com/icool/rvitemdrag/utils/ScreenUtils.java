package com.icool.rvitemdrag.utils;

import android.app.Activity;
import android.graphics.Rect;

/**
 * @author zhzy
 * @Description Created by ZhaoZongyao on 2018/7/17.
 */
public class ScreenUtils {

    public static int getStatusbarHeight(Activity activity) {
        Rect frame = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
        int statusBarHeight = frame.top;
        return statusBarHeight;

    }
}

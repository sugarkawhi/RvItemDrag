package com.icool.rvitemdrag.utils;

import android.widget.Toast;

import com.icool.rvitemdrag.base.ICoolApp;

/**
 * @author zhzy
 * @Description Created by ZhaoZongyao on 2018/7/13.
 */
public class ToastUtils {
    static Toast mToast;

    public static void show(String msg) {
        if (mToast == null)
            mToast = Toast.makeText(ICoolApp.getInstance(), msg, Toast.LENGTH_SHORT);
        mToast.setText(msg);
        mToast.show();
    }
}

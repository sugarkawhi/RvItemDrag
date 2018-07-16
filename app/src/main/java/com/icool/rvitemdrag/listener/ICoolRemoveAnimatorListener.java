package com.icool.rvitemdrag.listener;

import android.animation.Animator;
import android.view.View;

import com.icool.rvitemdrag.ICoolBottomAdapter;
import com.icool.rvitemdrag.ICoolTopAdapter;

import java.util.Collections;
import java.util.List;

/**
 * @author zhzy
 * @Description Created by ZhaoZongyao on 2018/7/13.
 */
public class ICoolRemoveAnimatorListener implements Animator.AnimatorListener {

    private ICoolTopAdapter mICoolTopAdapter;
    private ICoolBottomAdapter mICoolBottomAdapter;
    private int mPosition;

    public ICoolRemoveAnimatorListener( ICoolTopAdapter topAdapter, ICoolBottomAdapter bottomAdapter, int position) {
        mICoolTopAdapter = topAdapter;
        mICoolBottomAdapter = bottomAdapter;
        mPosition = position;
    }

    @Override
    public void onAnimationStart(Animator animation) {

    }

    @Override
    public void onAnimationEnd(Animator animation) {
        mICoolBottomAdapter.notifyDataSetChanged();
//        mICoolTopAdapter.getData().remove(mPosition);
//        mICoolTopAdapter.notifyItemRemoved(mPosition);
    }

    @Override
    public void onAnimationCancel(Animator animation) {

    }

    @Override
    public void onAnimationRepeat(Animator animation) {

    }
}

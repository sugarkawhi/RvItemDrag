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

    private View mView;
    private ICoolTopAdapter mICoolTopAdapter;
    private ICoolBottomAdapter mICoolBottomAdapter;
    private int mPosition;

    public ICoolRemoveAnimatorListener(View view, ICoolTopAdapter topAdapter, ICoolBottomAdapter bottomAdapter, int position) {
        mView = view;
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
        mView.setVisibility(View.GONE);
        List<String> data = mICoolTopAdapter.getData();
//        for (int i = mPosition; i < data.size() - 1; i++) {
//            Collections.swap(data, i, i + 1);
//        }
//        mICoolTopAdapter.notifyItemMoved(mPosition, data.size() - 1);
        data.remove(data.size() - 1);
        mICoolTopAdapter.notifyDataSetChanged();
    }

    @Override
    public void onAnimationCancel(Animator animation) {

    }

    @Override
    public void onAnimationRepeat(Animator animation) {

    }
}

package com.icool.rvitemdrag;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

/**
 * @author zhzy
 * @Description grid 布局 添加间距
 * Created by ZhaoZongyao on 2018/7/12.
 */
public class SpaceItemDecoration extends RecyclerView.ItemDecoration {
    private int lineWidth;


    public SpaceItemDecoration(int lineWidth) {
        this.lineWidth = lineWidth;
    }


    private int getSpanCount(RecyclerView parent) {
        // 列数
        int spanCount = -1;
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            spanCount = ((GridLayoutManager) layoutManager).getSpanCount();
        } else if (layoutManager instanceof StaggeredGridLayoutManager) {
            spanCount = ((StaggeredGridLayoutManager) layoutManager)
                    .getSpanCount();
        }
        return spanCount;
    }



    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
//        Log.e("liao", state.toString());
        boolean b = state.willRunPredictiveAnimations();
        int itemPosition = ((RecyclerView.LayoutParams) view.getLayoutParams()).getViewLayoutPosition();
        int spanCount = getSpanCount(parent);
        int childCount = parent.getAdapter().getItemCount();
//        if (isLastRaw(parent, itemPosition, spanCount, childCount))// 如果是最后一行，则不需要绘制底部
//        {
//            outRect.set(0, 0, lineWidth, 0);
//        }
//        else if (isLastColum(parent, itemPosition, spanCount, childCount))// 如果是最后一列，则不需要绘制右边
//        {
////            if (b){
////                outRect.set(0, 0, lineWidth, lineWidth);
////            }else {
//                outRect.set(0, 0, 0, lineWidth);
////            }
//        }
//        else {

//        if (itemPosition % spanCount == 0) {
//            outRect.set(lineWidth, 0, lineWidth, lineWidth);
//        } else {
        outRect.set(0, 0, lineWidth, lineWidth);
//        }
//        }

    }
}

package com.icool.rvitemdrag;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;

import java.util.Collections;
import java.util.List;

/**
 * @author zhzy
 * @Description Created by ZhaoZongyao on 2018/7/12.
 */
public class ICoolItemTouchHelperCallback<T> extends ItemTouchHelper.Callback {

    private static final String TAG = "ItemTouchHelper";

    private List<T> mData;
    private RecyclerView.Adapter mAdapter;

    public ICoolItemTouchHelperCallback(List<T> data, RecyclerView.Adapter adapter) {
        mData = data;
        mAdapter = adapter;
    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        Log.e(TAG, "getMovementFlags");
        if (recyclerView.getLayoutManager() instanceof GridLayoutManager) {
            final int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN |
                    ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
            final int swipeFlags = 0;
            return makeMovementFlags(dragFlags, swipeFlags);
        } else {
            final int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
            final int swipeFlags = 0;
            return makeMovementFlags(dragFlags, swipeFlags);
        }

    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        Log.e(TAG, "onMove");
        int fromPosition = viewHolder.getAdapterPosition();
        int targetPosition = target.getAdapterPosition();
        if (targetPosition == 0 || targetPosition == 1) {
            return false;
        }
        if (fromPosition < targetPosition) {
            for (int i = fromPosition; i < targetPosition; i++) {
                Collections.swap(mData, i, i + 1);
            }
        } else {
            for (int i = fromPosition; i > targetPosition; i--) {
                Collections.swap(mData, i, i - 1);
            }
        }
        mAdapter.notifyItemMoved(fromPosition, targetPosition);
        return true;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

    }

    @Override
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
        super.onSelectedChanged(viewHolder, actionState);
    }

    @Override
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        super.clearView(recyclerView, viewHolder);
    }

    @Override
    public boolean isLongPressDragEnabled() {
        return false;
    }
}

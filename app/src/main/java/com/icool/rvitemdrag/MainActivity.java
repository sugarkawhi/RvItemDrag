package com.icool.rvitemdrag;

import android.animation.Animator;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import com.icool.rvitemdrag.listener.ICoolRemoveAnimatorListener;
import com.icool.rvitemdrag.utils.ToastUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int SPAN_COUNT = 4;
    private int mItemSpace = 0;

    RecyclerView mRv1, mRv2;
    List<String> mTopData;
    List<String> mBottomData;

    private ItemTouchHelper mItemTouchHelper;
    private ICoolTopAdapter mTopAdapter;
    private ICoolBottomAdapter mBottomAdapter;
    private ICoolTopAdapter.OnItemClickListener mTopItemListener = new ICoolTopAdapter.OnItemClickListener() {
        @Override
        public void onLongClick(ICoolTopAdapter.ICoolTopHolder holder) {
            int position = holder.getAdapterPosition();
            if (position == 0 || position == 1) return;
            mItemTouchHelper.startDrag(holder);
        }

        @Override
        public void onClick(ICoolTopAdapter.ICoolTopHolder holder) {
            View view = holder.itemView;
            if (view == null) return;
            int position = holder.getAdapterPosition();
            ToastUtils.show(mTopData.get(position));
            startRemoveAnim(view, position);
        }

        @Override
        public void onDeleteClick(ICoolTopAdapter.ICoolTopHolder holder) {

        }
    };

    private ICoolBottomAdapter.OnItemClickListener mBottomItemClickListener = new ICoolBottomAdapter.OnItemClickListener() {
        @Override
        public void onClick(ICoolBottomAdapter.ICoolBottomHolder holder) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRv1 = findViewById(R.id.rv);
        mRv2 = findViewById(R.id.rv2);
        init();
    }

    private void init() {
        mTopData = new ArrayList<>();
        mTopData.add("推荐");
        mTopData.add("图片");
        mTopData.add("搞笑");
        mTopData.add("科技");
        mTopData.add("汽车");
        mTopData.add("咨询");
        mTopData.add("育婴");
        mTopData.add("篮球");
        mTopData.add("体育");
        mTopData.add("世界杯");
        mTopData.add("国家");
        mTopData.add("政治");
        mTopData.add("考试");
        mTopData.add("民生");
        mTopData.add("政策");
        mTopData.add("作家");
        mTopData.add("艺术");

        mBottomData = new ArrayList<>();
//        mBottomData.add("历史");
//        mBottomData.add("健康");
//        mBottomData.add("家具");
//        mBottomData.add("漫画");
//        mBottomData.add("数码");
//        mBottomData.add("教育");

        mRv1.setHasFixedSize(true);
        mRv1.setLayoutManager(new GridLayoutManager(this, SPAN_COUNT));
        mRv2.setLayoutManager(new GridLayoutManager(this, SPAN_COUNT));
        mItemSpace = (int) getResources().getDimension(R.dimen.item_space_catalog);
        mRv1.addItemDecoration(new SpaceItemDecoration(mItemSpace));
        mRv2.addItemDecoration(new SpaceItemDecoration(mItemSpace));
        mTopAdapter = new ICoolTopAdapter(this, mTopData, mTopItemListener);
        ICoolItemTouchHelperCallback callback = new ICoolItemTouchHelperCallback<>(mTopData, mTopAdapter);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(mRv1);
        mRv1.setAdapter(mTopAdapter);
        mBottomAdapter = new ICoolBottomAdapter(this, mBottomData, mBottomItemClickListener);
        mRv2.setAdapter(mBottomAdapter);
    }

    /**
     * 开启平移动画
     */
    private void startRemoveAnim(View fromView, int position) {
        mBottomData.add(mTopData.get(position));

        if (mRv2.getChildCount() == 0) {
            mBottomAdapter.notifyDataSetChanged();
            return;
        }
        int index = mRv2.getChildCount() - 1;
        View toView = mRv2.getChildAt(index);
        //起始位置
        int[] fOutLocation = new int[2];
        fromView.getLocationOnScreen(fOutLocation);
        float fromX = fOutLocation[0];
        float fromY = fOutLocation[1];
        //目标位置
        int[] tOutLocation = new int[2];
        toView.getLocationOnScreen(tOutLocation);
        float toX = tOutLocation[0];
        float toY = tOutLocation[1];

        if ((index + 1) % SPAN_COUNT == 0) {
            // 下一行第一个
            toY = toY + mItemSpace + toView.getMeasuredHeight();
            toX = mItemSpace;
        } else {
            // 本行下一个
            toX = toX + mItemSpace + toView.getMeasuredWidth();
        }

        fromView.animate()
                .translationXBy(toX - fromX)
                .translationYBy(toY - fromY)
                .setDuration(200)
                .setListener(new ICoolRemoveAnimatorListener(fromView, mTopAdapter, mBottomAdapter, position))
                .start();

    }


}

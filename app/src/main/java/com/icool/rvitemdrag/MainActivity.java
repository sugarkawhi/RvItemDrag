package com.icool.rvitemdrag;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.WindowManager;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.icool.rvitemdrag.listener.ICoolRemoveAnimatorListener;
import com.icool.rvitemdrag.utils.ScreenUtils;
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
            view.setVisibility(View.GONE);
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
        mBottomData.add("漫画");
        mBottomData.add("数码");
        mBottomData.add("教育");

        mRv1.setHasFixedSize(true);
        GridLayoutManager g1 = new GridLayoutManager(this, SPAN_COUNT);
        mRv1.setLayoutManager(g1);
        GridLayoutManager g2 = new GridLayoutManager(this, SPAN_COUNT);
        mRv2.setLayoutManager(g2);
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
        if (mRv2.getChildCount() == 0) {
            mBottomData.add(mTopData.get(position));
            mBottomAdapter.notifyDataSetChanged();
            mTopData.remove(position);
            fromView.setVisibility(View.VISIBLE);
            mTopAdapter.notifyItemRemoved(position);
            mTopAdapter.notifyItemRangeChanged(position, mTopAdapter.getData().size());
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


        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        //初始化后不首先获得窗口焦点。不妨碍设备上其他部件的点击、触摸事件。
        lp.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL | WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE;
        WindowManager wm = getWindowManager();
        // 设置窗口类型，一共有三种Application windows, Sub-windows, System windows
        // API中以TYPE_开头的常量有23个
        lp.type = WindowManager.LayoutParams.TYPE_APPLICATION_PANEL;
        // 设置期望的bitmap格式
        lp.format = PixelFormat.TRANSLUCENT;
        lp.gravity = Gravity.START | Gravity.TOP;
        final FrameLayout frameLayout = new FrameLayout(this);
        wm.addView(frameLayout, lp);
        fromView.setDrawingCacheEnabled(true);
        final ImageView imageView = new ImageView(this);
        frameLayout.addView(imageView);
        imageView.setImageBitmap(getDrawingCache(fromView));
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) imageView.getLayoutParams();
        layoutParams.width = fromView.getMeasuredWidth();
        layoutParams.height = fromView.getMeasuredHeight();
        imageView.setLayoutParams(layoutParams);
        imageView.setX(fromX);
        imageView.setY(fromY);
        imageView.bringToFront();

        imageView.animate()
                .translationXBy(toX - fromX)
                .translationYBy(toY - fromY)
                .setInterpolator(new LinearInterpolator())
                .setDuration(500)
                .setListener(new ICoolRemoveAnimatorListener(mTopAdapter, mBottomAdapter, position) {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        frameLayout.setVisibility(View.GONE);
                        getWindowManager().removeView(frameLayout);
                        super.onAnimationEnd(animation);
                    }
                })
                .start();
        mBottomData.add(mTopData.get(position));
        fromView.setVisibility(View.VISIBLE);
        mTopData.remove(position);
        mTopAdapter.notifyItemRemoved(position);
        mTopAdapter.notifyItemRangeChanged(position, mTopAdapter.getData().size());
    }


    private Bitmap getDrawingCache(View view) {
        Bitmap bitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(bitmap);//使用bitmap构建一个Canvas，绘制的所有内容都是绘制在此Bitmap上的
        Drawable bgDrawable = view.getBackground();
        if (bgDrawable == null) {
            c.drawColor(Color.TRANSPARENT);
        } else {
            bgDrawable.draw(c);//绘制背景
        }
        view.draw(c);//绘制前景
        return bitmap;
    }

}

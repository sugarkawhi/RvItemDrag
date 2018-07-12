package com.icool.rvitemdrag;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

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

        }

        @Override
        public void onDeleteClick(ICoolTopAdapter.ICoolTopHolder holder) {
            View view = holder.itemView;
            if (view == null) return;
            float x = view.getX();
            float y = view.getY();
            Toast.makeText(MainActivity.this, "x=" + x + " y=" + y, Toast.LENGTH_SHORT).show();
            startXAnim(view);
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
        mBottomData.add("历史");
        mTopData.add("健康");
        mBottomData.add("家具");
        mBottomData.add("漫画");
        mBottomData.add("数码");
        mBottomData.add("教育");

        mRv1.setLayoutManager(new GridLayoutManager(this, 4));
        mRv2.setLayoutManager(new GridLayoutManager(this, 4));
        mRv1.addItemDecoration(new SpaceItemDecoration(4,30,false));
        mRv2.addItemDecoration(new SpaceItemDecoration(4,30,false));
        ICoolItemTouchHelperCallback callback = new ICoolItemTouchHelperCallback<>(mTopData);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(mRv1);
        mTopAdapter = new ICoolTopAdapter(this, mTopData, mTopItemListener);
        mRv1.setAdapter(mTopAdapter);
        mBottomAdapter = new ICoolBottomAdapter(this, mBottomData, mBottomItemClickListener);
        mRv2.setAdapter(mBottomAdapter);
    }

    /**
     * 开启平移动画
     */
    private void startXAnim(View view) {

//        view.animate()
//                .translationXBy(mIvTarget.getX() - view.getX())
//                .translationYBy(mIvTarget.getY() - view.getY())
//                .setDuration(800)
//                .start();

    }
}

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

    RecyclerView mRv1;
    ImageView mIvTarget;
    ICoolAdapter mICoolAdapter;
    List<String> mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRv1 = findViewById(R.id.rv);
        mIvTarget = findViewById(R.id.iv_target);
        init();
        mRv1.setLayoutManager(new GridLayoutManager(this, 4));
        mRv1.addItemDecoration(new SpaceItemDecoration(20, 4));
        ICoolItemTouchHelperCallback callback = new ICoolItemTouchHelperCallback<>(mData);
        final ItemTouchHelper helper = new ItemTouchHelper(callback);
        ICoolAdapter.OnItemClickListener itemClickListener = new ICoolAdapter.OnItemClickListener() {
            @Override
            public void onLongClick(int position, ICoolAdapter.ICoolHolder holder) {
                if (position == 0 || position == 1) return;
                helper.startDrag(holder);
            }

            @Override
            public void onClick(int position) {

            }

            @Override
            public void onDeleteClick(int position, ICoolAdapter.ICoolHolder holder) {
                View view = holder.itemView;
                if (view == null) return;
                float x = view.getX();
                float y = view.getY();
                Toast.makeText(MainActivity.this, "x=" + x + " y=" + y, Toast.LENGTH_SHORT).show();
                startXAnim(view);
            }
        };
        mICoolAdapter = new ICoolAdapter(this, itemClickListener, mData);
        mRv1.setAdapter(mICoolAdapter);

        helper.attachToRecyclerView(mRv1);
    }

    private void init() {
        mData = new ArrayList<>();
        mData.add("推荐");
        mData.add("图片");
        mData.add("搞笑");
        mData.add("科技");
        mData.add("汽车");
        mData.add("咨询");
        mData.add("育婴");
        mData.add("篮球");
        mData.add("体育");
        mData.add("世界杯");
        mData.add("国家");
        mData.add("政治");
        mData.add("考试");
        mData.add("民生");
        mData.add("政策");
        mData.add("作家");
        mData.add("艺术");
    }

    /**
     * 开启平移动画
     */
    private void startXAnim(View view) {

        view.animate()
                .translationXBy(mIvTarget.getX() - view.getX())
                .translationYBy(mIvTarget.getY() - view.getY())
                .setDuration(800)
                .start();

    }
}

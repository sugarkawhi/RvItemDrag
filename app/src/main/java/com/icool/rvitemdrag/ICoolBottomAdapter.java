package com.icool.rvitemdrag;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * @author zhzy
 * @Description 顶部 我的栏目
 * <p>
 * Created by ZhaoZongyao on 2018/7/12.
 */
public class ICoolBottomAdapter extends RecyclerView.Adapter<ICoolBottomAdapter.ICoolBottomHolder> {

    private Context mContext;
    private OnItemClickListener mItemClickListener;

    private List<String> mData;

    public ICoolBottomAdapter(Context context, List<String> data, OnItemClickListener itemClickListener) {
        mContext = context;
        mItemClickListener = itemClickListener;
        mData = data;
    }

    @NonNull
    @Override
    public ICoolBottomHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.list_item_icool_bottom, parent, false);
        return new ICoolBottomHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ICoolBottomHolder holder, final int position) {
        holder.mTextView.setText(mData.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mItemClickListener.onClick(holder);
            }
        });


    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    static class ICoolBottomHolder extends RecyclerView.ViewHolder {
        private TextView mTextView;

        public ICoolBottomHolder(View itemView) {
            super(itemView);
            mTextView = itemView.findViewById(R.id.tv);
        }
    }

    public interface OnItemClickListener {

        void onClick(ICoolBottomHolder holder);

    }
}

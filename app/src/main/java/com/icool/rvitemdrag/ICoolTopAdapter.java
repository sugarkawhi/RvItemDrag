package com.icool.rvitemdrag;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * @author zhzy
 * @Description 顶部 我的栏目
 * <p>
 * Created by ZhaoZongyao on 2018/7/12.
 */
public class ICoolTopAdapter extends RecyclerView.Adapter<ICoolTopAdapter.ICoolTopHolder> {

    private Context mContext;
    private OnItemClickListener mItemClickListener;

    private List<String> mData;

    public ICoolTopAdapter(Context context, List<String> data, OnItemClickListener itemClickListener) {
        mContext = context;
        mItemClickListener = itemClickListener;
        mData = data;
    }

    @NonNull
    @Override
    public ICoolTopHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_icool_top, parent, false);
        return new ICoolTopHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ICoolTopHolder holder, final int position) {
        holder.mTextView.setText(mData.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, mData.get(holder.getAdapterPosition()), Toast.LENGTH_SHORT).show();
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                mItemClickListener.onLongClick(holder);
                return false;
            }
        });
        holder.mIvDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mItemClickListener.onDeleteClick(holder);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    static class ICoolTopHolder extends RecyclerView.ViewHolder {
        TextView mTextView;
        ImageView mIvDelete;

        public ICoolTopHolder(View itemView) {
            super(itemView);
            mTextView = itemView.findViewById(R.id.tv);
            mIvDelete = itemView.findViewById(R.id.iv_delete);
        }
    }

    public interface OnItemClickListener {
        void onLongClick(ICoolTopHolder holder);

        void onClick(ICoolTopHolder holder);

        void onDeleteClick(ICoolTopHolder holder);
    }
}

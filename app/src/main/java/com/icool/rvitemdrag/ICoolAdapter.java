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
 * @Description Created by ZhaoZongyao on 2018/7/12.
 */
public class ICoolAdapter extends RecyclerView.Adapter<ICoolAdapter.ICoolHolder> {

    private Context mContext;
    private OnItemClickListener mItemClickListener;

    List<String> mData;

    public ICoolAdapter(Context context, OnItemClickListener itemClickListener, List<String> data) {
        mContext = context;
        mItemClickListener = itemClickListener;
        mData = data;
    }

    @NonNull
    @Override
    public ICoolHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_icool, parent, false);
        return new ICoolHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ICoolHolder holder, final int position) {
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
                mItemClickListener.onLongClick(holder.getAdapterPosition(), holder);
                return false;
            }
        });
        holder.mIvDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mItemClickListener.onDeleteClick(holder.getAdapterPosition(), holder);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    static class ICoolHolder extends RecyclerView.ViewHolder {
        TextView mTextView;
        ImageView mIvDelete;

        public ICoolHolder(View itemView) {
            super(itemView);
            mTextView = itemView.findViewById(R.id.tv);
            mIvDelete = itemView.findViewById(R.id.iv_delete);
        }
    }

    public interface OnItemClickListener {
        void onLongClick(int position, ICoolHolder holder);

        void onClick(int position);

        void onDeleteClick(int position, ICoolHolder holder);
    }
}

package com.example.headerrecycler;

import android.annotation.SuppressLint;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

public class HeaderRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int ITEM_TYPE_HEADER = 0;
    public static final int ITEM_TYPE_NORMAL = 1;

    private RecyclerView.Adapter mAdapter;
    private View mHeaderView;
    private int mHeaderViewNumber = 0;

    private RecyclerView.AdapterDataObserver mObserver = new RecyclerView.AdapterDataObserver() {
        @Override
        public void onChanged() {
            super.onChanged();
        }

        @Override
        public void onItemRangeChanged(int positionStart, int itemCount) {
            notifyItemRangeChanged(positionStart - mHeaderViewNumber, itemCount);
        }

        @Override
        public void onItemRangeChanged(int positionStart, int itemCount, @Nullable Object payload) {
            onItemRangeChanged(positionStart, itemCount);
        }

        @Override
        public void onItemRangeInserted(int positionStart, int itemCount) {
            notifyItemRangeInserted(positionStart - mHeaderViewNumber, itemCount);
        }

        @Override
        public void onItemRangeRemoved(int positionStart, int itemCount) {
            notifyItemRangeRemoved(positionStart - mHeaderViewNumber, itemCount);
        }

        @Override
        public void onItemRangeMoved(int fromPosition, int toPosition, int itemCount) {
            onItemRangeRemoved(fromPosition - mHeaderViewNumber, itemCount);
        }
    };

    public HeaderRecyclerAdapter(RecyclerView.Adapter adapter) {
        mAdapter = adapter;
        registerAdapterDataObserver(mObserver);
    }

    private static class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == ITEM_TYPE_HEADER) {
            return new ViewHolder(mHeaderView);
        }
        return mAdapter.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (isHeader(position)) {
            return;
        }
        mAdapter.onBindViewHolder(holder, position - mHeaderViewNumber);
    }

    @Override
    public int getItemCount() {
        if (mHeaderView != null) {
            return mAdapter.getItemCount() + mHeaderViewNumber;
        }
        return mAdapter.getItemCount();
    }

    @Override
    public int getItemViewType(int position) {
        if (mHeaderView != null && position == 0) {
            return ITEM_TYPE_HEADER;
        }
        return ITEM_TYPE_NORMAL;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setHeaderView(View view) {
        if (view != null) {
            mHeaderView = view;
            mHeaderViewNumber = 1;
            notifyDataSetChanged();
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    public void removeHeaderView() {
        if (mHeaderView != null) {
            mHeaderView = null;
            mHeaderViewNumber = 0;
            notifyDataSetChanged();
        }
    }

    private boolean isHeader(int position) {
        return mHeaderView != null && position == 0;
    }

}

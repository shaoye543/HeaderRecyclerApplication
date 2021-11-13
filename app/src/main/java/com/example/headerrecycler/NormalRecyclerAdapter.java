package com.example.headerrecycler;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NormalRecyclerAdapter extends RecyclerView.Adapter<NormalRecyclerAdapter.ViewHolder> {

    private List<String> mContents;

    public NormalRecyclerAdapter(List<String> contents) {
        mContents = contents;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.item_text);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.header_recycler_view_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView.setText(mContents.get(position));
    }

    @Override
    public int getItemCount() {
        return mContents.size();
    }

    @Override
    public int getItemViewType(int position) {
        return 1;
    }
}

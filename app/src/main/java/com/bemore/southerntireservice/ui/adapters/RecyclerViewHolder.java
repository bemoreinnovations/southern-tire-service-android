package com.bemore.southerntireservice.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.ButterKnife;

public abstract class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    RecyclerViewHolder.OnItemClickListener onItemClickListener;
    public View root;

    public RecyclerViewHolder(View itemView) {
        super(itemView);

        ButterKnife.bind(this, itemView);
        itemView.setOnClickListener(this);
        this.root = itemView;
    }

    public RecyclerViewHolder(View view, RecyclerViewHolder.OnItemClickListener onItemClickListener) {
        this(view);
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public void onClick(View v) {
        onItemClickListener.onItemClick(v, getAdapterPosition());
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }
}
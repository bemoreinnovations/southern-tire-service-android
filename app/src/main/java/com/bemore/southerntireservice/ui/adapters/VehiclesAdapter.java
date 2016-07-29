package com.bemore.southerntireservice.ui.adapters;

import android.view.View;
import android.widget.TextView;

import com.bemore.southerntireservice.R;
import com.bemore.southerntireservice.model.Vehicle;

import java.util.List;

import butterknife.Bind;

/**
 * Created by Cody on 5/11/16.
 */
public class VehiclesAdapter extends RecyclerViewArrayAdapter<Vehicle, VehiclesAdapter.ViewHolder> {

    public VehiclesAdapter(List<Vehicle> items, RecyclerViewHolder.OnItemClickListener onItemClickListener) {
        super(items, onItemClickListener);
    }

    @Override
    public ViewHolder onCreateViewHolder(View view) {
        return new ViewHolder(view, this.onItemClickListener);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.row_vehicle;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, Vehicle item) {
        holder.yearView.setText(String.valueOf(item.getYear()));
        holder.makeView.setText(item.getMake());
        holder.modelView.setText(item.getModel());
    }

    public static class ViewHolder extends RecyclerViewHolder {

        @Bind(R.id.year)
        protected TextView yearView;

        @Bind(R.id.make)
        protected TextView makeView;

        @Bind(R.id.model)
        protected TextView modelView;

        public ViewHolder(View itemView) {
            super(itemView);
        }

        public ViewHolder(View view, OnItemClickListener onItemClickListener) {
            super(view, onItemClickListener);
        }
    }
}

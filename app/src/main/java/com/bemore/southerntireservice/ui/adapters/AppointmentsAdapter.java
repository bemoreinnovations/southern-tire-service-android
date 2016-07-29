package com.bemore.southerntireservice.ui.adapters;

import android.view.View;
import android.widget.TextView;

import com.bemore.southerntireservice.R;
import com.bemore.southerntireservice.model.Appointment;

import java.util.List;

import butterknife.Bind;

/**
 * Created by Cody on 5/11/16.
 */
public class AppointmentsAdapter extends RecyclerViewArrayAdapter<Appointment, AppointmentsAdapter.ViewHolder> {

    public AppointmentsAdapter(List<Appointment> items, RecyclerViewHolder.OnItemClickListener onItemClickListener) {
        super(items, onItemClickListener);
    }

    @Override
    public ViewHolder onCreateViewHolder(View view) {
        return new ViewHolder(view, this.onItemClickListener);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.row_appointment;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, Appointment item) {
        holder.serviceTypeView.setText(item.getFormattedType());
        holder.timeView.setText(item.getFormattedDateTime());

        if (item.getVehicle() != null) {
            holder.vehicleView.setText(item.getVehicle().getDisplay());
            holder.vehicleView.setVisibility(View.VISIBLE);
        }
        else {
            holder.vehicleView.setVisibility(View.GONE);
        }
    }

    public static class ViewHolder extends RecyclerViewHolder {

        @Bind(R.id.serviceType)
        protected TextView serviceTypeView;

        @Bind(R.id.time)
        protected TextView timeView;

        @Bind(R.id.vehicle)
        protected TextView vehicleView;

        public ViewHolder(View itemView) {
            super(itemView);
        }

        public ViewHolder(View view, OnItemClickListener onItemClickListener) {
            super(view, onItemClickListener);
        }
    }
}

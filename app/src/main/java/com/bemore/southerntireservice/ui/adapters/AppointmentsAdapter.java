package com.bemore.southerntireservice.ui.adapters;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.bemore.southerntireservice.R;
import com.bemore.southerntireservice.model.Appointment;
import com.bemore.southerntireservice.otto.events.CancelAppointmentEvent;
import com.bemore.southerntireservice.otto.events.EditAppointmentEvent;
import com.daimajia.swipe.SwipeLayout;

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
    public void onBindViewHolder(ViewHolder holder, final Appointment item) {
        holder.serviceTypeView.setText(item.getFormattedType());
        holder.timeView.setText(item.getFormattedDateTime());

        if (item.getVehicle() != null) {
            holder.vehicleView.setText(item.getVehicle().getDisplay());
            holder.vehicleView.setVisibility(View.VISIBLE);
        }
        else {
            holder.vehicleView.setVisibility(View.GONE);
        }

        holder.editView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scopedBus.post(new EditAppointmentEvent(item));
            }
        });

        holder.trashView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelAppointmentConfirmation(item);
            }
        });
    }

    private void cancelAppointmentConfirmation(final Appointment vehicle) {
        new MaterialDialog.Builder(getContext())
                .title("Cancel Appointment")
                .content("Are you sure you want to cancel this appointment?")
                .positiveText("Yes")
                .negativeText("No")
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        scopedBus.post(new CancelAppointmentEvent(vehicle));
                    }
                }).show();
    }

    public static class ViewHolder extends RecyclerViewHolder {

        @Bind(R.id.serviceType)
        protected TextView serviceTypeView;

        @Bind(R.id.time)
        protected TextView timeView;

        @Bind(R.id.vehicle)
        protected TextView vehicleView;

        @Bind(R.id.swipe)
        protected SwipeLayout swipeLayout;

        @Bind(R.id.edit)
        protected ImageView editView;

        @Bind(R.id.trash)
        protected ImageView trashView;

        public ViewHolder(View itemView) {
            super(itemView);
        }

        public ViewHolder(View view, OnItemClickListener onItemClickListener) {
            super(view, onItemClickListener);
        }
    }
}

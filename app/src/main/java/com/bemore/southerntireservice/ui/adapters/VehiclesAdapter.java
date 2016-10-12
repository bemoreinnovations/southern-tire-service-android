package com.bemore.southerntireservice.ui.adapters;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.bemore.southerntireservice.R;
import com.bemore.southerntireservice.model.Vehicle;
import com.bemore.southerntireservice.otto.events.DeleteVehicleEvent;
import com.bemore.southerntireservice.otto.events.EditVehicleEvent;
import com.daimajia.swipe.SwipeLayout;

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
    public void onBindViewHolder(ViewHolder holder, final Vehicle item) {
        holder.yearView.setText(String.valueOf(item.getYear()));
        holder.makeView.setText(item.getMake());
        holder.modelView.setText(item.getModel());

        holder.editView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scopedBus.post(new EditVehicleEvent(item));
            }
        });

        holder.trashView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteVehicleConfirmation(item);
            }
        });
    }

    private void deleteVehicleConfirmation(final Vehicle vehicle) {
        new MaterialDialog.Builder(getContext())
                .title("Delete Vehicle")
                .content("Are you sure you want to delete this vehicle?")
                .positiveText("Delete")
                .negativeText("Cancel")
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        scopedBus.post(new DeleteVehicleEvent(vehicle));
                    }
                }).show();
    }

    public static class ViewHolder extends RecyclerViewHolder {

        @Bind(R.id.year)
        protected TextView yearView;

        @Bind(R.id.make)
        protected TextView makeView;

        @Bind(R.id.model)
        protected TextView modelView;

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

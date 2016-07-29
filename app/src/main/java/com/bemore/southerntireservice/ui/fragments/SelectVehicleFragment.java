package com.bemore.southerntireservice.ui.fragments;

import android.view.View;

import com.bemore.southerntireservice.model.Vehicle;

/**
 * Created by Cody on 5/11/16.
 */
public class SelectVehicleFragment extends MyVehiclesFragment {
    @Override
    public void onItemClick(View view, int position) {
        super.onItemClick(view, position);

        Vehicle vehicle = vehiclesAdapter.getItem(position);


    }
}

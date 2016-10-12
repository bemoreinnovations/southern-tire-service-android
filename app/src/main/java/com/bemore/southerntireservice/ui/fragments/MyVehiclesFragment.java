package com.bemore.southerntireservice.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.bemore.southerntireservice.R;
import com.bemore.southerntireservice.model.User;
import com.bemore.southerntireservice.model.Vehicle;
import com.bemore.southerntireservice.otto.events.DeleteVehicleEvent;
import com.bemore.southerntireservice.otto.events.EditVehicleEvent;
import com.bemore.southerntireservice.ui.adapters.RecyclerViewHolder;
import com.bemore.southerntireservice.ui.adapters.VehiclesAdapter;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Cody on 5/11/16.
 */
public class MyVehiclesFragment extends BaseFragment implements RecyclerViewHolder.OnItemClickListener {

    @Bind(R.id.recyclerView)
    protected RecyclerView recyclerView;

    private LinearLayoutManager layoutManager;
    protected VehiclesAdapter vehiclesAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        vehiclesAdapter = new VehiclesAdapter(new ArrayList<Vehicle>(), MyVehiclesFragment.this);
        recyclerView.setAdapter(vehiclesAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();

        ParseQuery<Vehicle> query = new ParseQuery(Vehicle.class);
        query.whereEqualTo("user", User.getCurrentUser())
                .addDescendingOrder(Vehicle.KEY_YEAR)
                .findInBackground(new FindCallback<Vehicle>() {
                    @Override
                    public void done(List<Vehicle> vehicles, ParseException e) {
                        vehiclesAdapter.bindData(vehicles);
                    }
                });
    }

    @OnClick(R.id.fab)
    public void onFabClick(View view) {
        showFragmentWithSlidingTransition(new AddOrEditVehicleFragment(), true);
    }

    @Override
    public void onItemClick(View view, int position) {

    }

    @Override
    protected int getFragmentLayoutId() {
        return R.layout.fragment_my_vehicles;
    }

    @Subscribe
    public void onEditVehicleEvent(EditVehicleEvent event) {
        showFragmentWithSlidingTransition(AddOrEditVehicleFragment.newInstance(event.vehicle), true);
    }

    @Subscribe
    public void onDeleteVehicleEvent(DeleteVehicleEvent event) {

        Toast.makeText(getContext(), "Deleting vehicle from account", Toast.LENGTH_SHORT).show();

        event.vehicle.deleteInBackground();

        vehiclesAdapter.remove(event.vehicle);
    }
}

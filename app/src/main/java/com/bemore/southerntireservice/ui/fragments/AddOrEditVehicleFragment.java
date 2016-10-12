package com.bemore.southerntireservice.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bemore.southerntireservice.R;
import com.bemore.southerntireservice.constants.Args;
import com.bemore.southerntireservice.model.User;
import com.bemore.southerntireservice.model.Vehicle;
import com.parse.ParseException;
import com.parse.SaveCallback;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Cody on 5/11/16.
 */
public class AddOrEditVehicleFragment extends BaseFragment {

    @Bind(R.id.year)
    protected TextView yearView;

    @Bind(R.id.make)
    protected TextView makeView;

    @Bind(R.id.model)
    protected TextView modelView;

    @Bind(R.id.color)
    protected TextView colorView;

    @Bind(R.id.vin)
    protected TextView vinView;

    Vehicle vehicle;

    public static AddOrEditVehicleFragment newInstance(Vehicle vehicle) {
        Bundle args = new Bundle();
        args.putParcelable(Args.VEHICLE, vehicle);
        AddOrEditVehicleFragment fragment = new AddOrEditVehicleFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle args = getArguments();

        if (args != null && args.containsKey(Args.VEHICLE)) {
            vehicle = args.getParcelable(Args.VEHICLE);
            populateUI();
        }
    }

    protected void populateUI() {
        yearView.setText(String.valueOf(vehicle.getYear()));
        makeView.setText(vehicle.getMake());
        modelView.setText(vehicle.getModel());
        colorView.setText(vehicle.getColor());
        vinView.setText(vehicle.getVin());
    }

    @OnClick(R.id.saveVehicleButton)
    public void addVehicle(Button view) {

        if (vehicle == null) {
            vehicle = newVehicle();
        }
        else {
            vehicle = getUpdatedVehicle();
        }

        vehicle.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                Toast.makeText(getContext(), vehicle.getYear() + " " + vehicle.getMake() + " " + vehicle.getModel() + " saved to your vehicles.", Toast.LENGTH_SHORT).show();
                getActivity().onBackPressed();
            }
        });
    }

    private Vehicle newVehicle() {

        final int year = Integer.valueOf(yearView.getText().toString());

        return new Vehicle.Builder()
                .year(year)
                .make(makeView.getText().toString())
                .model(modelView.getText().toString())
                .color(colorView.getText().toString())
                .vin(vinView.getText().toString())
                .user(User.getCurrentUser())
                .build();
    }

    private Vehicle getUpdatedVehicle() {
        final int year = Integer.valueOf(yearView.getText().toString());

        vehicle.setYear(year);
        vehicle.setMake(makeView.getText().toString());
        vehicle.setModel(modelView.getText().toString());
        vehicle.setColor(colorView.getText().toString());
        vehicle.setVin(vinView.getText().toString());

        return vehicle;
    }

    @Override
    protected int getFragmentLayoutId() {
        return R.layout.fragment_add_vehicle;
    }
}

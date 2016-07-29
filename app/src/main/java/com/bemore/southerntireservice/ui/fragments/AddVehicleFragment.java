package com.bemore.southerntireservice.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bemore.southerntireservice.R;
import com.bemore.southerntireservice.model.User;
import com.bemore.southerntireservice.model.Vehicle;
import com.parse.ParseException;
import com.parse.SaveCallback;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Cody on 5/11/16.
 */
public class AddVehicleFragment extends BaseFragment {

    @Bind(R.id.year)
    protected TextView yearView;

    @Bind(R.id.make)
    protected TextView makeiew;

    @Bind(R.id.model)
    protected TextView modelView;

    @Bind(R.id.color)
    protected TextView colorView;

    @Bind(R.id.vin)
    protected TextView vinView;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @OnClick(R.id.addVehicleButton)
    public void addVehicle(Button view) {

        final int year = Integer.valueOf(yearView.getText().toString());

        final Vehicle vehicle = new Vehicle.Builder()
                .year(year)
                .make(makeiew.getText().toString())
                .model(modelView.getText().toString())
                .color(colorView.getText().toString())
                .vin(vinView.getText().toString())
                .user(User.getCurrentUser())
                .build();

        vehicle.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                Toast.makeText(getContext(), year+ " " + vehicle.getMake() + " " + vehicle.getModel() + " added to your vehicles.", Toast.LENGTH_SHORT).show();
                getActivity().onBackPressed();
            }
        });
    }

    @Override
    protected int getFragmentLayoutId() {
        return R.layout.fragment_add_vehicle;
    }
}

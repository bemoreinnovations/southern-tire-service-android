package com.bemore.southerntireservice.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.bemore.southerntireservice.R;
import com.bemore.southerntireservice.model.Appointment;
import com.bemore.southerntireservice.model.User;
import com.bemore.southerntireservice.ui.adapters.AppointmentsAdapter;
import com.bemore.southerntireservice.ui.adapters.RecyclerViewHolder;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.List;

import butterknife.Bind;

/**
 * Created by Cody on 5/11/16.
 */
public class MyAppointmentsFragment extends BaseFragment implements RecyclerViewHolder.OnItemClickListener {

    @Bind(R.id.recyclerView)
    protected RecyclerView recyclerView;

    private LinearLayoutManager layoutManager;
    private AppointmentsAdapter appointmentsAdapter;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        ParseQuery<Appointment> query = new ParseQuery(Appointment.class);
        query.whereEqualTo("user", User.getCurrentUser())
                .addAscendingOrder("dateTime")
                .include("vehicle")
                .findInBackground(new FindCallback<Appointment>() {
                    @Override
                    public void done(List<Appointment> appointments, ParseException e) {
                        appointmentsAdapter = new AppointmentsAdapter(appointments, MyAppointmentsFragment.this);
                        recyclerView.setAdapter(appointmentsAdapter);
                    }
                });
    }

    @Override
    public void onItemClick(View view, int position) {

    }

    @Override
    protected int getFragmentLayoutId() {
        return R.layout.fragment_my_appointments;
    }
}

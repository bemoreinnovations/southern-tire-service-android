package com.bemore.southerntireservice.ui.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.bemore.southerntireservice.constants.Args;
import com.bemore.southerntireservice.model.Appointment;
import com.bemore.southerntireservice.ui.fragments.RequestAppointmentFragment;

/**
 * Created by Cody on 5/11/16.
 */
public class RequestAppointmentActivity extends ContainerActivity {

    private Appointment appointment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        appointment = getIntent().getParcelableExtra(Args.APPOINTMENT);

        super.onCreate(savedInstanceState);
    }

    @Override
    public Fragment getFragment() {
        return RequestAppointmentFragment.newInstance(appointment);
    }
}

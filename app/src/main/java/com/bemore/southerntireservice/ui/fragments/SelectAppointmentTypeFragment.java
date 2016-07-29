package com.bemore.southerntireservice.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.bemore.southerntireservice.R;
import com.bemore.southerntireservice.constants.Args;
import com.bemore.southerntireservice.model.Appointment;
import com.bemore.southerntireservice.model.AppointmentType;
import com.bemore.southerntireservice.model.User;
import com.bemore.southerntireservice.ui.activities.RequestAppointmentActivity;

import butterknife.OnClick;

/**
 * Created by Cody on 5/11/16.
 */
public class SelectAppointmentTypeFragment extends BaseFragment {

    private Appointment appointment;

    public static SelectAppointmentTypeFragment newInstance(Appointment appointment) {
        return newInstance(new SelectAppointmentTypeFragment(), appointment);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        appointment = getArguments().getParcelable(Args.APPOINTMENT);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @OnClick({ R.id.dropOffButton, R.id.waitingButton, R.id.notSureButton })
    public void onClick(View view) {

        appointment.setUser(User.getCurrentUser());

        switch (view.getId()) {
            case R.id.dropOffButton:

                appointment.setAppointmentType(AppointmentType.DROP_OFF);
                break;

            case R.id.waitingButton:

                appointment.setAppointmentType(AppointmentType.WAITING);
                break;

            case R.id.notSureButton:

                // phone call
                break;

            default:

                break;
        }

        Intent intent = new Intent(getActivity(), RequestAppointmentActivity.class);
        intent.putExtra(Args.APPOINTMENT, appointment);
        startActivity(intent);
    }

    @Override
    protected int getFragmentLayoutId() {
        return R.layout.fragment_select_appointment_type;
    }
}

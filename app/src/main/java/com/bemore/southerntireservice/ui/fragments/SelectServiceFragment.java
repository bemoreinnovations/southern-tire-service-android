package com.bemore.southerntireservice.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.bemore.southerntireservice.R;
import com.bemore.southerntireservice.model.Appointment;
import com.bemore.southerntireservice.model.User;

import butterknife.OnClick;

/**
 * Created by Cody on 5/11/16.
 */
public class SelectServiceFragment extends BaseFragment {

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @OnClick({ R.id.oilChangeButton, R.id.tireRotationButton, R.id.maintenanceButton, R.id.otherButton })
    public void onClick(View view) {

        Appointment appointment = new Appointment();
        appointment.setUser(User.getCurrentUser());

        switch (view.getId()) {
            case R.id.oilChangeButton:

                appointment.setServiceType("oilChange");
                break;

            case R.id.tireRotationButton:

                appointment.setServiceType("tireRotation");
                break;

            case R.id.maintenanceButton:

                appointment.setServiceType("maintenance");
                break;

            case R.id.otherButton:

                appointment.setServiceType("other");
                return;

            default:

                break;
        }

//        Intent intent = new Intent(getActivity(), RequestAppointmentActivity.class);
//        intent.putExtra(Args.APPOINTMENT, appointment);
//        startActivity(intent);

        SelectAppointmentTypeFragment selectAppointmentTypeFragment = SelectAppointmentTypeFragment.newInstance(appointment);
        showFragmentWithSlidingTransition(selectAppointmentTypeFragment, true);
    }

    @Override
    protected int getFragmentLayoutId() {
        return R.layout.fragment_select_service;
    }
}

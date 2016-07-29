package com.bemore.southerntireservice.ui.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.bemore.southerntireservice.R;
import com.bemore.southerntireservice.constants.Args;
import com.bemore.southerntireservice.model.Appointment;
import com.bemore.southerntireservice.model.AppointmentType;
import com.bemore.southerntireservice.model.User;
import com.bemore.southerntireservice.model.Vehicle;
import com.bemore.southerntireservice.utils.LauncherUtils;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.SaveCallback;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.Bind;

/**
 * Created by Cody on 4/5/16.
 */
public class RequestAppointmentFragment extends BaseFragment {

    @Bind(R.id.calendarView)
    protected MaterialCalendarView calendarView;

    private Appointment appointment;

    public static RequestAppointmentFragment newInstance(Appointment appointment) {
        return newInstance(new RequestAppointmentFragment(), appointment);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        appointment = getArguments().getParcelable(Args.APPOINTMENT);
        appointment.setUser(User.getCurrentUser());

        calendarView.setMinimumDate(new Date());
        calendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {

                if (appointment == null) {
                    appointment = Appointment.newBuilder()
                            .startTime(date.getDate())
                            .build();
                }
                else {
                    appointment.setDateTime(date.getDate());
                }

                appointment.sync();

                if (appointment.getAppointmentType().equals(AppointmentType.WAITING)) {
                    ChooseTimeFragment chooseTimeFragment = ChooseTimeFragment.newInstance(appointment);
                    showFragmentWithSlidingTransition(chooseTimeFragment, true);
                }
                else {
                    showSelectCarDialog();
                }
            }
        });
    }

    private void showSelectCarDialog() {
        ParseQuery<Vehicle> query = new ParseQuery(Vehicle.class);
        query.whereEqualTo("user", User.getCurrentUser())
                .addDescendingOrder(Vehicle.KEY_YEAR)
                .findInBackground(new FindCallback<Vehicle>() {
                    @Override
                    public void done(final List<Vehicle> vehicles, ParseException e) {
                        if (vehicles != null) {
                            new MaterialDialog.Builder(getActivity())
                                    .title("Select Vehicle")
                                    .positiveText("Select")
                                    .negativeText("Cancel")
                                    .items(vehicles)
                                    .itemsCallbackSingleChoice(-1, new MaterialDialog.ListCallbackSingleChoice() {
                                        @Override
                                        public boolean onSelection(MaterialDialog dialog, View itemView, int which, CharSequence text) {

                                            appointment.setVehicle(vehicles.get(which));
                                            showAppointmentConfirmation();
                                            return false;
                                        }
                                    })
                                    .show();
                        }
                        else {
                            showAppointmentConfirmation();
                        }
                    }
                });
    }

    private void showAppointmentConfirmation() {
        String appointmentText = SimpleDateFormat.getDateInstance(DateFormat.FULL).format(appointment.getDateTime());
        appointmentText += "\nReminder that by selecting Drop Off, you are agreeing to being patient until you receive a call from us that the service has been completed.";

        new MaterialDialog.Builder(getActivity())
                .title("Request Appointment")
                .content("Confirm appointment request for " + appointmentText)
                .positiveText("Confirm")
                .negativeText("Cancel")
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        appointment.saveInBackground(new SaveCallback() {
                            @Override
                            public void done(ParseException e) {

                                if (e != null) {
                                    Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                                }
                                else {
                                    Toast.makeText(getContext(), "Appointment Created", Toast.LENGTH_SHORT).show();
                                    LauncherUtils.launchMainActivity(getContext());
                                }
                            }
                        });
                    }
                })
                .show();
    }

    @Override
    protected int getFragmentLayoutId() {
        return R.layout.fragment_calendar;
    }
}

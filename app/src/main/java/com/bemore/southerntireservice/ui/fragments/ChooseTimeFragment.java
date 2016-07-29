package com.bemore.southerntireservice.ui.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.bemore.southerntireservice.R;
import com.bemore.southerntireservice.constants.Args;
import com.bemore.southerntireservice.model.Appointment;
import com.bemore.southerntireservice.model.Schedule;
import com.bemore.southerntireservice.model.User;
import com.bemore.southerntireservice.model.Vehicle;
import com.bemore.southerntireservice.utils.LauncherUtils;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.Bind;

/**
 * Created by Cody on 4/6/16.
 */
public class ChooseTimeFragment extends BaseFragment {

    private List<String> timeSlots;
    private List<String> unavailableTimeSlots;

    @Bind(R.id.morningTimeSlots)
    protected ViewGroup morningTimeSlotsView;

    @Bind(R.id.afternoonTimeSlots)
    protected ViewGroup afternoonTimeSlotsView;

    @Bind(R.id.progressBar)
    protected ProgressBar progressBar;

    private Appointment appointment;

    public static ChooseTimeFragment newInstance(Appointment appointment) {
        return newInstance(new ChooseTimeFragment(), appointment);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        appointment = getArguments().getParcelable(Args.APPOINTMENT);
        appointment.setUser(User.getCurrentUser());

        queryAvailableAppointments();
    }

    private void queryAvailableAppointments() {

        progressBar.setVisibility(View.VISIBLE);

        ParseQuery<Schedule> scheduleQuery = new ParseQuery("Schedule");
        scheduleQuery.whereEqualTo("dayOfWeek", appointment.getDayOfWeek());

        scheduleQuery.getFirstInBackground(new GetCallback<Schedule>() {
            @Override
            public void done(Schedule schedule, ParseException e) {

                timeSlots = schedule.getTimeSlots();

                ParseQuery<Appointment> appointmentQuery = new ParseQuery("Appointment");
                appointmentQuery.whereEqualTo("date", appointment.getDate());
                appointmentQuery.whereEqualTo("month", appointment.getMonth());
                appointmentQuery.whereEqualTo("year", appointment.getYear());

                appointmentQuery.findInBackground(new FindCallback<Appointment>() {
                    @Override
                    public void done(List<Appointment> appointments, ParseException e) {
                        if (appointments != null) {
                            unavailableTimeSlots = new ArrayList();
                            for (Appointment appointment : appointments) {
                                unavailableTimeSlots.add(String.valueOf(appointment.getTime()));
                            }
                        }

                        populateTimeSlots();
                    }
                });
            }
        });
    }

    private void populateTimeSlots() {

        if (!isAdded()) {
            return;
        }

        progressBar.setVisibility(View.GONE);

        morningTimeSlotsView.removeAllViews();
        afternoonTimeSlotsView.removeAllViews();

        if (timeSlots == null) {

            // closed
            return;
        }

        for (String timeSlotStr : timeSlots) {

            Integer timeSlot = Integer.valueOf(timeSlotStr);
            Button button = new Button(getContext());
            button.setTextColor(getResources().getColor(R.color.white));

            int hours = timeSlot / 60;
            int mins = timeSlot % 60;

            NumberFormat f = new DecimalFormat("00");
            String text;

            if (timeSlot < 720) {

                text = hours + ":" + f.format(mins) + " AM";
                button.setText(text);
                morningTimeSlotsView.addView(button);
            }
            else {

                if (hours > 12) {
                    hours = hours - 12;
                }

                text = hours + ":" + f.format(mins) + " PM";
                button.setText(text);
                afternoonTimeSlotsView.addView(button);
            }

            button.setTag(timeSlot);

            if (unavailableTimeSlots != null && unavailableTimeSlots.contains(timeSlotStr)) {
                button.setEnabled(false);
            }

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Integer timeSlot = (Integer) view.getTag();
                    onAppointmentSelected(timeSlot);
                }
            });
        }
    }

    private void onAppointmentSelected(int timeSlot) {

        appointment.setTime(timeSlot);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(appointment.getDateTime());

        calendar.set(Calendar.HOUR_OF_DAY, appointment.getHours());
        calendar.set(Calendar.MINUTE, appointment.getMinutes());

        appointment.setDateTime(calendar.getTime());
        appointment.sync();

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
        String appointmentText = SimpleDateFormat.getDateInstance(DateFormat.FULL).format(appointment.getDateTime())
                + " @ " + SimpleDateFormat.getTimeInstance(DateFormat.SHORT).format(appointment.getDateTime());

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
                                    queryAvailableAppointments();
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
        return R.layout.fragment_choose_time;
    }
}

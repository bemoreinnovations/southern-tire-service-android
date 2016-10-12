package com.bemore.southerntireservice.otto.events;

import com.bemore.southerntireservice.model.Appointment;

/**
 * Created by Cody on 10/5/16.
 */
public class EditAppointmentEvent {
    public Appointment appointment;

    public EditAppointmentEvent(Appointment appointment) {
        this.appointment = appointment;
    }
}

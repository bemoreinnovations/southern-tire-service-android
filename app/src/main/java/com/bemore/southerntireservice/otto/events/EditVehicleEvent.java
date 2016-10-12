package com.bemore.southerntireservice.otto.events;

import com.bemore.southerntireservice.model.Vehicle;

/**
 * Created by Cody on 10/5/16.
 */
public class EditVehicleEvent {
    public Vehicle vehicle;

    public EditVehicleEvent(Vehicle vehicle) {
        this.vehicle = vehicle;
    }
}

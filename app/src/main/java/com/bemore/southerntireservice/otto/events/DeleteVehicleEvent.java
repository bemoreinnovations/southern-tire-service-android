package com.bemore.southerntireservice.otto.events;

import com.bemore.southerntireservice.model.Vehicle;

/**
 * Created by Cody on 10/5/16.
 */
public class DeleteVehicleEvent {
    public Vehicle vehicle;

    public DeleteVehicleEvent(Vehicle vehicle) {
        this.vehicle = vehicle;
    }
}

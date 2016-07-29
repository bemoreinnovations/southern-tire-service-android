package com.bemore.southerntireservice;

import android.app.Application;

import com.bemore.southerntireservice.constants.ApiConfig;
import com.bemore.southerntireservice.model.Appointment;
import com.bemore.southerntireservice.model.Schedule;
import com.bemore.southerntireservice.model.TimeSlot;
import com.bemore.southerntireservice.model.User;
import com.bemore.southerntireservice.model.Vehicle;
import com.bemore.southerntireservice.network.NetModule;
import com.parse.Parse;
import com.parse.ParseObject;

/**
 * Created by Cody on 4/6/16.
 */
public class ServiceApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        NetModule.init(this, ApiConfig.SERVER_URL);
//        Injector.init(this);
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId(ApiConfig.APP_ID)
                .server(ApiConfig.SERVER_URL)
                .build());

        ParseObject.registerSubclass(User.class);
        ParseObject.registerSubclass(Schedule.class);
        ParseObject.registerSubclass(Appointment.class);
        ParseObject.registerSubclass(TimeSlot.class);
        ParseObject.registerSubclass(Vehicle.class);
    }
}

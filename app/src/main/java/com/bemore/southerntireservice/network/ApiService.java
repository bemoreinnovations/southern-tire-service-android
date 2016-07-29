package com.bemore.southerntireservice.network;

import com.bemore.southerntireservice.model.Appointment;

import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by Cody on 4/6/16.
 */
public interface ApiService {

    @POST("classes/appointment")
    Observable createAppointment(@Body Appointment appointment);
}

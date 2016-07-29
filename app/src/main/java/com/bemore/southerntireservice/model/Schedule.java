package com.bemore.southerntireservice.model;

import com.parse.ParseClassName;
import com.parse.ParseObject;

import java.util.List;

/**
 * Created by Cody on 5/10/16.
 */
@ParseClassName("Schedule")
public class Schedule extends ParseObject {

    public List<String> getTimeSlots() {
        return getList("timeSlots");
    }

    public String getDayOfWeek() {
        return getString("dayOfWeek");
    }
}

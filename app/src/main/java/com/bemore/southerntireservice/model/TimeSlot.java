package com.bemore.southerntireservice.model;

import com.parse.ParseClassName;
import com.parse.ParseObject;

/**
 * Created by Cody on 5/9/16.
 */
@ParseClassName("TimeSlot")
public class TimeSlot extends ParseObject {

    public static String FIELD_TIME = "time";

    public TimeSlot() {
    }

    public Number getTime() {
        return getNumber(FIELD_TIME);
    }
}

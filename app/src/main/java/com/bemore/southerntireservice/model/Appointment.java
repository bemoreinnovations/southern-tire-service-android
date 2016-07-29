package com.bemore.southerntireservice.model;

import android.os.Parcel;

import com.bemore.southerntireservice.constants.Args;
import com.parse.ParseClassName;
import com.parse.ParseObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Cody on 4/6/16.
 */
@ParseClassName("Appointment")
public class Appointment extends ParseObject implements ParcelableArg {

    public String id;

    public Date dateTime;
    public Integer time;
    public Integer date;
    public Integer month;
    public Integer year;

    public Date startTime;
    public Date endTime;
    public String name;
    public String email;
    public Vehicle vehicle;
    public String comments;

    private User user;
    private String serviceType;
    private String appointmentType;

    private Appointment(Builder builder) {
        id = builder.id;
        setDateTime(builder.dateTime);
        time = builder.time;
        date = builder.date;
        month = builder.month;
        year = builder.year;
        startTime = builder.startTime;
        endTime = builder.endTime;
        name = builder.name;
        email = builder.email;
        setVehicle(builder.vehicle);
        comments = builder.comments;
        setUser(builder.user);
        setServiceType(builder.serviceType);
        setAppointmentType(builder.appointmentType);
    }

    public int getTime() {

        if (time == null) {
            if (getNumber("time") != null) {
                time = getNumber("time").intValue();
            }
            else {
                time = new Integer(0);
            }
        }

        return time;
    }

    public void setTime(int time) {

        this.time = time;
        put("time", time);
    }

    public Date getDateTime() {

        if (dateTime == null) {
            dateTime = getDate("dateTime");
        }

        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
        put("dateTime", dateTime);
    }

    public void setDayOfWeek(String dayOfWeek) {
        put("dayOfWeek", dayOfWeek);
    }

    public void setDate(int date) {
        this.date = date;
        put("date", date);
    }

    public void setMonth(int month) {
        this.month = month;
        put("month", month);
    }

    public void setYear(int year) {
        this.year = year;
        put("year", year);
    }

    public void setUser(User user) {
        this.user = user;
        put("user", user);
    }

    public User getUser() {
        if (user == null) {
            user = (User) getParseUser("user");
        }

        return user;
    }

    public Vehicle getVehicle() {
        if (vehicle == null) {
            vehicle = (Vehicle) get("vehicle");
        }

        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
        put("vehicle", vehicle);
    }

    public String getFormattedDateTime() {
        String formattedDateTime = SimpleDateFormat.getDateInstance(DateFormat.FULL).format(getDateTime())
                + " @ " + SimpleDateFormat.getTimeInstance(DateFormat.SHORT).format(getDateTime());

        return formattedDateTime;
    }

    public int getHours() {
        return time / 60;
    }

    public int getMinutes() {
        return time % 60;
    }

    public void sync() {
        setDate(getDate());
        setMonth(getMonth());
        setYear(getYear());
        setDayOfWeek(getDayOfWeek());
        setServiceType(getServiceType());
        setAppointmentType(getAppointmentType());
    }

    public String getServiceType() {
        if (serviceType == null) {
            serviceType = getString("serviceType");
        }

        return serviceType;
    }

    public String getAppointmentType() {
        if (appointmentType == null) {
            appointmentType = getString("appointmentType");
        }

        return appointmentType;
    }

    public String getFormattedType() {

        String formattedType;
        switch (getServiceType()) {
            case "oilChange":
                formattedType = "Oil Change";
                break;

            case "tireRotation":
                formattedType = "Tire Rotation";
                break;

            case "maintenance":
                formattedType = "Preventative Maintenance";
                break;

            case "other":
                formattedType = "Other";
                break;

            default:
                formattedType = serviceType;
                break;
        }

        return formattedType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
        put("serviceType", serviceType);
    }

    public void setAppointmentType(String appointmentType) {
        this.appointmentType = appointmentType;
        put("appointmentType", appointmentType);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public String getDayOfWeek() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(getDateTime());

        String dayOfWeek = calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault());

        return dayOfWeek;
    }

    public int getDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(getDateTime());

        return calendar.get(Calendar.DATE);
    }

    public int getMonth() {

        if (month == null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(getDateTime());

            month =  calendar.get(Calendar.MONTH) + 1;
        }

        return month;
    }

    public int getYear() {

        if (year == null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(getDateTime());

            year =  calendar.get(Calendar.YEAR);
        }

        return year;
    }

    public Appointment() {
    }

    public static final class Builder {
        private String id;
        private Date dateTime;
        private Integer time;
        private Integer date;
        private Integer month;
        private Integer year;
        private Date startTime;
        private Date endTime;
        private String name;
        private String email;
        private Vehicle vehicle;
        private String comments;
        private User user;
        private String serviceType;
        private String appointmentType;

        private Builder() {
        }

        public Builder id(String val) {
            id = val;
            return this;
        }

        public Builder dateTime(Date val) {
            dateTime = val;
            return this;
        }

        public Builder time(Integer val) {
            time = val;
            return this;
        }

        public Builder date(Integer val) {
            date = val;
            return this;
        }

        public Builder month(Integer val) {
            month = val;
            return this;
        }

        public Builder year(Integer val) {
            year = val;
            return this;
        }

        public Builder startTime(Date val) {
            startTime = val;
            return this;
        }

        public Builder endTime(Date val) {
            endTime = val;
            return this;
        }

        public Builder name(String val) {
            name = val;
            return this;
        }

        public Builder email(String val) {
            email = val;
            return this;
        }

        public Builder vehicle(Vehicle val) {
            vehicle = val;
            return this;
        }

        public Builder comments(String val) {
            comments = val;
            return this;
        }

        public Builder user(User val) {
            user = val;
            return this;
        }

        public Builder serviceType(String val) {
            serviceType = val;
            return this;
        }

        public Builder appointmentType(String val) {
            appointmentType = val;
            return this;
        }

        public Appointment build() {
            return new Appointment(this);
        }
    }

    @Override
    public String getArgName() {
        return Args.APPOINTMENT;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeLong(dateTime != null ? dateTime.getTime() : -1);
        dest.writeValue(this.time);
        dest.writeValue(this.date);
        dest.writeValue(this.month);
        dest.writeValue(this.year);
        dest.writeLong(startTime != null ? startTime.getTime() : -1);
        dest.writeLong(endTime != null ? endTime.getTime() : -1);
        dest.writeString(this.name);
        dest.writeString(this.email);
        dest.writeString(this.comments);
        dest.writeString(this.serviceType);
        dest.writeString(this.appointmentType);
    }

    protected Appointment(Parcel in) {
        this.id = in.readString();
        long tmpDateTime = in.readLong();
        this.dateTime = tmpDateTime == -1 ? null : new Date(tmpDateTime);
        this.time = (Integer) in.readValue(Integer.class.getClassLoader());
        this.date = (Integer) in.readValue(Integer.class.getClassLoader());
        this.month = (Integer) in.readValue(Integer.class.getClassLoader());
        this.year = (Integer) in.readValue(Integer.class.getClassLoader());
        long tmpStartTime = in.readLong();
        this.startTime = tmpStartTime == -1 ? null : new Date(tmpStartTime);
        long tmpEndTime = in.readLong();
        this.endTime = tmpEndTime == -1 ? null : new Date(tmpEndTime);
        this.name = in.readString();
        this.email = in.readString();
        this.comments = in.readString();
        this.serviceType = in.readString();
        this.appointmentType = in.readString();
    }

    public static final Creator<Appointment> CREATOR = new Creator<Appointment>() {
        @Override
        public Appointment createFromParcel(Parcel source) {
            return new Appointment(source);
        }

        @Override
        public Appointment[] newArray(int size) {
            return new Appointment[size];
        }
    };
}

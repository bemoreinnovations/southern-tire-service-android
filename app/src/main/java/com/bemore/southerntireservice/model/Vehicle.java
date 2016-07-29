package com.bemore.southerntireservice.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.parse.ParseClassName;
import com.parse.ParseObject;

/**
 * Created by Cody on 5/10/16.
 */
@ParseClassName("Vehicle")
public class Vehicle extends ParseObject implements Parcelable {

    public static final String KEY_YEAR = "year";
    public static final String KEY_MAKE = "make";
    public static final String KEY_MODEL = "model";
    public static final String KEY_COLOR = "color";
    public static final String KEY_VIN = "vin";
    public static final String KEY_USER = "user";

    private Integer year;
    private String make;
    private String model;
    private String color;
    private String vin;
    private User user;

    private Vehicle(Builder builder) {
        setYear(builder.year);
        setMake(builder.make);
        setModel(builder.model);
        setColor(builder.color);
        setVin(builder.vin);
        setUser(builder.user);
    }

    public Vehicle() {
    }

    public int getYear() {
        if (year == null) {
            year = (Integer) get(KEY_YEAR);
        }

        return year;
    }

    public void setYear(int year) {
        this.year = year;
        put(KEY_YEAR, year);
    }

    public String getMake() {
        if (make == null) {
            make = (String) get(KEY_MAKE);
        }

        return make;
    }

    public void setMake(String make) {
        this.make = make;
        put(KEY_MAKE, make);
    }

    public String getModel() {
        if (model == null) {
            model = (String) get(KEY_MODEL);
        }

        return model;
    }

    public void setModel(String model) {
        this.model = model;
        put(KEY_MODEL, model);
    }

    public String getColor() {
        if (color == null) {
            color = (String) get(KEY_COLOR);
        }

        return color;
    }

    public void setColor(String color) {
        this.color = color;
        put(KEY_COLOR, color);
    }

    public String getVin() {
        if (vin == null) {
            vin = (String) get(KEY_VIN);
        }

        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
        put(KEY_VIN, vin);
    }

    public User getUser() {
        if (user == null) {
            user = (User) get(KEY_USER);
        }

        return user;
    }

    public void setUser(User user) {
        this.user = user;
        put(KEY_USER, user);
    }

    public static final class Builder {
        private Integer year;
        private String make;
        private String model;
        private String color;
        private String vin;
        private User user;

        public Builder() {
        }

        public Builder year(Integer val) {
            year = val;
            return this;
        }

        public Builder make(String val) {
            make = val;
            return this;
        }

        public Builder model(String val) {
            model = val;
            return this;
        }

        public Builder color(String val) {
            color = val;
            return this;
        }

        public Builder vin(String val) {
            vin = val;
            return this;
        }

        public Builder user(User val) {
            user = val;
            return this;
        }

        public Vehicle build() {
            return new Vehicle(this);
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.year);
        dest.writeString(this.make);
        dest.writeString(this.model);
        dest.writeString(this.color);
        dest.writeString(this.vin);
    }

    protected Vehicle(Parcel in) {
        this.year = (Integer) in.readValue(Integer.class.getClassLoader());
        this.make = in.readString();
        this.model = in.readString();
        this.color = in.readString();
        this.vin = in.readString();
    }

    public static final Creator<Vehicle> CREATOR = new Creator<Vehicle>() {
        @Override
        public Vehicle createFromParcel(Parcel source) {
            return new Vehicle(source);
        }

        @Override
        public Vehicle[] newArray(int size) {
            return new Vehicle[size];
        }
    };

    public String getDisplay() {
        return getYear() + " " + getMake() + " " + getModel();
    }

    @Override
    public String toString() {
        return getDisplay();
    }
}

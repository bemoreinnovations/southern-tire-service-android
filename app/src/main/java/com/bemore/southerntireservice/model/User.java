package com.bemore.southerntireservice.model;

import android.text.TextUtils;

import com.parse.ParseUser;

/**
 * Created by Cody on 5/11/16.
 */
public class User extends ParseUser {

    private static final String KEY_FIRST_NAME = "firstName";
    private static final String KEY_LAST_NAME = "lastName";

    public String getDisplayName() {
        String displayName;
        if (TextUtils.isEmpty(getFirstName())) {
            displayName = getLastName();
        }
        else if (TextUtils.isEmpty(getLastName())) {
            displayName = getFirstName();
        }
        else {
            displayName = getFirstName() + " " + getLastName();
        }

        return displayName;
    }

    public String getFirstName() {
        return (String) get(KEY_FIRST_NAME);
    }

    public void setFirstName(String firstName) {
        put(KEY_FIRST_NAME, firstName);
    }

    public String getLastName() {
        return (String) get(KEY_LAST_NAME);
    }

    public void setLastName(String lastName) {
        put(KEY_LAST_NAME, lastName);
    }

    public static User getCurrentUser() {
        return (User) ParseUser.getCurrentUser();
    }
}

package com.bemore.southerntireservice.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.bemore.southerntireservice.MainActivity;
import com.bemore.southerntireservice.model.User;
import com.bemore.southerntireservice.ui.fragments.LoginFragment;

/**
 * Created by Cody on 5/11/16.
 */
public class LandingActivity extends ContainerActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (User.getCurrentUser() != null) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public Fragment getFragment() {
        return new LoginFragment();
    }
}

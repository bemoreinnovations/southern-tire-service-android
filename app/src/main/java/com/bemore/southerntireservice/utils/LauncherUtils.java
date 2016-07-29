package com.bemore.southerntireservice.utils;

import android.content.Context;
import android.content.Intent;

import com.bemore.southerntireservice.ui.activities.LandingActivity;

/**
 * Created by Cody on 6/1/16.
 */
public class LauncherUtils {

    public static void launchMainActivity(Context context) {
        Intent intent = new Intent(context, LandingActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);

        context.startActivity(intent);
    }
}

package com.bemore.southerntireservice.ui.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import com.bemore.southerntireservice.R;


/**
 * Created by Cody on 11/21/15.
 */
public abstract class ContainerActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addFragment(getFragment(), false);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_content;
    }

    public abstract Fragment getFragment();

    protected void addFragment(android.support.v4.app.Fragment fragment, boolean addToBackStack)
    {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content, fragment);

        if (addToBackStack)
        {
            transaction.addToBackStack(null);
        }

        transaction.commit();
    }
}

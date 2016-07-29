package com.bemore.southerntireservice.ui.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import butterknife.ButterKnife;

/**
 * Created by Cody on 11/16/15.
 */
public abstract class BaseActivity extends AppCompatActivity {

//    protected ScopedBus scopedBus = new ScopedBus();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(getLayoutId());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:

                onBackPressed();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);

        ButterKnife.bind(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
//        scopedBus.resumed();
    }

    @Override
    protected void onPause() {
        super.onPause();
//        scopedBus.paused();
    }

    public abstract int getLayoutId();
}

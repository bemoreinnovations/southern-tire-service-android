package com.bemore.southerntireservice.dagger;

import android.app.Application;

import com.bemore.southerntireservice.MainActivity;
import com.bemore.southerntireservice.ServiceApp;

/**
 * Created by Cody on 4/6/16.
 */
public class Injector implements AppComponent {

    private static NetComponent mNetComponent;

    public static void init(Application app) {
        mNetComponent = DaggerNetComponent.builder()
                // list of modules that are part of this component need to be created here too
                .appModule(new AppModule(app)) // This also corresponds to the name of your module: %component_name%Module
                .netModule(new NetModule("https://api.github.com"))
                .build();
    }

    @Override
    public void inject(MainActivity activity) {
        mNetComponent.inject(activity);
    }

    @Override
    public void inject(ServiceApp app) {
        mNetComponent.inject(app);
    }
}

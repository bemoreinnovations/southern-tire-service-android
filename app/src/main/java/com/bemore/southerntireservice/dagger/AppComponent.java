package com.bemore.southerntireservice.dagger;

import com.bemore.southerntireservice.MainActivity;
import com.bemore.southerntireservice.ServiceApp;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {
    void inject(ServiceApp app);
    void inject(MainActivity activity);
}
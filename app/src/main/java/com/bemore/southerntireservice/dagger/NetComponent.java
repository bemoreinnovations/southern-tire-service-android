package com.bemore.southerntireservice.dagger;

import com.bemore.southerntireservice.MainActivity;
import com.bemore.southerntireservice.ServiceApp;

import javax.inject.Singleton;

import dagger.Component;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

@Singleton
@Component(modules = {AppModule.class, NetModule.class})
public interface NetComponent {
    void inject(MainActivity activity);

    void inject(ServiceApp app);
    // void inject(MyService service);

    Retrofit retrofit();
    OkHttpClient okHttpClient();
}
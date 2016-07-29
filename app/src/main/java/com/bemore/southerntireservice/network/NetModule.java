package com.bemore.southerntireservice.network;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetModule {

    private static Application app;
    private static String mBaseUrl;
    private static ApiService apiService;

    // Constructor needs one parameter to instantiate.  
    public static void init(Application application, String baseUrl) {
        app = application;
        mBaseUrl = baseUrl;
    }

    // Dagger will only look for methods annotated with @Provides
    @Provides
    @Singleton
    // Application reference must come from AppModule.class
    public static SharedPreferences providesSharedPreferences(Application application) {
        return PreferenceManager.getDefaultSharedPreferences(application);
    }

    @Provides
    @Singleton
    public static Cache provideOkHttpCache(Application application) {
        int cacheSize = 10 * 1024 * 1024; // 10 MiB
        Cache cache = new Cache(application.getCacheDir(), cacheSize);
        return cache;
    }

   @Provides 
   @Singleton
   public static Gson provideGson() {
       GsonBuilder gsonBuilder = new GsonBuilder();
       gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
       return gsonBuilder.create();
   }

   @Provides
   @Singleton
   public static OkHttpClient provideOkHttpClient(Cache cache) {

       OkHttpClient client = new OkHttpClient.Builder()
               .cache(cache)
               .build();

       return client;
   }

   @Provides
   @Singleton
   public static Retrofit provideRetrofit(Gson gson, OkHttpClient okHttpClient) {
      Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(mBaseUrl)
                .client(okHttpClient)
                .build();

        return retrofit;
    }

    public static ApiService apiService() {

        if (apiService == null) {
            apiService = provideRetrofit(provideGson(), provideOkHttpClient(provideOkHttpCache(app))).create(ApiService.class);
        }

        return apiService;
    }
}
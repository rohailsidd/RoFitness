package com.rofitness.fitnessapp.di;

import android.app.Application;
import android.content.Context;

import com.rofitness.fitnessapp.AppPreferences;

import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
public class AppModule {
    @Provides
    @Singleton
    Context providesContext(Application application) {
        return application;
    }

    @Provides
    @Singleton
    AppPreferences providesAppPreferences(Context context) {
        return new AppPreferences(context);
    }
}

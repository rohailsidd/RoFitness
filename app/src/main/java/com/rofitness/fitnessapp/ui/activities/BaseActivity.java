package com.rofitness.fitnessapp.ui.activities;

import android.content.Context;
import android.os.Bundle;

import com.rofitness.fitnessapp.AppPreferences;
import com.rofitness.fitnessapp.FitnessApp;

import dagger.android.support.DaggerAppCompatActivity;

import java.util.Locale;

import javax.inject.Singleton;

@Singleton
public abstract class BaseActivity extends DaggerAppCompatActivity {
    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        FitnessApp.getInstance().loadLocale();
    }

    @Override
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(FitnessApp.getInstance().setNewLocale(context, new AppPreferences(context).getString(AppPreferences.Key.language, Locale.getDefault().getLanguage()), false));
    }
}

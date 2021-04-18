package com.rofitness.fitnessapp.managers;

import android.content.SharedPreferences;

import com.rofitness.fitnessapp.FitnessApp;
import com.rofitness.fitnessapp.R;

public class SharedPreferencesManager {
    private static SharedPreferencesManager appPreferences;
    private SharedPreferences sharedPreferences;

    private SharedPreferencesManager() {
    }

    public static SharedPreferencesManager getInstance() {
        if (appPreferences == null) {
            SharedPreferencesManager sharedPreferencesManager = new SharedPreferencesManager();
            appPreferences = sharedPreferencesManager;
            if (sharedPreferencesManager.sharedPreferences == null) {
                FitnessApp instance = FitnessApp.getInstance();
                appPreferences.sharedPreferences = instance.getSharedPreferences(instance.getString(R.string.prefs), 0);
            }
        }
        return appPreferences;
    }

    public boolean getBoolean(String str) {
        return this.sharedPreferences.getBoolean(str, false);
    }

}

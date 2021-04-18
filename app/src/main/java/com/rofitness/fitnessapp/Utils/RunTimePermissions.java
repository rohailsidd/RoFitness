package com.rofitness.fitnessapp.Utils;

import android.os.Build;

import androidx.core.content.ContextCompat;

import com.rofitness.fitnessapp.FitnessApp;

public class RunTimePermissions {
    private static final RunTimePermissions ourInstance = new RunTimePermissions();

    private RunTimePermissions() {
    }

    public static RunTimePermissions getInstance() {
        return ourInstance;
    }

    public static boolean checkStoragePermission() {
        return Build.VERSION.SDK_INT < 23 || ContextCompat.checkSelfPermission(FitnessApp.getInstance().getApplicationContext(), "android.permission.READ_EXTERNAL_STORAGE") == 0 || ContextCompat.checkSelfPermission(FitnessApp.getInstance().getApplicationContext(), "android.permission.WRITE_EXTERNAL_STORAGE") == 0;
    }


}

package com.rofitness.fitnessapp;

import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Singleton;
import javax.inject.Inject;

@Singleton
public class AppPreferences {
    private static final String SETTINGS_NAME = "FitnessPreferences";
    private boolean mBulkUpdate = false;
    private SharedPreferences.Editor mEditor;
    private SharedPreferences mPref;

    @Inject
    public AppPreferences(Context context) {
        this.mPref = context.getSharedPreferences(SETTINGS_NAME, 0);
    }

    public void put(Key key, String str) {
        doEdit();
        this.mEditor.putString(key.name(), str);
        doCommit();
    }

    public void put(Key key, int i) {
        doEdit();
        this.mEditor.putInt(key.name(), i);
        doCommit();
    }

    public void put(Key key, boolean z) {
        doEdit();
        this.mEditor.putBoolean(key.name(), z);
        doCommit();
    }

    public void put(Key key, float f) {
        doEdit();
        this.mEditor.putFloat(key.name(), f);
        doCommit();
    }

    public void put(Key key, double d) {
        doEdit();
        this.mEditor.putString(key.name(), String.valueOf(d));
        doCommit();
    }

    public void put(Key key, long j) {
        doEdit();
        this.mEditor.putLong(key.name(), j);
        doCommit();
    }

    public String getString(Key key, String str) {
        return this.mPref.getString(key.name(), str);
    }

    public String getString(Key key) {
        return this.mPref.getString(key.name(), null);
    }

    public long getLong(Key key, long j) {
        return this.mPref.getLong(key.name(), j);
    }

    public boolean getBoolean(Key key, boolean z) {
        return this.mPref.getBoolean(key.name(), z);
    }

    private void doEdit() {
        if (!this.mBulkUpdate && this.mEditor == null) {
            this.mEditor = this.mPref.edit();
        }
    }

    private void doCommit() {
        SharedPreferences.Editor editor;
        if (!this.mBulkUpdate && (editor = this.mEditor) != null) {
            editor.commit();
            this.mEditor = null;
        }
    }

    public boolean getBoolean(String str, boolean z) {
        return this.mPref.getBoolean(str, z);
    }

    public void put(String str, boolean z) {
        doEdit();
        this.mEditor.putBoolean(str, z);
        doCommit();
    }

    public void put(String str, int i) {
        doEdit();
        this.mEditor.putInt(str, i);
        doCommit();
    }

    public void put(String str, String str2) {
        doEdit();
        this.mEditor.putString(str, str2);
        doCommit();
    }

    public String getString(String str, String str2) {
        return this.mPref.getString(str, str2);
    }

    public enum Key {
        isRunFirstTime,
        bmi,
        tapTargetRun,
        language,
        languageId,
        tapPlayVideo
    }
}

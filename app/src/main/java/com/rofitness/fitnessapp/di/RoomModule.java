package com.rofitness.fitnessapp.di;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.rofitness.fitnessapp.database.AppDatabase;
import com.rofitness.fitnessapp.database.dao.CategoryTypeProgressDao;
import com.rofitness.fitnessapp.database.dao.ExerciseDao;
import com.rofitness.fitnessapp.database.dao.ExerciseProgressDao;
import com.rofitness.fitnessapp.database.dao.ExerciseTranslationDao;
import com.rofitness.fitnessapp.database.dao.LanguageDao;
import com.rofitness.fitnessapp.database.dao.PlanDao;
import com.rofitness.fitnessapp.database.dao.UserAccountDao;

import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
public class RoomModule {
    private static final String TAG = "RoomModule";
    private static final String dbName = "FitenessDb";

    @Provides
    @Singleton
    public AppDatabase getDatabaseManager(Context context) {
        return (AppDatabase) Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, dbName).allowMainThreadQueries().fallbackToDestructiveMigration().addCallback(new RoomDatabase.Callback() {
            @Override
            public void onCreate(SupportSQLiteDatabase supportSQLiteDatabase) {
                super.onCreate(supportSQLiteDatabase);
                Log.d(RoomModule.TAG, "populating with data...");
                new AsyncTask<Void, Void, Void>() {
                    /* access modifiers changed from: protected */
                    public Void doInBackground(Void... voidArr) {
                        return null;
                    }
                }.execute(new Void[0]);
            }

            @Override
            public void onOpen(SupportSQLiteDatabase supportSQLiteDatabase) {
                super.onOpen(supportSQLiteDatabase);
            }
        }).build();
    }

    @Provides
    @Singleton
    public ExerciseDao providesExerciseDao(AppDatabase appDatabase) {
        return appDatabase.getExerciseDao();
    }

    @Provides
    @Singleton
    public ExerciseProgressDao providesExerciseProgressDao(AppDatabase appDatabase) {
        return appDatabase.getExerciseProgressDao();
    }

    @Provides
    @Singleton
    public UserAccountDao providesUserAccountDao(AppDatabase appDatabase) {
        return appDatabase.getUserAccountDao();
    }

    @Provides
    @Singleton
    public LanguageDao providesLanguageDao(AppDatabase appDatabase) {
        return appDatabase.getLanguageDao();
    }

    @Provides
    @Singleton
    public ExerciseTranslationDao providesExerciseTranslationDao(AppDatabase appDatabase) {
        return appDatabase.getExerciseTranslationDao();
    }

    @Provides
    @Singleton
    public PlanDao providesPlanDao(AppDatabase appDatabase) {
        return appDatabase.getPlanDao();
    }

    @Provides
    @Singleton
    public CategoryTypeProgressDao providesCategoryProgressTypeDao(AppDatabase appDatabase) {
        return appDatabase.getCategoryTypeProgressDao();
    }
}

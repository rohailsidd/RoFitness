package com.rofitness.fitnessapp.database;

import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import com.rofitness.fitnessapp.database.dao.CategoryTypeProgressDao;
import com.rofitness.fitnessapp.database.dao.ExerciseDao;
import com.rofitness.fitnessapp.database.dao.ExerciseProgressDao;
import com.rofitness.fitnessapp.database.dao.ExerciseTranslationDao;
import com.rofitness.fitnessapp.database.dao.LanguageDao;
import com.rofitness.fitnessapp.database.dao.PlanDao;
import com.rofitness.fitnessapp.database.dao.UserAccountDao;

public abstract class AppDatabase extends RoomDatabase {
    @Override
    protected InvalidationTracker createInvalidationTracker() {
        return null;
    }

    @Override
    protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration databaseConfiguration) {
        return null;
    }

    public abstract CategoryTypeProgressDao getCategoryTypeProgressDao();

    public abstract ExerciseDao getExerciseDao();

    public abstract ExerciseProgressDao getExerciseProgressDao();

    public abstract ExerciseTranslationDao getExerciseTranslationDao();

    public abstract LanguageDao getLanguageDao();

    public abstract PlanDao getPlanDao();

    public abstract UserAccountDao getUserAccountDao();
}

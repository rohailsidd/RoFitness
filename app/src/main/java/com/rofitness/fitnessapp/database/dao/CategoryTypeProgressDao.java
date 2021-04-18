package com.rofitness.fitnessapp.database.dao;

import androidx.lifecycle.LiveData;

import com.rofitness.fitnessapp.database.entities.CategoryTypeProgress;
import com.rofitness.fitnessapp.database.entities.Exercise;

import java.util.List;

public abstract class CategoryTypeProgressDao implements BaseDao<CategoryTypeProgress> {
    public abstract LiveData<List<CategoryTypeProgress>> getAllCategoryTypeProgressForMainMuscleGroup(String str);

    public abstract Integer getAllCountForMainMuscleGroup(String str);

    public abstract Integer getCompletedCountForMainMuscleGroup(String str);

    public abstract boolean getExerciseFlag(long j, String str);

    public abstract List<Exercise> getExercisesByTag(String str, Long l);

    public abstract Integer getMainMuscleGroupExercisesCount(String str);

    public abstract void resetCategoryTypeProgress(String str, boolean z);

    public abstract void setCompleted(long j, String str, boolean z);

    public int getMainMuscleGroupProgress(String str) {
        Integer allCountForMainMuscleGroup = getAllCountForMainMuscleGroup(str);
        double intValue = (double) getCompletedCountForMainMuscleGroup(str).intValue();
        double intValue2 = (double) allCountForMainMuscleGroup.intValue();
        Double.isNaN(intValue);
        Double.isNaN(intValue2);
        return (int) Math.round((intValue / intValue2) * 100.0d);
    }
}

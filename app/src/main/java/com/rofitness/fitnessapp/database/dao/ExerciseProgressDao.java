package com.rofitness.fitnessapp.database.dao;

import androidx.lifecycle.LiveData;

import com.rofitness.fitnessapp.database.entities.ExerciseProgress;
import com.rofitness.fitnessapp.dto.UnCompletedDay;

import java.util.List;

public abstract class ExerciseProgressDao implements BaseDao<ExerciseProgress> {
    public abstract Integer getAllCountForDay(int i, int i2, int i3);

    public abstract Integer getAllCountForWeek(int i, int i2);

    public abstract LiveData<List<ExerciseProgress>> getAllExercieses();

    public abstract LiveData<List<ExerciseProgress>> getAllExerciesesProgressForPlan(int i);

    public abstract Integer getAllPlanCount(int i);

    public abstract Integer getCompletedCount(int i);

    public abstract Integer getCompletedCountForDay(int i, int i2, int i3);

    public abstract Integer getCompletedCountForWeek(int i, int i2);

    public abstract UnCompletedDay getLatestUnCompletedDay(int i);

    public abstract LiveData<Integer> getLatestWeekComplete();

    public abstract Integer getPlanDayExercisesCount(int i, int i2, int i3);

    public abstract List<UnCompletedDay> getUnCompletedDay(int i);

    public abstract boolean isExerciseCompleted(int i, int i2, int i3, long j);

    public abstract LiveData<List<ExerciseProgress>> observeDayExercises(int i, int i2, int i3);

    public abstract void resetPlanProgress(int i, boolean z);

    public abstract void setExerciseCompleted(int i, int i2, int i3, long j, boolean z);

    public Integer daysLeft(int i) {
        return Integer.valueOf(getUnCompletedDay(i).size());
    }

    public Integer getProgressInPercentageForCompletePlan(int i) {
        Integer allPlanCount = getAllPlanCount(i);
        double intValue = (double) getCompletedCount(i).intValue();
        double intValue2 = (double) allPlanCount.intValue();
        Double.isNaN(intValue);
        Double.isNaN(intValue2);
        return Integer.valueOf((int) Math.round((intValue / intValue2) * 100.0d));
    }

    public Integer getProgressInPercentageForWeek(int i, int i2) {
        Integer allCountForWeek = getAllCountForWeek(i, i2);
        double intValue = (double) getCompletedCountForWeek(i, i2).intValue();
        double intValue2 = (double) allCountForWeek.intValue();
        Double.isNaN(intValue);
        Double.isNaN(intValue2);
        return Integer.valueOf((int) Math.round((intValue / intValue2) * 100.0d));
    }

    public Integer getProgressInPercentageForDay(int i, int i2, int i3) {
        Integer allCountForDay = getAllCountForDay(i, i2, i3);
        double intValue = (double) getCompletedCountForDay(i, i2, i3).intValue();
        double intValue2 = (double) allCountForDay.intValue();
        Double.isNaN(intValue);
        Double.isNaN(intValue2);
        return Integer.valueOf((int) Math.round((intValue / intValue2) * 100.0d));
    }

    public LiveData<Integer> getLatestWeekCompleteTest() {
        return getLatestWeekComplete();
    }
}

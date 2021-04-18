package com.rofitness.fitnessapp.ui.viewmodels;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.rofitness.fitnessapp.database.entities.ExerciseProgress;
import com.rofitness.fitnessapp.database.entities.PlanWrapper;
import com.rofitness.fitnessapp.dto.UnCompletedDay;
import com.rofitness.fitnessapp.repository.FitnessRepository;
import com.rofitness.fitnessapp.R;

import java.util.List;

import javax.inject.Inject;

public class PlanViewModel extends ViewModel {
    @Inject
    FitnessRepository fitnessRepository;

    public List<Long> insertExerciseProgressList(List<ExerciseProgress> list) {
        return this.fitnessRepository.insertExerciseProgressList(list);
    }

    public void resetPlanProgress(int i) {
        this.fitnessRepository.resetPlanProgress(i);
    }

    public Integer getPlanProgress(int i) {
        return this.fitnessRepository.getPlanCompletedPercentage(i);
    }

    public LiveData<List<ExerciseProgress>> getAllExercieses() {
        return this.fitnessRepository.getAllExercieses();
    }

    public LiveData<List<ExerciseProgress>> getAllExerciesesForPlan(int i) {
        return this.fitnessRepository.getAllExerciesesForPlan(i);
    }

    public Integer getAllPlanCount(int i) {
        return this.fitnessRepository.getAllPlanCount(i);
    }

    public Integer getPlanDayExercisesCount(int i, int i2, int i3) {
        return this.fitnessRepository.getPlanDayExercisesCount(i, i2, i3);
    }

    public UnCompletedDay getLatestUnCompletedDay(int i) {
        return this.fitnessRepository.getLatestUnCompletedDay(i);
    }

    public LiveData<Integer> getLatestWeekComplete() {
        return this.fitnessRepository.getLatestWeekComplete();
    }

    public Integer getProgressInPercentageForDay(int i, int i2, int i3) {
        return this.fitnessRepository.getProgressInPercentageForDay(i, i2, i3);
    }

    public LiveData<List<PlanWrapper>> getAllPlans() {
        return this.fitnessRepository.getAllPlans();
    }

    public String getDayLeft(Context context, int i) {
        int daysLeft = this.fitnessRepository.getDaysLeft(i);
        if (daysLeft <= 1) {
            return context.getResources().getString(R.string.text_working_day_left, Integer.valueOf(daysLeft));
        }
        return context.getResources().getString(R.string.text_working_days_left, Integer.valueOf(daysLeft));

    }
}

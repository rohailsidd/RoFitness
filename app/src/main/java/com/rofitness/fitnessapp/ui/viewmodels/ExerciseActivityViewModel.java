package com.rofitness.fitnessapp.ui.viewmodels;

import androidx.lifecycle.ViewModel;

import com.rofitness.fitnessapp.FitnessApp;
import com.rofitness.fitnessapp.database.entities.Exercise;
import com.rofitness.fitnessapp.repository.FitnessRepository;

import javax.inject.Inject;

public class ExerciseActivityViewModel extends ViewModel {
    @Inject
    FitnessRepository fitnessRepository;

    public float getMainBodyGroupProgress(String arg2) {
        if (this.fitnessRepository == null) {
            this.fitnessRepository = FitnessApp.getInstance().fitnessRepository;
        }

        return this.fitnessRepository.getMainBodyGroupProgress(arg2);
    }

    public float getMainMuscleProgress(String arg2) {
        return this.fitnessRepository.getMainMuscleGroupProgress(arg2);
    }

    public Exercise getSingleExercise(long arg2) {
        return this.fitnessRepository.getSingleExercise(arg2);
    }


    public boolean isExerciseCompleted(long arg2, String arg4) {
        return this.fitnessRepository.isExerciseCompletedFromCategory(Long.valueOf(arg2), arg4);
    }

    //   public void setExerciseCompleted(int arg7, int arg8, int arg9, long arg10) {
    //     this.fitnessRepository.setExerciseCompleted(arg7, arg8, arg9, arg10);
    //} rohail

    public void setExerciseCompleted(long arg2, String arg4) {
        this.fitnessRepository.setExerciseCompletedFromCategory(arg2, arg4);
    }
}


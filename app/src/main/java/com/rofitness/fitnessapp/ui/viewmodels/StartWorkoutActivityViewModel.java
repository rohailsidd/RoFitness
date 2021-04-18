package com.rofitness.fitnessapp.ui.viewmodels;

import androidx.lifecycle.ViewModel;

import com.rofitness.fitnessapp.database.entities.Exercise;
import com.rofitness.fitnessapp.repository.FitnessRepository;

import javax.inject.Inject;

public class StartWorkoutActivityViewModel extends ViewModel {
    @Inject
    FitnessRepository fitnessRepository;

    public Exercise getSingleExercise(long j) {
        return this.fitnessRepository.getSingleExercise(j);
    }
}

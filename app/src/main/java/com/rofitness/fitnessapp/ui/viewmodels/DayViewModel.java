package com.rofitness.fitnessapp.ui.viewmodels;

import androidx.lifecycle.ViewModel;

import com.rofitness.fitnessapp.database.entities.Exercise;
import com.rofitness.fitnessapp.repository.FitnessRepository;

import java.util.List;

import javax.inject.Inject;

public class DayViewModel extends ViewModel {
    @Inject
    FitnessRepository fitnessRepository;

    @Inject
    public DayViewModel(FitnessRepository fitnessRepository2) {
        this.fitnessRepository = fitnessRepository2;
    }


    public List<Exercise> getExercises(long j, int i, int i2) {
        return this.fitnessRepository.getExercies(j, i, i2);
    }

//    public LiveData<List<ExerciseProgress>> observeDayExercises(int i, int i2, int i3) {
    //      return this.fitnessRepository.observeDayExercises(i, i2, i3);
    //}   rohail

    //public LiveData<Integer> getLatestWeekComplete() {
    //  return this.fitnessRepository.getLatestWeekComplete();
    //}
    /////////////rohail
}

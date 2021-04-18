package com.rofitness.fitnessapp.ui.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.rofitness.fitnessapp.database.entities.CategoryTypeProgress;
import com.rofitness.fitnessapp.database.entities.Exercise;
import com.rofitness.fitnessapp.repository.FitnessRepository;

import java.util.List;

import javax.inject.Inject;

public class SubWorkoutsActivityViewModel extends ViewModel {
    @Inject
    FitnessRepository fitnessRepository;


    public int getSubExerciseCount(String str) {
        return this.fitnessRepository.getExerciseCountInsideSubCategory(str);
    }

    public List<Exercise> getExercisesByTag(String str) {
        return this.fitnessRepository.getExercisesByTag(str);
    }

    public LiveData<List<CategoryTypeProgress>> getAllCategoryTypeProgressForMainMuscleGroup(String str) {
        return this.fitnessRepository.getAllCategoryTypeProgressForMainMuscleGroup(str);
    }

    public float getMainMuscleProgress(String str) {
        return this.fitnessRepository.getMainMuscleGroupProgress(str);
    }

    public void resetProgress(String str) {
        this.fitnessRepository.resetMainMuscleProgress(str);
    }
}

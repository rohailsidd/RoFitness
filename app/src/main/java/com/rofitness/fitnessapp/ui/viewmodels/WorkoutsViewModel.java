package com.rofitness.fitnessapp.ui.viewmodels;

import androidx.lifecycle.ViewModel;

import com.rofitness.fitnessapp.FitnessApp;
import com.rofitness.fitnessapp.database.dataobjects.Category;
import com.rofitness.fitnessapp.database.dataobjects.SubCategory;
import com.rofitness.fitnessapp.dto.TargetMuscle;
import com.rofitness.fitnessapp.repository.FitnessRepository;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class WorkoutsViewModel extends ViewModel {
    @Inject
    FitnessRepository fitnessRepository;

    public List<Category> getCategories() {
        if (this.fitnessRepository == null) {
            this.fitnessRepository = FitnessApp.getInstance().fitnessRepository;
        }
        return this.fitnessRepository.getExerciseCategories();
    }

    public List<TargetMuscle> getSubCategories(String str) {
        if (this.fitnessRepository == null) {
            this.fitnessRepository = FitnessApp.getInstance().fitnessRepository;
        }
        List<SubCategory> subCategories = this.fitnessRepository.getSubCategories(str);
        ArrayList arrayList = new ArrayList();
        for (SubCategory subCategory : subCategories) {
            String imageLink = subCategory.getImageLink();
            String mainMuscleGroup = subCategory.getMainMuscleGroup();
            arrayList.add(new TargetMuscle(imageLink, mainMuscleGroup, getMainMuscleGroupProgress(mainMuscleGroup)));
        }
        return arrayList;
    }


    public void resetCategoryTypeProgress(String str) {
        if (this.fitnessRepository == null) {
            this.fitnessRepository = FitnessApp.getInstance().fitnessRepository;
        }
        this.fitnessRepository.resetCategoryTypeProgress(str);
    }

    public float getMainBodyGroupProgress(String str) {
        if (this.fitnessRepository == null) {
            this.fitnessRepository = FitnessApp.getInstance().fitnessRepository;
        }
        return this.fitnessRepository.getMainBodyGroupProgress(str);
    }

    public float getMainMuscleGroupProgress(String str) {
        if (this.fitnessRepository == null) {
            this.fitnessRepository = FitnessApp.getInstance().fitnessRepository;
        }
        return this.fitnessRepository.getMainMuscleGroupProgress(str);
    }


}

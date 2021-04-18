package com.rofitness.fitnessapp.database.dataobjects;

import com.rofitness.fitnessapp.Utils.AppConstants;

public class SubCategory {
    String mainMuscleGroup;

    public SubCategory(String str) {
        this.mainMuscleGroup = str;
    }

    public String getMainMuscleGroup() {
        return this.mainMuscleGroup;
    }

    public void setMainMuscleGroup(String str) {
        this.mainMuscleGroup = str;
    }

    public String getImageLink() {
        String replace = this.mainMuscleGroup.replace(" ", "").toLowerCase().trim().replace("-", "");
        return AppConstants.path + replace + ".webp";
    }
}

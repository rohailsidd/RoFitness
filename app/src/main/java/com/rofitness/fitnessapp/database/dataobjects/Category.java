package com.rofitness.fitnessapp.database.dataobjects;

import com.rofitness.fitnessapp.Utils.AppConstants;

public class Category {
    String mainBodyGroup;

    public Category(String str) {
        this.mainBodyGroup = str;
    }

    public String getMainBodyGroup() {
        return this.mainBodyGroup;
    }

    public void setMainBodyGroup(String str) {
        this.mainBodyGroup = str;
    }

    public String getImageLink() {
        String trim = this.mainBodyGroup.replace(" ", "").toLowerCase().trim();
        return AppConstants.path + trim + "workoutcard.webp";
    }
}

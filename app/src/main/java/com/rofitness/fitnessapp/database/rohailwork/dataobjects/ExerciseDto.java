package com.rofitness.fitnessapp.database.rohailwork.dataobjects;

import com.rofitness.fitnessapp.database.rohailwork.entities.NewExercise;

public class ExerciseDto extends NewExercise {
    private String detail;
    private String exerciseTitle;

    public String getExerciseTitle() {
        return this.exerciseTitle;
    }

    public void setExerciseTitle(String str) {
        this.exerciseTitle = str;
    }

    public String getDetail() {
        return this.detail;
    }

    public void setDetail(String str) {
        this.detail = str;
    }
}

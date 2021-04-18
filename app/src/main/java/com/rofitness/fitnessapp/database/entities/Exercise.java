package com.rofitness.fitnessapp.database.entities;

import com.rofitness.fitnessapp.database.rohailwork.entities.NewExercise;

public class Exercise extends NewExercise {
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

    public String getExerciseImage() {
        return getImage2Link();
    }

    public String getExerciseThumbnail() {
        return getImage1Link();
    }
}

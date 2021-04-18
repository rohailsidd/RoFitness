package com.rofitness.fitnessapp.database.rohailwork.entities;

public class ExerciseTranslation {
    private String detail;
    private Long exerciseId;
    private String exerciseTitle;
    private Long languageId;

    public Long getLanguageId() {
        return this.languageId;
    }

    public void setLanguageId(Long l) {
        this.languageId = l;
    }

    public Long getExerciseId() {
        return this.exerciseId;
    }

    public void setExerciseId(Long l) {
        this.exerciseId = l;
    }

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

package com.rofitness.fitnessapp.database.entities;

public class CategoryTypeProgress {
    private long exerciseId;
    private boolean isCompleted;
    private String mainMuscleGroup;

    public CategoryTypeProgress(String str, long j, boolean z) {
        this.mainMuscleGroup = str;
        this.exerciseId = j;
        this.isCompleted = z;
    }

    public String getMainMuscleGroup() {
        return this.mainMuscleGroup;
    }

    public void setMainMuscleGroup(String str) {
        this.mainMuscleGroup = str;
    }

    public long getExerciseId() {
        return this.exerciseId;
    }

    public void setExerciseId(long j) {
        this.exerciseId = j;
    }

    public boolean isCompleted() {
        return this.isCompleted;
    }

    public void setCompleted(boolean z) {
        this.isCompleted = z;
    }
}

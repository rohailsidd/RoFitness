package com.rofitness.fitnessapp.database.entities;

public class ExerciseProgress {
    private boolean completed;
    private int dayId;
    private long exerciseId;
    private int orderId;
    private int planId;
    private int weekId;

    public int getPlanId() {
        return this.planId;
    }

    public void setPlanId(int i) {
        this.planId = i;
    }

    public int getWeekId() {
        return this.weekId;
    }

    public void setWeekId(int i) {
        this.weekId = i;
    }

    public int getDayId() {
        return this.dayId;
    }

    public void setDayId(int i) {
        this.dayId = i;
    }

    public long getExerciseId() {
        return this.exerciseId;
    }

    public void setExerciseId(long j) {
        this.exerciseId = j;
    }

    public boolean isCompleted() {
        return this.completed;
    }

    public void setCompleted(boolean z) {
        this.completed = z;
    }

    public String getKey() {
        return this.planId + "_" + this.weekId + "_" + this.dayId + "_" + this.exerciseId;
    }

    public int getOrderId() {
        return this.orderId;
    }

    public void setOrderId(int i) {
        this.orderId = i;
    }
}

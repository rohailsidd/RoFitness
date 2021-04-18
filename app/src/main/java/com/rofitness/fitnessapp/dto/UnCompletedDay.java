package com.rofitness.fitnessapp.dto;

public class UnCompletedDay {
    private int dayId;
    private int weekId;

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
}

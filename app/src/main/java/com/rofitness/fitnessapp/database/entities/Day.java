package com.rofitness.fitnessapp.database.entities;

import java.util.List;

public class Day {
    private int dayId;
    private String dayTitle;
    private List<Long> exercises = null;

    public String getDayTitle() {
        return this.dayTitle;
    }

    public void setDayTitle(String str) {
        this.dayTitle = str;
    }

    public List<Long> getExercises() {
        return this.exercises;
    }

    public void setExercises(List<Long> list) {
        this.exercises = list;
    }

    public int getDayId() {
        return this.dayId;
    }

    public void setDayId(int i) {
        this.dayId = i;
    }
}

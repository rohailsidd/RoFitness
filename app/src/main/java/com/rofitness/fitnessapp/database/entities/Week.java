package com.rofitness.fitnessapp.database.entities;

import java.util.List;

public class Week {
    private List<Day> days = null;
    private int weekId;
    private String weekTitle;

    public String getWeekTitle() {
        return this.weekTitle;
    }

    public void setWeekTitle(String str) {
        this.weekTitle = str;
    }

    public int getWeekId() {
        return this.weekId;
    }

    public void setWeekId(int i) {
        this.weekId = i;
    }

    public List<Day> getDays() {
        return this.days;
    }

    public void setDays(List<Day> list) {
        this.days = list;
    }
}

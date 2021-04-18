package com.rofitness.fitnessapp.database.entities;

import java.util.List;

public class ChallengingPlan extends Plan {
    private List<Week> weeks = null;

    public List<Week> getWeeks() {
        return this.weeks;
    }

    public void setWeeks(List<Week> list) {
        this.weeks = list;
    }
}

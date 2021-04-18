package com.rofitness.fitnessapp.database.entities;

public class PlanWrapper {
    private Long id;
    private String plan;
    private String planType;

    public Long getId() {
        return this.id;
    }

    public void setId(Long l) {
        this.id = l;
    }

    public String getPlanType() {
        return this.planType;
    }

    public void setPlanType(String str) {
        this.planType = str;
    }

    public String getPlan() {
        return this.plan;
    }

    public void setPlan(String str) {
        this.plan = str;
    }
}

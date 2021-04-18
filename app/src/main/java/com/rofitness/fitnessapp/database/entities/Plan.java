package com.rofitness.fitnessapp.database.entities;

public abstract class Plan {
    protected String baseImagesPath = "asset:///images/plan_images/";
    private boolean isPremium;
    private int planId;
    private String planImage;
    private String planTitle;

    public boolean isPremium() {
        return this.isPremium;
    }

    public void setPremium(boolean z) {
        this.isPremium = z;
    }

    public int getPlanId() {
        return this.planId;
    }

    public void setPlanId(int i) {
        this.planId = i;
    }

    public String getPlanTitle() {
        return this.planTitle;
    }

    public void setPlanTitle(String str) {
        this.planTitle = str;
    }

    public String getPlanImage() {
        String lowerCase = this.planTitle.trim().replace(" ", "").toLowerCase();
        return this.baseImagesPath + lowerCase + ".webp";
    }

    public void setPlanImage(String str) {
        this.planImage = str;
    }

    public String getBaseImagesPath() {
        return this.baseImagesPath;
    }

    public void setBaseImagesPath(String str) {
        this.baseImagesPath = str;
    }
}

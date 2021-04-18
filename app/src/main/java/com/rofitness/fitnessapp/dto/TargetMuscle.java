package com.rofitness.fitnessapp.dto;

import com.rofitness.fitnessapp.Utils.AppConstants;

public class TargetMuscle {
    String backgroundImage;
    String muscleName;
    float progress;

    public TargetMuscle(String str, String str2, float f) {
        this.backgroundImage = str;
        this.muscleName = str2;
        this.progress = f;
    }

    public String getBackgroundImage() {
        return this.backgroundImage;
    }

    public void setBackgroundImage(String str) {
        this.backgroundImage = str;
    }

    public String getMuscleName() {
        return this.muscleName;
    }

    public void setMuscleName(String str) {
        this.muscleName = str;
    }

    public float getProgress() {
        return this.progress;
    }

    public void setProgress(float f) {
        this.progress = f;
    }

    public String getBackgroundMirroredImage() {
        String replace = this.muscleName.replace(" ", "").toLowerCase().trim().replace("-", "");
        return AppConstants.path + replace + "mirrored.webp";
    }
}

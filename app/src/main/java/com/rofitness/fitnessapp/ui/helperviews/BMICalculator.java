package com.rofitness.fitnessapp.ui.helperviews;

public class BMICalculator {
    double heightInCM = 122.0d;
    double weightInKg = 27.210884353741495d;
    double weightInPounds;

    public void setWeightInPounds(double d) {
        this.weightInPounds = d;
        this.weightInKg = d / 2.205d;
    }

    public void setHeightInCM(double d) {
        this.heightInCM = d;
    }

    public double getBMIValue() {
        double d = this.heightInCM;
        if (d <= 0.0d) {
            return 0.0d;
        }
        double d2 = this.weightInKg;
        if (d2 <= 0.0d) {
            return 0.0d;
        }
        double d3 = d / 100.0d;
        double round = (double) Math.round((d2 / (d3 * d3)) * 100.0d);
        return round / 100.0d;
    }
}

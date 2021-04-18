package com.rofitness.fitnessapp.Utils;

public class BmiUtility {
    public static double calculationBMI(double d, double d2) {
        double d3 = d2 / 100.0d;
        return d / (d3 * d3);
    }

    public static double poundToKg(double d) {
        return d * 0.454d;
    }

    public static double convertFeetandInchesToCentimeter(String str, String str2) {
        double d;
        double d2 = 0.0d;
        if (str != null) {
            try {
                if (str.trim().length() != 0) {
                    d = Double.parseDouble(str);
                    if (str2 != null) {
                        try {
                            if (str2.trim().length() != 0) {
                                d2 = Double.parseDouble(str2);
                            }
                        } catch (NumberFormatException unused) {
                        }
                    }
                    return (d * 30.48d) + (d2 * 2.54d);
                }
            } catch (NumberFormatException unused2) {
                d = 0.0d;
            }
        }
        d = 0.0d;
        if (str2 != null) {
        }
        return (d * 30.48d) + (d2 * 2.54d);
    }

    public static String displayBMI(double d) {
        if (Double.compare(d, 15.0d) <= 0) {
            return "Very Severely Underweight";
        }
        if (Double.compare(d, 15.0d) > 0 && Double.compare(d, 16.0d) <= 0) {
            return "Severely Underweight";
        }
        if (Double.compare(d, 16.0d) > 0 && Double.compare(d, 18.5d) <= 0) {
            return "Underweight";
        }
        if (Double.compare(d, 18.5d) > 0 && Double.compare(d, 25.0d) <= 0) {
            return "Healthy Weight";
        }
        if (Double.compare(d, 25.0d) > 0 && Double.compare(d, 30.0d) <= 0) {
            return "Overweight";
        }
        if (Double.compare(d, 30.0d) <= 0 || Double.compare(d, 35.0d) > 0) {
            return (Double.compare(d, 35.0d) <= 0 || Double.compare(d, 40.0d) > 0) ? "Very Severely Obese" : "Severely Obese";
        }
        return "Moderately Obese";
    }
}

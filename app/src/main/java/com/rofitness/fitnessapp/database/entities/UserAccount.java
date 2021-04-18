package com.rofitness.fitnessapp.database.entities;

public class UserAccount {
    private int age;
    private double height;
    private String heightUnit = "ft+inch";
    private int id;
    private String image;
    private String name;
    private double waist;
    private String waistUnit = "in";
    private double weight;
    private String weightUnit = "kg";

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getImage() {
        return this.image;
    }

    public void setImage(String str) {
        this.image = str;
    }

    public String getHeightUnit() {
        return this.heightUnit;
    }

    public void setHeightUnit(String str) {
        this.heightUnit = str;
    }

    public String getWeightUnit() {
        return this.weightUnit;
    }

    public void setWeightUnit(String str) {
        this.weightUnit = str;
    }

    public String getWaistUnit() {
        return this.waistUnit;
    }

    public void setWaistUnit(String str) {
        this.waistUnit = str;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int i) {
        this.age = i;
    }

    public double getHeight() {
        return this.height;
    }

    public void setHeight(double d) {
        this.height = d;
    }

    public double getWeight() {
        return this.weight;
    }

    public void setWeight(double d) {
        this.weight = d;
    }

    public double getWaist() {
        return this.waist;
    }

    public void setWaist(double d) {
        this.waist = d;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int i) {
        this.id = i;
    }
}

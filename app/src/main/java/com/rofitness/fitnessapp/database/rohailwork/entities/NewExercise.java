package com.rofitness.fitnessapp.database.rohailwork.entities;

public class NewExercise {
    protected static final String basePath = "asset:///images/Exercises_images/";
    String detailLink;
    String difficulty;
    String equipment;
    String image1Link;
    String image2Link;
    String mainBodyGroup;
    String mainMuscleGroup;
    String mechanics;
    String otherMuscleGroups;
    String tags;
    String type;
    private Long id;
    private String repetitions;
    private String sets;
    private String videoLink;

    public Long getId() {
        return this.id;
    }

    public void setId(Long l) {
        this.id = l;
    }

    public String getMainBodyGroup() {
        return this.mainBodyGroup;
    }

    public void setMainBodyGroup(String str) {
        this.mainBodyGroup = str;
    }

    public String getTags() {
        return this.tags;
    }

    public void setTags(String str) {
        this.tags = str;
    }

    public String getDetailLink() {
        return this.detailLink;
    }

    public void setDetailLink(String str) {
        this.detailLink = str;
    }

    public String getMainMuscleGroup() {
        return this.mainMuscleGroup;
    }

    public void setMainMuscleGroup(String str) {
        this.mainMuscleGroup = str;
    }

    public String getOtherMuscleGroups() {
        return this.otherMuscleGroups;
    }

    public void setOtherMuscleGroups(String str) {
        this.otherMuscleGroups = str;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String str) {
        this.type = str;
    }

    public String getEquipment() {
        return this.equipment;
    }

    public void setEquipment(String str) {
        this.equipment = str;
    }

    public String getMechanics() {
        return this.mechanics;
    }

    public void setMechanics(String str) {
        this.mechanics = str;
    }

    public String getDifficulty() {
        return this.difficulty;
    }

    public void setDifficulty(String str) {
        this.difficulty = str;
    }

    public String getImage1Link() {
        return this.image1Link;
    }

    public void setImage1Link(String str) {
        this.image1Link = str;
    }

    public String getImage2Link() {
        return this.image2Link;
    }

    public void setImage2Link(String str) {
        this.image2Link = str;
    }

    public String getSets() {
        return this.sets;
    }

    public void setSets(String str) {
        this.sets = str;
    }

    public String getRepetitions() {
        return this.repetitions;
    }

    public void setRepetitions(String str) {
        this.repetitions = str;
    }

    public String getVideoLink() {
        String str = this.videoLink;
        return str.contains("?t=") ? str.substring(0, str.indexOf("?t=")) : str;
    }

    public void setVideoLink(String str) {
        this.videoLink = str;
    }
}

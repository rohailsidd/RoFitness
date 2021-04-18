package com.rofitness.fitnessapp.database.rohailwork.entities;

public class Language {
    private Boolean active = false;
    private Long id;
    private String languageCode;
    private String languageName;

    public Long getId() {
        return this.id;
    }

    public void setId(Long l) {
        this.id = l;
    }

    public String getLanguageCode() {
        return this.languageCode;
    }

    public void setLanguageCode(String str) {
        this.languageCode = str;
    }

    public String getLanguageName() {
        return this.languageName;
    }

    public void setLanguageName(String str) {
        this.languageName = str;
    }

    public Boolean getActive() {
        return this.active;
    }

    public void setActive(Boolean bool) {
        this.active = bool;
    }

    public String toString() {
        return this.languageName;
    }
}

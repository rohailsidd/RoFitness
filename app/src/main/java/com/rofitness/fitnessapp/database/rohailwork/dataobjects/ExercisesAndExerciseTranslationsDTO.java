package com.rofitness.fitnessapp.database.rohailwork.dataobjects;

import com.rofitness.fitnessapp.database.entities.PlanWrapper;
import com.rofitness.fitnessapp.database.rohailwork.entities.ExerciseTranslation;
import com.rofitness.fitnessapp.database.rohailwork.entities.Language;
import com.rofitness.fitnessapp.database.rohailwork.entities.NewExercise;

import java.util.List;

public class ExercisesAndExerciseTranslationsDTO {
    private List<NewExercise> exerciseList;
    private List<ExerciseTranslation> exerciseTranslationList;
    private List<Language> languageList;
    private List<PlanWrapper> plans;

    public List<NewExercise> getExerciseList() {
        return this.exerciseList;
    }

    //ToDo: setExerciseList
    //@BindingAdapter({"bind:newexerciselist"})
    public void setExerciseList(List<NewExercise> list) {
        this.exerciseList = list;
    }

    public List<PlanWrapper> getPlans() {
        return this.plans;
    }

    public void setPlans(List<PlanWrapper> list) {
        this.plans = list;
    }

    public List<ExerciseTranslation> getExerciseTranslationList() {
        return this.exerciseTranslationList;
    }

    public void setExerciseTranslationList(List<ExerciseTranslation> list) {
        this.exerciseTranslationList = list;
    }

    public List<Language> getLanguageList() {
        return this.languageList;
    }

    public void setLanguageList(List<Language> list) {
        this.languageList = list;
    }
}

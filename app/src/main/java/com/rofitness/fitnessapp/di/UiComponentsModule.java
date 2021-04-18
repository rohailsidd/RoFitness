package com.rofitness.fitnessapp.di;

import com.rofitness.fitnessapp.ui.activities.AboutActivity;
import com.rofitness.fitnessapp.ui.activities.BmiCalculatorActivity;

import com.rofitness.fitnessapp.ui.activities.ExerciseActivity;
import com.rofitness.fitnessapp.ui.activities.HomePageActivity;
import com.rofitness.fitnessapp.ui.activities.SettingsActivity;
import com.rofitness.fitnessapp.ui.activities.SplashScreenActivity;
import com.rofitness.fitnessapp.ui.activities.StartWorkoutActivity;
import com.rofitness.fitnessapp.ui.activities.SubWorkoutsActivity;
import com.rofitness.fitnessapp.ui.activities.TargetMuscleActivity;

import dagger.Module;

@Module
public abstract class UiComponentsModule {
    public abstract AboutActivity contributeAboutActivity();

    public abstract BmiCalculatorActivity contributeBmiCalculatorActivity();

    public abstract ExerciseActivity contributeExerciseActivity();

    public abstract HomePageActivity contributeHomePageActivity();

    public abstract SettingsActivity contributeSettingsActivity();

    public abstract SplashScreenActivity contributeSplashScreenActivity();

    public abstract StartWorkoutActivity contributeStartWorkoutActivity();

    public abstract SubWorkoutsActivity contributeSubWorkoutsActivity();

    public abstract TargetMuscleActivity contributeTargetMuscleActivity();
}
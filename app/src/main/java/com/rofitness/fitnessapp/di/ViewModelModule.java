package com.rofitness.fitnessapp.di;

import androidx.lifecycle.ViewModel;

import com.rofitness.fitnessapp.ui.viewmodels.DayViewModel;
import com.rofitness.fitnessapp.ui.viewmodels.ExerciseActivityViewModel;
import com.rofitness.fitnessapp.ui.viewmodels.PlanViewModel;
import com.rofitness.fitnessapp.ui.viewmodels.SettingsViewModel;
import com.rofitness.fitnessapp.ui.viewmodels.StartWorkoutActivityViewModel;
import com.rofitness.fitnessapp.ui.viewmodels.SubWorkoutsActivityViewModel;
import com.rofitness.fitnessapp.ui.viewmodels.WorkoutsViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
abstract class ViewModelModule {
    ViewModelModule() {
    }

    @ViewModelKey(PlanViewModel.class)
    @Binds
    @IntoMap
    abstract ViewModel bindDayPlanViewModel(PlanViewModel planViewModel);

    @ViewModelKey(DayViewModel.class)
    @Binds
    @IntoMap
    abstract ViewModel bindDayViewModel(DayViewModel dayViewModel);

    @ViewModelKey(ExerciseActivityViewModel.class)
    @Binds
    @IntoMap
    abstract ViewModel bindExerciseActivityViewModel(ExerciseActivityViewModel exerciseActivityViewModel);

    @ViewModelKey(SettingsViewModel.class)
    @Binds
    @IntoMap
    abstract ViewModel bindSettingsViewModel(SettingsViewModel settingsViewModel);

    @ViewModelKey(StartWorkoutActivityViewModel.class)
    @Binds
    @IntoMap
    abstract ViewModel bindStartWorkoutActivitViewModel(StartWorkoutActivityViewModel startWorkoutActivityViewModel);

    @ViewModelKey(SubWorkoutsActivityViewModel.class)
    @Binds
    @IntoMap
    abstract ViewModel bindSubWorkoutsActivityViewModel(SubWorkoutsActivityViewModel subWorkoutsActivityViewModel);

    @ViewModelKey(WorkoutsViewModel.class)
    @Binds
    @IntoMap
    abstract ViewModel bindWorkoutsViewModel(WorkoutsViewModel workoutsViewModel);
}

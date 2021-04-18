package com.rofitness.fitnessapp.ui.viewmodels;

import androidx.lifecycle.ViewModel;

import com.rofitness.fitnessapp.database.rohailwork.entities.Language;
import com.rofitness.fitnessapp.repository.FitnessRepository;

import java.util.List;

import javax.inject.Inject;

public class SettingsViewModel extends ViewModel {
    @Inject
    FitnessRepository fitnessRepository;

    public List<String> getLanguages() {
        return this.fitnessRepository.getLanguages();
    }

    public Language getLanguage(String str) {
        return this.fitnessRepository.getLanguage(str);
    }
}

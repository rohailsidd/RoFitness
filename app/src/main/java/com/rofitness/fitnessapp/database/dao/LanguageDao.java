package com.rofitness.fitnessapp.database.dao;

import com.rofitness.fitnessapp.database.rohailwork.entities.Language;

import java.util.List;

public interface LanguageDao extends BaseDao<Language> {
    Language getLanguage(String str);

    Language getLanguageByCode(String str);

    List<String> getLanguages();
}

package com.rofitness.fitnessapp.database.dao;

import com.rofitness.fitnessapp.database.dataobjects.Category;
import com.rofitness.fitnessapp.database.dataobjects.SubCategory;
import com.rofitness.fitnessapp.database.entities.Exercise;
import com.rofitness.fitnessapp.database.rohailwork.entities.NewExercise;

import java.util.List;

public interface ExerciseDao extends BaseDao<NewExercise> {
    List<Exercise> findByIds(long j, int i, int i2, Long l);

    List<Exercise> findByIds(List<Long> list, Long l);

    List<Category> getCategories();

    Integer getExerciseCountInsideCategory(String str);

    Integer getExerciseCountInsideSubCategory(String str);

    List<Exercise> getExercisesByCategoryName(String str, Long l);

    Exercise getSingleExercise(long j, Long l);

    List<SubCategory> getSubCategories(String str);
}

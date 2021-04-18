package com.rofitness.fitnessapp.repository;

import androidx.lifecycle.LiveData;

import com.rofitness.fitnessapp.AppPreferences;
import com.rofitness.fitnessapp.FitnessApp;
import com.rofitness.fitnessapp.Utils.ExerciseComparator;
import com.rofitness.fitnessapp.database.dao.CategoryTypeProgressDao;
import com.rofitness.fitnessapp.database.dao.ExerciseDao;
import com.rofitness.fitnessapp.database.dao.ExerciseProgressDao;
import com.rofitness.fitnessapp.database.dao.LanguageDao;
import com.rofitness.fitnessapp.database.dao.PlanDao;
import com.rofitness.fitnessapp.database.dao.UserAccountDao;
import com.rofitness.fitnessapp.database.dataobjects.Category;
import com.rofitness.fitnessapp.database.dataobjects.SubCategory;
import com.rofitness.fitnessapp.database.entities.CategoryTypeProgress;
import com.rofitness.fitnessapp.database.entities.Exercise;
import com.rofitness.fitnessapp.database.entities.ExerciseProgress;
import com.rofitness.fitnessapp.database.entities.PlanWrapper;
import com.rofitness.fitnessapp.database.entities.UserAccount;
import com.rofitness.fitnessapp.database.rohailwork.entities.Language;
import com.rofitness.fitnessapp.dto.UnCompletedDay;
import com.rofitness.fitnessapp.helpers.AppConstants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import javax.inject.Singleton;
import javax.inject.Inject;

@SuppressWarnings("ALL")
@Singleton
public class FitnessRepository implements Comparator<Category> {
    private static List<String> CATEGORY_ORDERED_ENTRIES/* = Arrays.asList("Arms", "Abs", "Back", "Wings", "Chest", "Legs", "Shoulders", "Cardio")*/;

    static {
        CATEGORY_ORDERED_ENTRIES = Arrays.asList("Arms", "Abs", "Back", "Wings", "Chest", "Legs", "Shoulders", "Cardio");
    }

    @Inject
    AppPreferences appPreferences;
    @Inject
    ExerciseDao exerciseDao;
    @Inject
    ExerciseProgressDao exerciseProgressDao;
    @Inject
    LanguageDao languageDao;
    @Inject
    CategoryTypeProgressDao mCategoryTypeProgressDao;
    @Inject
    PlanDao planDao;
    @Inject
    UserAccountDao userAccountDao;
    private Map<String, List<Integer>> orderRuleMap/* = ExerciseComparator.getOrderRuleMap()*/;

    @Inject
    public FitnessRepository() {
        this.orderRuleMap = ExerciseComparator.getOrderRuleMap();
    }

    public List<Category> getExerciseCategories() {
        ArrayList arrayList = new ArrayList();
        for (String str : FitnessApp.getInstance().generalWorkoutPlans.keySet()) {
            arrayList.add(new Category(str));
        }
        Collections.sort(arrayList, this);
        return arrayList;
    }

    public List<SubCategory> getSubCategories(String str) {
        ArrayList arrayList = new ArrayList();
        for (String str2 : FitnessApp.getInstance().generalWorkoutPlans.get(str)) {
            arrayList.add(new SubCategory(str2));
        }
        return arrayList;
    }

    public Integer getExerciseCountInsideCategory(String str) {
        return this.exerciseDao.getExerciseCountInsideCategory(str);
    }

    public Integer getExerciseCountInsideSubCategory(String str) {
        return this.exerciseDao.getExerciseCountInsideSubCategory(str);
    }

    public List<Exercise> getExercisesByCategoryName(String str) {
        return this.exerciseDao.getExercisesByCategoryName(str, Long.valueOf(getLanguageId()));
    }

    public Exercise getSingleExercise(long j) {
        return this.exerciseDao.getSingleExercise(j, Long.valueOf(getLanguageId()));
    }

    public List<Long> insertExerciseProgressList(List<ExerciseProgress> list) {
        return this.exerciseProgressDao.insert((List) list);
    }

    public List<Exercise> getExercies(List<Long> list) {
        return this.exerciseDao.findByIds(list, Long.valueOf(getLanguageId()));
    }

    public List<Exercise> getExercies(long j, int i, int i2) {
        return this.exerciseDao.findByIds(j, i, i2, Long.valueOf(getLanguageId()));
    }

    public void setExerciseCompleted(int i, int i2, int i3, long j) {
        this.exerciseProgressDao.setExerciseCompleted(i, i2, i3, j, true);
    }

    public void resetPlanProgress(int i) {
        this.exerciseProgressDao.resetPlanProgress(i, false);
    }

    public Integer getPlanCompletedPercentage(int i) {
        return this.exerciseProgressDao.getProgressInPercentageForCompletePlan(i);
    }

    public LiveData<List<ExerciseProgress>> getAllExercieses() {
        return this.exerciseProgressDao.getAllExercieses();
    }

    public LiveData<List<ExerciseProgress>> getAllExerciesesForPlan(int i) {
        return this.exerciseProgressDao.getAllExerciesesProgressForPlan(i);
    }

    public Integer getProgressInPercentageForDay(int i, int i2, int i3) {
        return this.exerciseProgressDao.getProgressInPercentageForDay(i, i2, i3);
    }

    public Integer getProgressInPercentageForWeek(int i, int i2) {
        return this.exerciseProgressDao.getProgressInPercentageForWeek(i, i2);
    }

    public int getDaysLeft(int i) {
        return this.exerciseProgressDao.daysLeft(i).intValue();
    }

    public boolean isExerciseCompleted(int i, int i2, int i3, long j) {
        return this.exerciseProgressDao.isExerciseCompleted(i, i2, i3, j);
    }

    public LiveData<List<ExerciseProgress>> observeDayExercises(int i, int i2, int i3) {
        return this.exerciseProgressDao.observeDayExercises(i, i2, i3);
    }

    public Integer getAllPlanCount(int i) {
        return this.exerciseProgressDao.getAllPlanCount(i);
    }

    public Integer getPlanDayExercisesCount(int i, int i2, int i3) {
        return this.exerciseProgressDao.getPlanDayExercisesCount(i, i2, i3);
    }

    public LiveData<UserAccount> getUserAccount() {
        return this.userAccountDao.getUserAccount();
    }

    public void insertUserAccount(UserAccount userAccount) {
        this.userAccountDao.insert(/*(UserAccountDao)*/ userAccount);
    }

    public void updateUserAccount(UserAccount userAccount) {
        this.userAccountDao.update(userAccount);
    }

    public UnCompletedDay getLatestUnCompletedDay(int i) {
        return this.exerciseProgressDao.getLatestUnCompletedDay(i);
    }

    public LiveData<Integer> getLatestWeekComplete() {
        return this.exerciseProgressDao.getLatestWeekCompleteTest();
    }

    public LiveData<List<PlanWrapper>> getAllPlans() {
        return this.planDao.getAllPlans();
    }

    public List<String> getLanguages() {
        return this.languageDao.getLanguages();
    }

    public Language getLanguage(String str) {
        return this.languageDao.getLanguage(str);
    }

    public long getLanguageId() {
        return this.appPreferences.getLong(AppPreferences.Key.languageId, AppConstants.EN_LANG_ID.longValue());
    }

    public Language getLanguageByCode(String str) {
        return this.languageDao.getLanguageByCode(str.trim().toLowerCase());
    }

    public void resetCategoryTypeProgress(String str) {
        for (String str2 : FitnessApp.getInstance().generalWorkoutPlans.get(str)) {
            this.mCategoryTypeProgressDao.resetCategoryTypeProgress(str2, false);
        }
    }

    public void resetMainMuscleProgress(String str) {
        this.mCategoryTypeProgressDao.resetCategoryTypeProgress(str, false);
    }

    public float getMainBodyGroupProgress(String str) {
        int i = 0;
        int i2 = 0;
        for (String str2 : FitnessApp.getInstance().generalWorkoutPlans.get(str)) {
            i2 += this.mCategoryTypeProgressDao.getAllCountForMainMuscleGroup(str2).intValue();
            i += this.mCategoryTypeProgressDao.getCompletedCountForMainMuscleGroup(str2).intValue();
        }
        double d = (double) i;
        double d2 = (double) i2;
        Double.isNaN(d);
        Double.isNaN(d2);
        return (float) ((int) Math.round((d / d2) * 100.0d));
    }

    public float getMainMuscleGroupProgress(String str) {
        return (float) this.mCategoryTypeProgressDao.getMainMuscleGroupProgress(str);
    }

    public boolean isExerciseCompletedFromCategory(Long l, String str) {
        return this.mCategoryTypeProgressDao.getExerciseFlag(l.longValue(), str);
    }

    public LiveData<List<CategoryTypeProgress>> getAllCategoryTypeProgressForMainMuscleGroup(String str) {
        return this.mCategoryTypeProgressDao.getAllCategoryTypeProgressForMainMuscleGroup(str);
    }

    public void setExerciseCompletedFromCategory(long j, String str) {
        this.mCategoryTypeProgressDao.setCompleted(j, str, true);
    }

    public List<Exercise> getExercisesByTag(String str) {
        List<Exercise> exercisesByTag = this.mCategoryTypeProgressDao.getExercisesByTag(str, Long.valueOf(getLanguageId()));
        if (this.orderRuleMap.containsKey(str)) {
            Collections.sort(exercisesByTag, new ExerciseComparator(this.orderRuleMap.get(str)));
        }
        return exercisesByTag;
    }

    public int compare(Category category, Category category2) {
        category.getMainBodyGroup();
        category2.getMainBodyGroup();
        if (CATEGORY_ORDERED_ENTRIES.contains(category.getMainBodyGroup()) && CATEGORY_ORDERED_ENTRIES.contains(category2.getMainBodyGroup())) {
            return CATEGORY_ORDERED_ENTRIES.indexOf(category.getMainBodyGroup()) - CATEGORY_ORDERED_ENTRIES.indexOf(category2.getMainBodyGroup());
        }
        if (CATEGORY_ORDERED_ENTRIES.contains(category.getMainBodyGroup())) {
            return -1;
        }
        if (CATEGORY_ORDERED_ENTRIES.contains(category2.getMainBodyGroup())) {
            return 1;
        }
        return category.toString().compareTo(category2.toString());
    }
}

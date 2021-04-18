package com.rofitness.fitnessapp;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Build;
import android.os.LocaleList;
import android.util.Log;

import androidx.multidex.MultiDex;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.stetho.Stetho;
import com.rofitness.fitnessapp.Utils.AppConstants;
import com.rofitness.fitnessapp.database.dao.CategoryTypeProgressDao;
import com.rofitness.fitnessapp.database.dao.ExerciseDao;
import com.rofitness.fitnessapp.database.dao.ExerciseTranslationDao;
import com.rofitness.fitnessapp.database.dao.LanguageDao;
import com.rofitness.fitnessapp.database.dao.PlanDao;
import com.rofitness.fitnessapp.database.entities.Plan;
import com.rofitness.fitnessapp.di.AppComponent;
import com.rofitness.fitnessapp.di.DaggerAppComponent;
import com.rofitness.fitnessapp.repository.FitnessRepository;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.inject.Inject;

public class FitnessApp extends DaggerApplication {

    private static final String TAG = "FitnessApp";
    private static FitnessApp mInstance;
    @Inject
    public AppPreferences appPreferences;
    @Inject
    public CategoryTypeProgressDao categoryTypeProgressDao;
    @Inject
    public ExerciseDao exerciseDao;
    @Inject
    public ExerciseTranslationDao exerciseTranslationDao;
    @Inject
    public FitnessRepository fitnessRepository;
    public Map<String, List<String>> generalWorkoutPlans;
    @Inject
    public LanguageDao languageDao;
    @Inject
    public PlanDao planDao;
    private Map<Integer, String> exerciseParentMap;
    private Map<Integer, Plan> planMap;
    public FitnessApp() {
        this.generalWorkoutPlans = new HashMap() {
            {
                this.put("Arms", Arrays.asList(new String[]{"Biceps", "Triceps", "Forearm"}));
                this.put("Abs", Arrays.asList(new String[]{"Upper Abs", "Lower Abs", "Side Cutting"}));
                this.put("Back", Arrays.asList(new String[]{"Upper Back", "Lower Back", "V-Shape Back"}));
                this.put("Wings", Arrays.asList(new String[]{"V-Shape", "Wings Extension"}));
                this.put("Chest", Arrays.asList(new String[]{"Upper Chest", "Middle Chest", "Lower Chest"}));
                this.put("Legs", Arrays.asList(new String[]{"Front Thighs", "Back Thighs", "Calfs", "Hips"}));
                this.put("Shoulders", Arrays.asList(new String[]{"Front Shoulder", "Back Shoulder", "Traps"}));
                this.put("Cardio", Arrays.asList(new String[]{"Aerobic", "Stretching", "Yoga"}));
            }
        };
        this.planMap = new HashMap();
    }

    public static FitnessApp getInstance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        loadLocale();
        mInstance = this;
        Fresco.initialize(this);
        Stetho.initializeWithDefaults(this);
        this.exerciseParentMap = new HashMap();
        saveCategoryTag();
    }


    public List<Plan> getPlanList() {
        ArrayList arrayList = new ArrayList(this.planMap.values());
        return arrayList;
    }


    private void saveCategoryTag() {
        this.appPreferences.put(AppConstants.CategoryTag.sChest, "Killer");
        this.appPreferences.put(AppConstants.CategoryTag.sTriceps, "Effective");
        this.appPreferences.put(AppConstants.CategoryTag.sBiceps, "Killer");
        this.appPreferences.put(AppConstants.CategoryTag.sBack, "Hardcore");
        this.appPreferences.put(AppConstants.CategoryTag.sShoulders, "Pumping");
        this.appPreferences.put(AppConstants.CategoryTag.sUpper_legs, "Core");
        this.appPreferences.put(AppConstants.CategoryTag.sLower_legs, "Core");
        this.appPreferences.put(AppConstants.CategoryTag.sAbs, "Burning");
    }

    public Map<Integer, String> getExerciseParentMap() {
        return this.exerciseParentMap;
    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        AppComponent build = DaggerAppComponent.builder().application(this).build();
        build.inject(this);
        return build;
    }

    @Inject
    public String loadLocale() {
        String string = this.appPreferences.getString(AppPreferences.Key.language, Locale.getDefault().getLanguage());
        String languageName = getLanguageName(string);
        Log.e("Getting Language: ", string);
        setNewLocale(this, string, false);
        return languageName;
    }

    public String getLanguageName(String str) {
        if (str.equals("ar")) {
            return "العربية";
        }
        if (str.equals("ur")) {
            return "اردو";
        }
        if (str.equals("fa")) {
            return "فارسی";
        }
        if (str.equals("tr")) {
            return "Türkçe";
        }
        if (str.equals("fr")) {
            return "français";
        }
        if (str.equals("hi")) {
            return "हिंदी";
        }
        if (str.equals("zh")) {
            return "中文";
        }
        if (str.equals("ru")) {
            return "русский";
        }
        if (str.equals("bg")) {
            return "български";
        }
        if (str.equals("es")) {
            return "español";
        }
        return str.equals("pt") ? "português" : "English";
    }


    private Context updateResources(Context context, String str, boolean z) {
        Context context2;
        Locale locale = new Locale(str);
        Locale.setDefault(locale);
        Configuration configuration = new Configuration(context.getResources().getConfiguration());
        configuration.setLocale(locale);
        if (Build.VERSION.SDK_INT >= 24) {
            LocaleList localeList = new LocaleList(locale);
            LocaleList.setDefault(localeList);
            configuration.setLocales(localeList);
            context2 = context.createConfigurationContext(configuration);
        } else {
            context2 = context.createConfigurationContext(configuration);
        }
        if (z) {
            this.appPreferences.put(AppPreferences.Key.language, str);
        }
        return context2;
    }

    public Context setNewLocale(Context context, String str, boolean z) {
        return updateResources(context, str, z);
    }

    @Override
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(setNewLocale(context, new AppPreferences(context).getString(AppPreferences.Key.language, Locale.getDefault().getLanguage()), false));
    }

    @Override
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        MultiDex.install(this);
        setNewLocale(this, this.appPreferences.getString(AppPreferences.Key.language, Locale.getDefault().getLanguage()), false);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        Log.d(TAG, "onTerminate: ");
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        Log.d(TAG, "onLowMemory: ");
    }
}

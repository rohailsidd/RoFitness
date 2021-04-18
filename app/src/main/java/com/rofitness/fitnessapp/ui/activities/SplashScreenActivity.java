package com.rofitness.fitnessapp.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.blankj.utilcode.util.ResourceUtils;
import com.rofitness.fitnessapp.AppPreferences;
import com.rofitness.fitnessapp.FitnessApp;
import com.rofitness.fitnessapp.Utils.AppConstants;
import com.rofitness.fitnessapp.database.entities.CategoryTypeProgress;
import com.rofitness.fitnessapp.database.rohailwork.dataobjects.ExercisesAndExerciseTranslationsDTO;
import com.rofitness.fitnessapp.database.rohailwork.entities.Language;
import com.rofitness.fitnessapp.database.rohailwork.entities.NewExercise;
import com.rofitness.fitnessapp.R;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@SuppressWarnings("ALL")
public class SplashScreenActivity extends AppCompatActivity {
    /*private final String TAG = SplashScreenActivity.class.getSimpleName();
    Animation fromBottom;
    Animation fromTop;
    ImageView imageViewAppLogo;
    private Moshi moshi = new Moshi.Builder().build();
    public int splashTime = 1900;
    TextView textViewMensFitness;*/
    private final String TAG;
    public int splashTime;
    Animation fromBottom;
    Animation fromTop;
    ImageView imageViewAppLogo;
    TextView textViewMensFitness;
    private Moshi moshi;

    public SplashScreenActivity() {
        this.TAG = SplashScreenActivity.class.getSimpleName();
        this.splashTime = 1900;
        this.moshi = new Moshi.Builder().build();
    }

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requestWindowFeature(1);
        getWindow().setFlags(1024, 1024);
        setContentView(R.layout.activity_splash_screen);
        this.imageViewAppLogo = (ImageView) findViewById(R.id.imageView_splashScreen_appLogo);
        this.textViewMensFitness = (TextView) findViewById(R.id.textView_splashScreen_mensFitness);
        Animation loadAnimation = AnimationUtils.loadAnimation(this, R.anim.fromtop);
        this.fromTop = loadAnimation;
        this.imageViewAppLogo.setAnimation(loadAnimation);
        Animation loadAnimation2 = AnimationUtils.loadAnimation(this, R.anim.frombottom);
        this.fromBottom = loadAnimation2;
        this.textViewMensFitness.setAnimation(loadAnimation2);
        if (FitnessApp.getInstance().appPreferences.getBoolean(AppPreferences.Key.isRunFirstTime, true)) {
            insertDataToDb();
            insertDefLang();
            return;
        }
        goToNextActivity(this.splashTime);
    }

    private void insertDefLang() {
        String string = FitnessApp.getInstance().appPreferences.getString(AppPreferences.Key.language, Locale.getDefault().getLanguage());
        Language languageByCode = FitnessApp.getInstance().fitnessRepository.getLanguageByCode(string);
        if (languageByCode == null) {
            FitnessApp.getInstance().appPreferences.put(AppPreferences.Key.languageId, 17L);
            FitnessApp.getInstance().appPreferences.put(AppPreferences.Key.language, AppConstants.Settings.defaultLanguage);
            return;
        }
        FitnessApp.getInstance().appPreferences.put(AppPreferences.Key.languageId, languageByCode.getId().longValue());
        FitnessApp.getInstance().appPreferences.put(AppPreferences.Key.language, string);
    }

    private void insertDataToDb() {
        String string = ResourceUtils.readAssets2String("data.json");
        try {
            ExercisesAndExerciseTranslationsDTO exercisesAndTranslations = getExercisesAndTranslations(string/*ResourceUtils.readAssets2String("data.json")*/);
            FitnessApp.getInstance().exerciseTranslationDao.insert((List) exercisesAndTranslations.getExerciseTranslationList());
            FitnessApp.getInstance().exerciseDao.insert((List) exercisesAndTranslations.getExerciseList());
            initTagsAndProgress(exercisesAndTranslations.getExerciseList());
            FitnessApp.getInstance().languageDao.insert((List) exercisesAndTranslations.getLanguageList());
            FitnessApp.getInstance().planDao.insert((List) exercisesAndTranslations.getPlans());

            FitnessApp.getInstance().appPreferences.put(AppPreferences.Key.isRunFirstTime, false);
            goToNextActivity(this.splashTime);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initTagsAndProgress(List<NewExercise> list) {
        ArrayList arrayList = new ArrayList();
        for (NewExercise newExercise : list) {
            for (String str : newExercise.getTags().split(",")) {
                arrayList.add(new CategoryTypeProgress(str.trim(), newExercise.getId().longValue(), false));
            }
        }
        FitnessApp.getInstance().categoryTypeProgressDao.insert((List) arrayList);
    }

    private void goToNextActivity(int i) {
        new Handler().postDelayed(new Runnable() {


            @Override
            public void run() {
                Intent intent = new Intent(SplashScreenActivity.this, HomePageActivity.class);
                SplashScreenActivity.this.overridePendingTransition(R.anim.showsplash, R.anim.slideupward);
                SplashScreenActivity.this.startActivity(intent);
                SplashScreenActivity.this.finish();
            }
        }, (long) i);
    }

    private ExercisesAndExerciseTranslationsDTO getExercisesAndTranslations(String str) throws IOException {
        return (ExercisesAndExerciseTranslationsDTO) this.moshi.adapter(Types.newParameterizedType(ExercisesAndExerciseTranslationsDTO.class, new Type[0])).fromJson(str);
    }
}

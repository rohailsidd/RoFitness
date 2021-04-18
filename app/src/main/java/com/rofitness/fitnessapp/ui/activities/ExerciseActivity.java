package com.rofitness.fitnessapp.ui.activities;

import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProviders;

import com.facebook.drawee.view.SimpleDraweeView;
import com.rofitness.fitnessapp.FitnessApp;
import com.rofitness.fitnessapp.Utils.AppConstants;
import com.rofitness.fitnessapp.database.entities.Exercise;
import com.rofitness.fitnessapp.database.entities.Plan;
import com.rofitness.fitnessapp.imagehelper.ImageLoadingAdapter;
import com.rofitness.fitnessapp.ui.viewmodels.ExerciseActivityViewModel;
import com.rofitness.fitnessapp.ui.viewmodels.ViewModelFactory;
import com.rofitness.fitnessapp.R;

import javax.inject.Inject;

@SuppressWarnings("ALL")
public class ExerciseActivity extends BaseActivity {
    @Inject
    ViewModelFactory viewModelFactory;
    private String categoryName;
    private Exercise exercise;
    private long exerciseId;
    private Plan plan;
    private String tag = "";
    private ExerciseActivityViewModel viewModel;
    private Exercise mExercise;
    private ExerciseActivity mExerciseActivity;
    private String mParentWorkout;
    private Button buttonExerciseDone;
    private TextView textViewExerciseTitle;
    private TextView textView_exercise_workoutValue;
    private TextView textView_exercise_repititionValue;
    private TextView textView_exercise_exerciseValue;
    private TextView textView_exercise_descriptionValue;
    private SimpleDraweeView exercise_img;
    private Toolbar toolbar_exercise_appBar;

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_exercise);
        toolbar_exercise_appBar = findViewById(R.id.toolbar_exercise_appBar);
        buttonExerciseDone = findViewById(R.id.button_exercise_done);
        textViewExerciseTitle = findViewById(R.id.textView_exercise_title);
        textView_exercise_workoutValue = findViewById(R.id.textView_exercise_workoutValue);
        textView_exercise_descriptionValue = findViewById(R.id.textView_exercise_descriptionValue);
        textView_exercise_repititionValue = findViewById(R.id.textView_exercise_repititionValue);
        textView_exercise_exerciseValue = findViewById(R.id.textView_exercise_exerciseValue);
        exercise_img = findViewById(R.id.exercise_img);
        toolbar_exercise_appBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        if (bundle != null) {
            this.exerciseId = bundle.getLong(AppConstants.exerciseIdKey);
            if (bundle.containsKey(AppConstants.tagKey)) {
                this.categoryName = bundle.getString(AppConstants.categoryNameKey);
                this.tag = bundle.getString(AppConstants.tagKey);
            }

        } else {
            Bundle extras = getIntent().getExtras();

            if (extras.containsKey(AppConstants.tagKey)) {
                this.categoryName = extras.getString(AppConstants.categoryNameKey);
                this.tag = extras.getString(AppConstants.tagKey);
            }

            this.exerciseId = getIntent().getExtras().getLong(AppConstants.exerciseIdKey);
        }
        ExerciseActivityViewModel exerciseActivityViewModel = (ExerciseActivityViewModel) ViewModelProviders.of(this, this.viewModelFactory).get(ExerciseActivityViewModel.class);
        this.viewModel = exerciseActivityViewModel;
        Exercise singleExercise = exerciseActivityViewModel.getSingleExercise(this.exerciseId);
        this.exercise = singleExercise;
        if (singleExercise != null) {
            if (this.plan != null) {
            } else {
            }
            setExercise(this.exercise);
            setExerciseActivity(this);
            setParentWorkout(FitnessApp.getInstance().getExerciseParentMap().get(this.exercise.getId()));
            if (!this.viewModel.isExerciseCompleted(this.exerciseId, this.tag)) {
                buttonExerciseDone.setBackground(ContextCompat.getDrawable(this, R.drawable.exercise_undone_background));
            }
            buttonExerciseDone.setBackground(ContextCompat.getDrawable(this, R.drawable.exercise_undone_background));
            buttonExerciseDone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (plan != null) {
                        buttonExerciseDone.setBackground(ContextCompat.getDrawable(ExerciseActivity.this, R.drawable.button_style));
                    } else {
                        buttonExerciseDone.setBackground(ContextCompat.getDrawable(ExerciseActivity.this, R.drawable.button_style));
                        viewModel.setExerciseCompleted(exerciseId, tag);
                    }
                    onBackPressed();
                }
            });
            textViewExerciseTitle.setText(exercise.getExerciseTitle());
            textView_exercise_workoutValue.setText(exercise.getMainMuscleGroup());
            textView_exercise_descriptionValue.setText(Html.fromHtml(exercise.getDetail()));
            textView_exercise_repititionValue.setText(exercise.getRepetitions());
            textView_exercise_exerciseValue.setText(exercise.getExerciseTitle());
            ImageLoadingAdapter.loadImage(exercise_img, exercise.getExerciseImage());
        }
    }

    public void setExercise(Exercise exercise) {
        this.mExercise = exercise;

    }

    public void setParentWorkout(String str) {
        this.mParentWorkout = str;
    }

    public void setExerciseActivity(ExerciseActivity exerciseActivity) {
        this.mExerciseActivity = exerciseActivity;

    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_exercise_done:
                break;

            default:
                return;
        }
        if (this.plan != null) {
            buttonExerciseDone.setBackground(ContextCompat.getDrawable(this, R.drawable.button_style));
        } else {
            buttonExerciseDone.setBackground(ContextCompat.getDrawable(this, R.drawable.button_style));
            this.viewModel.setExerciseCompleted(this.exerciseId, this.tag);
            if (this.viewModel.getMainMuscleProgress(this.tag) == 100.0f) {
            }
            if (this.viewModel.getMainBodyGroupProgress(this.categoryName) == 100.0f) {
            }
        }
        onBackPressed();
    }

    @Override
    protected void onSaveInstanceState(Bundle bundle) {
        bundle.putLong(AppConstants.exerciseIdKey, this.exerciseId);
        if (!this.tag.isEmpty()) {
            bundle.putString(AppConstants.categoryNameKey, this.categoryName);
            bundle.putString(AppConstants.tagKey, this.tag);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

}
package com.rofitness.fitnessapp.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;

import com.rofitness.fitnessapp.Utils.AppConstants;
import com.rofitness.fitnessapp.database.entities.Exercise;
import com.rofitness.fitnessapp.ui.viewmodels.StartWorkoutActivityViewModel;
import com.rofitness.fitnessapp.ui.viewmodels.ViewModelFactory;
import com.rofitness.fitnessapp.R;

import javax.inject.Inject;

public class StartWorkoutActivity extends BaseActivity {
    String categoryName;
    long exerciseId;
    String tag = "";
    @Inject
    ViewModelFactory viewModelFactory;
    ImageButton imageButton;
    private int dayId = 0;
    private StartWorkoutActivityViewModel viewModel;
    private Exercise mExercise;
    private StartWorkoutActivity mStartWorkoutActivity;
    private Toolbar toolbarStartActivityAppBar;
    private TextView textView_startWorkout_workoutTitle;
    private TextView textView_startWorkout_workoutRepetitions;

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_start_workout);
        imageButton = findViewById(R.id.imageButton_startActivity_start);
        toolbarStartActivityAppBar = findViewById(R.id.toolbar_startActivity_appBar);
        textView_startWorkout_workoutTitle = findViewById(R.id.textView_startWorkout_workoutTitle);
        textView_startWorkout_workoutRepetitions = findViewById(R.id.textView_startWorkout_workoutRepetitions);
        if (bundle != null) {
            this.exerciseId = bundle.getLong(AppConstants.exerciseIdKey);
            if (bundle.containsKey(AppConstants.tagKey)) {
                this.categoryName = bundle.getString(AppConstants.categoryNameKey);
                this.tag = bundle.getString(AppConstants.tagKey);
            }
            if (bundle.containsKey(AppConstants.weekIdKey) && bundle.containsKey(AppConstants.dayIdKey)) {
                this.dayId = bundle.getInt(AppConstants.dayIdKey);
            }
            int i = bundle.getInt(AppConstants.planIdKey, -1);

        } else {
            Bundle extras = getIntent().getExtras();
            int i2 = extras.getInt(AppConstants.planIdKey, -1);

            if (extras.containsKey(AppConstants.tagKey)) {
                this.categoryName = extras.getString(AppConstants.categoryNameKey);
                this.tag = extras.getString(AppConstants.tagKey);
            }

            this.exerciseId = extras.getLong(AppConstants.exerciseIdKey);
        }
        StartWorkoutActivityViewModel startWorkoutActivityViewModel = (StartWorkoutActivityViewModel) ViewModelProviders.of(this, this.viewModelFactory).get(StartWorkoutActivityViewModel.class);
        this.viewModel = startWorkoutActivityViewModel;
        Exercise singleExercise = startWorkoutActivityViewModel.getSingleExercise(this.exerciseId);
        if (singleExercise != null) {
            setExercise(singleExercise);
            setStartWorkoutActivity(this);

            textView_startWorkout_workoutTitle.setText(singleExercise.getExerciseTitle());
            textView_startWorkout_workoutRepetitions.setText(singleExercise.getRepetitions());
            imageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onButtonClick(getExercise());
                }
            });
        }
        toolbarStartActivityAppBar.setNavigationOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                StartWorkoutActivity.this.onBackPressed();
            }
        });
    }

    public Exercise getExercise() {
        return this.mExercise;
    }

    public void setExercise(Exercise exercise) {
        this.mExercise = exercise;
    }

    public void setStartWorkoutActivity(StartWorkoutActivity startWorkoutActivity) {
        this.mStartWorkoutActivity = startWorkoutActivity;
    }

    public void onButtonClick(Exercise exercise) {
        Intent intent = new Intent(getBaseContext(), ExerciseActivity.class);
        intent.putExtra(AppConstants.exerciseIdKey, exercise.getId());

        if (!this.tag.isEmpty()) {
            intent.putExtra(AppConstants.categoryNameKey, this.categoryName);
            intent.putExtra(AppConstants.tagKey, this.tag);
        }
        startActivity(intent);
        finish();
    }

    @Override
    protected void onSaveInstanceState(Bundle bundle) {
        bundle.putLong(AppConstants.exerciseIdKey, this.exerciseId);
        if (!this.tag.isEmpty()) {
            bundle.putString(AppConstants.categoryNameKey, this.categoryName);
            bundle.putString(AppConstants.tagKey, this.tag);
        }

        super.onSaveInstanceState(bundle);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}

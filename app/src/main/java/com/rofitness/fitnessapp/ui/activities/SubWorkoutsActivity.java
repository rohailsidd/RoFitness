package com.rofitness.fitnessapp.ui.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.akexorcist.roundcornerprogressbar.RoundCornerProgressBar;
import com.facebook.drawee.view.SimpleDraweeView;
import com.rofitness.fitnessapp.Utils.AppConstants;
import com.rofitness.fitnessapp.database.entities.CategoryTypeProgress;
import com.rofitness.fitnessapp.database.entities.Exercise;
import com.rofitness.fitnessapp.ui.adapters.ExerciseListAdapter;
import com.rofitness.fitnessapp.ui.listeners.ISubWorkoutActivityListener;
import com.rofitness.fitnessapp.ui.viewmodels.SubWorkoutsActivityViewModel;
import com.rofitness.fitnessapp.ui.viewmodels.ViewModelFactory;
import com.rofitness.fitnessapp.R;
import com.shashank.sony.fancydialoglib.Animation;
import com.shashank.sony.fancydialoglib.FancyAlertDialog;
import com.shashank.sony.fancydialoglib.FancyAlertDialogListener;

import java.util.List;

import javax.inject.Inject;

@SuppressWarnings("ALL")
public class SubWorkoutsActivity extends BaseActivity implements ISubWorkoutActivityListener {
    public RecyclerView recyclerViewSubWorkoutsSubWorkoutsList;
    SimpleDraweeView imageViewHeader;
    ImageView imageViewResetProgress;
    RoundCornerProgressBar progressBar;
    TextView textViewCategoryName;
    TextView textViewProgressPercentage;
    Toolbar toolbar;
    View viewHeader;
    @Inject
    ViewModelFactory viewModelFactory;
    List<Exercise> exercisesByTag;
    private String categoryName;
    private String imageLink;
    private String subCategoryName;
    private SubWorkoutsActivityViewModel subWorkoutsActivityViewModel;
    private Integer mExerciseCounter;
    private List<Exercise> mExerciseList;
    private ISubWorkoutActivityListener mISubWorkoutActivityListener;
    ISubWorkoutActivityListener iSubWorkoutActivityListener = this.mISubWorkoutActivityListener;
    private String mTag;
    private Exercise mExercise;
    private ExerciseListAdapter exerciseListAdapter;

    public void setExerciseCounter(Integer num) {
        this.mExerciseCounter = num;
    }

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_sub_workouts);
        recyclerViewSubWorkoutsSubWorkoutsList = findViewById(R.id.recyclerView_subWorkouts_subWorkoutsList);
        bindViews();
        initListener();
        this.subWorkoutsActivityViewModel = (SubWorkoutsActivityViewModel) ViewModelProviders.of(this, this.viewModelFactory).get(SubWorkoutsActivityViewModel.class);
        if (bundle != null) {
            this.categoryName = bundle.getString(AppConstants.categoryNameKey);
            this.subCategoryName = bundle.getString(AppConstants.subCategoryNameKey);
            this.imageLink = bundle.getString(AppConstants.imageLinkKey);
        } else {
            this.categoryName = getIntent().getExtras().getString(AppConstants.categoryNameKey);
            this.subCategoryName = getIntent().getExtras().getString(AppConstants.subCategoryNameKey);
            this.imageLink = getIntent().getExtras().getString(AppConstants.imageLinkKey);
        }
        String str = this.subCategoryName;
        if (str != null) {
            this.textViewCategoryName.setText(str);
            this.imageViewHeader.setImageURI(this.imageLink);
            setExerciseCounter(this.subWorkoutsActivityViewModel.getSubExerciseCount(this.subCategoryName));
            setISubWorkoutActivityListener(this);
            this.subWorkoutsActivityViewModel.getAllCategoryTypeProgressForMainMuscleGroup(this.subCategoryName).observe(this, new Observer<List<CategoryTypeProgress>>() {

                public void onChanged(List<CategoryTypeProgress> list) {
                    SubWorkoutsActivity.this.setProgress();
                    exercisesByTag = SubWorkoutsActivity.this.subWorkoutsActivityViewModel.getExercisesByTag(SubWorkoutsActivity.this.subCategoryName);
                    if (!exercisesByTag.isEmpty()) {
                        setTag(SubWorkoutsActivity.this.subCategoryName);
                        setExerciseList(exercisesByTag);
                        // exerciseListAdapter = new ExerciseListAdapter(SubWorkoutsActivity.this, exercisesByTag, true, mTag, SubWorkoutsActivity.this);

                        //   ExerciseListAdapter exerciseListAdapter;
                        if (exercisesByTag != null) {
                            ExerciseListAdapter exerciseListAdapter2 = (ExerciseListAdapter) recyclerViewSubWorkoutsSubWorkoutsList.getAdapter();
                            if (exerciseListAdapter2 == null) {
                                recyclerViewSubWorkoutsSubWorkoutsList.setLayoutManager(new LinearLayoutManager(recyclerViewSubWorkoutsSubWorkoutsList.getContext()));
                                recyclerViewSubWorkoutsSubWorkoutsList.setHasFixedSize(true);
                                exerciseListAdapter = new ExerciseListAdapter(SubWorkoutsActivity.this, exercisesByTag, true, str, iSubWorkoutActivityListener);
                                recyclerViewSubWorkoutsSubWorkoutsList.setAdapter(exerciseListAdapter);
                                return;
                            }
                            exerciseListAdapter2.setExerciseList(exercisesByTag);
                            exerciseListAdapter2.notifyDataSetChanged();
                        }
                    }
                }
            });


        }
    }

    public void setTag(String str) {
        this.mTag = str;
    }

    public void setISubWorkoutActivityListener(ISubWorkoutActivityListener iSubWorkoutActivityListener) {
        this.mISubWorkoutActivityListener = iSubWorkoutActivityListener;
    }

    public void setExerciseList(List<Exercise> list) {
        this.mExerciseList = list;
    }

    public void setExercise(Exercise exercise) {
        this.mExercise = exercise;
    }

    private void bindViews() {
        View findViewById = findViewById(R.id.layout_sub_workout_header);
        this.viewHeader = findViewById;
        this.toolbar = (Toolbar) findViewById.findViewById(R.id.toolbar_workoutHeader);
        this.imageViewResetProgress = (ImageView) this.viewHeader.findViewById(R.id.imageView_workoutHeader_reset);
        this.progressBar = (RoundCornerProgressBar) this.viewHeader.findViewById(R.id.roundCornerProgressBar_workoutHeader_workoutOverAllProgress);
        this.textViewProgressPercentage = (TextView) this.viewHeader.findViewById(R.id.textView_workoutHeader_percentProgress);
        this.textViewCategoryName = (TextView) this.viewHeader.findViewById(R.id.textView_workoutHeader_title);
        this.imageViewHeader = (SimpleDraweeView) this.viewHeader.findViewById(R.id.imageView_workoutHeader_headerBackground);
    }

    private void initListener() {
        this.toolbar.setNavigationOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                SubWorkoutsActivity.this.finish();
            }
        });
        this.imageViewResetProgress.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                new FancyAlertDialog.Builder(SubWorkoutsActivity.this).setTitle(SubWorkoutsActivity.this.getString(R.string.reset_dialog_title))
                        .setBackgroundColor(Color.parseColor("#303F9F"))
                        .setMessage(SubWorkoutsActivity.this.getString(R.string.reset_dialog_message))
                        .setNegativeBtnText(SubWorkoutsActivity.this.getString(R.string.reset_progress_dialog_negative_btn_text))
                        .setBackgroundColor(ContextCompat.getColor(SubWorkoutsActivity.this, R.color.white))
                        .setPositiveBtnText(SubWorkoutsActivity.this.getString(R.string.reset_dialog_positive_btn_text))
                        .setPositiveBtnBackground(ContextCompat.getColor(SubWorkoutsActivity.this, R.color.colorPrimary))
                        .setNegativeBtnBackground(Color.parseColor("#FFA9A7A8")).setAnimation(Animation.POP)
                        .isCancellable(true).setIcon(R.drawable.dialog_logo, com.shashank.sony.fancydialoglib.Icon.Visible)
                        .OnPositiveClicked(new FancyAlertDialogListener() {

                            @Override
                            public void OnClick() {
                                SubWorkoutsActivity.this.subWorkoutsActivityViewModel.resetProgress(SubWorkoutsActivity.this.subCategoryName);
                                //Intent intent = getIntent();
                                SubWorkoutsActivity.this.finish();
                                //startActivity(intent);
                            }
                        }).OnNegativeClicked(new FancyAlertDialogListener() {


                    @Override
                    public void OnClick() {
                    }
                }).build();
            }
        });
    }


    @Override
    protected void onSaveInstanceState(Bundle bundle) {
        bundle.putString(AppConstants.categoryNameKey, this.categoryName);
        bundle.putString(AppConstants.subCategoryNameKey, this.subCategoryName);
        bundle.putString(AppConstants.imageLinkKey, this.imageLink);
        super.onSaveInstanceState(bundle);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void itemClickListener(Exercise exercise) {
        Intent intent = new Intent(getBaseContext(), StartWorkoutActivity.class);
        intent.putExtra(AppConstants.categoryNameKey, this.categoryName);
        intent.putExtra(AppConstants.tagKey, this.subCategoryName);
        intent.putExtra(AppConstants.exerciseIdKey, exercise.getId());
        startActivity(intent);
    }

    public void itemClick(Exercise exercise) {
        Intent intent = new Intent(getBaseContext(), StartWorkoutActivity.class);
        intent.putExtra(AppConstants.categoryNameKey, this.categoryName);
        intent.putExtra(AppConstants.tagKey, this.subCategoryName);
        intent.putExtra(AppConstants.exerciseIdKey, exercise.getId());
        startActivity(intent);
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void setProgress() {
        float mainMuscleProgress = this.subWorkoutsActivityViewModel.getMainMuscleProgress(this.subCategoryName);
        this.progressBar.setProgress(mainMuscleProgress);
        this.textViewProgressPercentage.setText(getString(R.string.text_progress_completed_target_muscle_header, new Object[]{String.valueOf(mainMuscleProgress)}));
    }
}